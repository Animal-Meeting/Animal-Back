package animal.meeting.domain.measurements.service;

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
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class MeasurementResultService {

    private final MaleMeasurementResultRepository maleMeasurementResultRepository;
    private final FemaleMeasurementResultRepository femaleMeasurementResultRepository;
    private final S3Service s3Service;
    private final MeasurementResultMapper measurementResultMapper;

    public void saveMaleMeasurementResult(MaleMeasurementResultRequest request) throws IOException {
        validateMultipartFile(request.animalPhoto());
        validateStudentId(request.studentId(), request.gender());
        String photoUrl = s3Service.uploadMultipartFile(request.animalPhoto());
        MaleMeasurementResult measurementResult = measurementResultMapper.toMaleEntity(request, photoUrl);
        maleMeasurementResultRepository.save(measurementResult);
    }

    public void saveFemaleMeasurementResult(FemaleMeasurementResultRequest request) throws IOException {
        validateMultipartFile(request.animalPhoto());
        validateStudentId(request.studentId(), request.gender());
        String photoUrl = s3Service.uploadMultipartFile(request.animalPhoto());
        FemaleMeasurementResult measurementResult = measurementResultMapper.toFemaleEntity(request, photoUrl);
        femaleMeasurementResultRepository.save(measurementResult);
    }

    private void validateStudentId(String studentId, Gender gender) {
        Optional<?> measurementResultOptional;

        if (gender.equals(Gender.MALE)) {
            measurementResultOptional = maleMeasurementResultRepository
                .findMaleMeasurementResultByStudentId(studentId);
        } else {
            measurementResultOptional = femaleMeasurementResultRepository
                .findFemaleMeasurementResultByStudentId(studentId);
        }

        if (measurementResultOptional.isPresent()) {
            throw new CustomException(ErrorCode.DUPLICATED_STUDENT_ID);
        }
    }

    private void validateMultipartFile(MultipartFile multipartFile) {
        if (multipartFile == null || multipartFile.isEmpty()) {
            throw new CustomException(ErrorCode.INVALID_MULTIPARTFILE);
        }
    }

    public MeasurementResultResponse getMaleMeasurementResult(String studentId) {
        Optional<FemaleMeasurementResult> femaleResultOptional = femaleMeasurementResultRepository
            .findFemaleMeasurementResultByStudentId(studentId);

        Optional<MaleMeasurementResult> maleResultOptional = maleMeasurementResultRepository
            .findMaleMeasurementResultByStudentId(studentId);

        if (femaleResultOptional.isPresent()) {
            FemaleMeasurementResult femaleResult = femaleResultOptional.get();
            return createMeasurementResultResponse(femaleResult);
        } else if (maleResultOptional.isPresent()) {
            MaleMeasurementResult maleResult = maleResultOptional.get();
            return createMeasurementResultResponse(maleResult);
        } else {
            return null;
        }
    }

    private MeasurementResultResponse createMeasurementResultResponse(FemaleMeasurementResult result) {
        return new MeasurementResultResponse(
            result.getPhotoUrl(),
            result.getAnimalType(),
            List.of(
                new MeasurementResultResponse.AnimalScore("girl_dog", result.getDogScore()),
                new MeasurementResultResponse.AnimalScore("girl_cat", result.getCatScore()),
                new MeasurementResultResponse.AnimalScore("girl_rabbit", result.getRabbitScore()),
                new MeasurementResultResponse.AnimalScore("girl_desertFox", result.getDesertFoxScore()),
                new MeasurementResultResponse.AnimalScore("girl_deer", result.getDeerScore()),
                new MeasurementResultResponse.AnimalScore("girl_hamster", result.getHamsterScore())
            )
        );
    }

    private MeasurementResultResponse createMeasurementResultResponse(MaleMeasurementResult result) {
        return new MeasurementResultResponse(
            result.getPhotoUrl(),
            result.getAnimalType(),
            List.of(
                new MeasurementResultResponse.AnimalScore("man_dog", result.getDogScore()),
                new MeasurementResultResponse.AnimalScore("man_cat", result.getCatScore()),
                new MeasurementResultResponse.AnimalScore("man_rabbit", result.getRabbitScore()),
                new MeasurementResultResponse.AnimalScore("man_dinosaur", result.getDinosaurScore()),
                new MeasurementResultResponse.AnimalScore("man_bear", result.getBearScore()),
                new MeasurementResultResponse.AnimalScore("man_wolf", result.getWolfScore())
            )
        );
    }
}
