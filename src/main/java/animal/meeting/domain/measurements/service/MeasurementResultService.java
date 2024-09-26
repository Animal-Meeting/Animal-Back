package animal.meeting.domain.measurements.service;

import animal.meeting.domain.measurements.dto.request.CommonMeasurementRequest;
import animal.meeting.domain.measurements.dto.request.FemaleMeasurementResultRequest;
import animal.meeting.domain.measurements.dto.request.MaleMeasurementResultRequest;
import animal.meeting.domain.measurements.dto.response.MeasurementResultResponse;
import animal.meeting.domain.measurements.entity.female.FemaleMeasurementResult;
import animal.meeting.domain.measurements.entity.male.MaleMeasurementResult;
import animal.meeting.domain.measurements.repository.FemaleMeasurementResultRepository;
import animal.meeting.domain.measurements.repository.MaleMeasurementResultRepository;
import animal.meeting.domain.measurements.service.mapper.MeasurementResultMapper;
import animal.meeting.domain.user.entity.type.Gender;
import animal.meeting.global.error.CustomException;
import animal.meeting.global.error.constants.ErrorCode;
import animal.meeting.global.s3.S3Service;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class MeasurementResultService {

    private final MaleMeasurementResultRepository maleMeasurementResultRepository;
    private final FemaleMeasurementResultRepository femaleMeasurementResultRepository;
    private final S3Service s3Service;
    private final MeasurementResultMapper measurementResultMapper;

    public void saveMeasurementResult(CommonMeasurementRequest request) throws IOException {
        validateMultipartFile(request.getAnimalPhoto());
        validateDuplicateStudentId(request.getStudentId(), request.getGender());

        String photoUrl = s3Service.uploadMultipartFile(request.getAnimalPhoto());

        if (request instanceof MaleMeasurementResultRequest maleRequest) {
            MaleMeasurementResult maleResult = measurementResultMapper.toMaleEntity(maleRequest, photoUrl);
            maleMeasurementResultRepository.save(maleResult);
        } else if (request instanceof FemaleMeasurementResultRequest femaleRequest) {
            FemaleMeasurementResult femaleResult = measurementResultMapper.toFemaleEntity(femaleRequest, photoUrl);
            femaleMeasurementResultRepository.save(femaleResult);
        }
    }

    private void validateDuplicateStudentId(String studentId, Gender gender) {
        Optional<?> measurementResultOptional = gender.equals(Gender.MALE)
            ? maleMeasurementResultRepository.findMaleMeasurementResultByStudentId(studentId)
            : femaleMeasurementResultRepository.findFemaleMeasurementResultByStudentId(studentId);

        measurementResultOptional.ifPresent(result -> {
            throw new CustomException(ErrorCode.DUPLICATED_STUDENT_ID);
        });
    }

    private void validateMultipartFile(MultipartFile multipartFile) {
        if (multipartFile == null || multipartFile.isEmpty()) {
            throw new CustomException(ErrorCode.INVALID_MULTIPARTFILE);
        }
    }

    public MeasurementResultResponse getMeasurementResult(String studentId) {
        return femaleMeasurementResultRepository.findFemaleMeasurementResultByStudentId(studentId)
            .map(MeasurementResultResponse::createMeasurementResultResponse)
            .or(() -> maleMeasurementResultRepository.findMaleMeasurementResultByStudentId(studentId)
                .map(MeasurementResultResponse::createMeasurementResultResponse))
            .orElseThrow(() -> new CustomException(ErrorCode.RESULT_NOT_FOUND));
    }
}
