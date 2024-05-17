package animal.meeting.domain.measurements.service;

import animal.meeting.domain.measurements.dto.request.FemaleMeasurementResultRequest;
import animal.meeting.domain.measurements.dto.request.MaleMeasurementResultRequest;
import animal.meeting.domain.measurements.dto.response.MeasurementResultResponse;
import animal.meeting.domain.measurements.entity.female.FemaleMeasurementResult;
import animal.meeting.domain.measurements.entity.male.MaleMeasurementResult;
import animal.meeting.domain.measurements.repository.FemaleMeasurementResultRepository;
import animal.meeting.domain.measurements.repository.MaleMeasurementResultRepository;
import animal.meeting.domain.measurements.service.mapper.MeasurementResultMapper;
import animal.meeting.global.s3.S3Service;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.io.IOException;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class MeasurementResultService {

    private final MaleMeasurementResultRepository maleMeasurementResultRepository;
    private final FemaleMeasurementResultRepository femaleMeasurementResultRepository;
    private final S3Service s3Service;
    private final MeasurementResultMapper measurementResultMapper;

    public void saveMaleMeasurementResult(MaleMeasurementResultRequest request) throws IOException {
        String photoUrl = s3Service.uploadMultipartFile(request.animalPhoto());
        MaleMeasurementResult measurementResult = measurementResultMapper.toMaleEntity(request, photoUrl);
        maleMeasurementResultRepository.save(measurementResult);
    }

    public void saveFemaleMeasurementResult(FemaleMeasurementResultRequest request) throws IOException {
        String photoUrl = s3Service.uploadMultipartFile(request.animalPhoto());
        FemaleMeasurementResult measurementResult = measurementResultMapper.toFemaleEntity(request, photoUrl);
        femaleMeasurementResultRepository.save(measurementResult);
    }

    public MeasurementResultResponse getMaleMeasurementResult(String studentId) {
        FemaleMeasurementResult femaleResult = femaleMeasurementResultRepository
            .findFemaleMeasurementResultByStudentId(studentId).orElse(null);

        MaleMeasurementResult maleResult = maleMeasurementResultRepository
            .findFemaleMeasurementResultByStudentId(studentId).orElse(null);

        if (femaleResult != null) {
            return new MeasurementResultResponse(
                femaleResult.getPhotoUrl(),
                femaleResult.getAnimalType(),
                List.of(
                    new MeasurementResultResponse.AnimalScore("dog", femaleResult.getDogScore()),
                    new MeasurementResultResponse.AnimalScore("cat", femaleResult.getCatScore()),
                    new MeasurementResultResponse.AnimalScore("rabbit", femaleResult.getRabbitScore()),
                    new MeasurementResultResponse.AnimalScore("desertFox", femaleResult.getDesertFoxScore()),
                    new MeasurementResultResponse.AnimalScore("deer", femaleResult.getDeerScore()),
                    new MeasurementResultResponse.AnimalScore("hamster", femaleResult.getHamsterScore())
                )
            );
        }
        else if (maleResult != null) {
            return new MeasurementResultResponse(
                maleResult.getPhotoUrl(),
                maleResult.getAnimalType(),
                List.of(
                    new MeasurementResultResponse.AnimalScore("dog", maleResult.getDogScore()),
                    new MeasurementResultResponse.AnimalScore("cat", maleResult.getCatScore()),
                    new MeasurementResultResponse.AnimalScore("rabbit", maleResult.getRabbitScore()),
                    new MeasurementResultResponse.AnimalScore("dinosaur", maleResult.getDinosaurScore()),
                    new MeasurementResultResponse.AnimalScore("bear", maleResult.getBearScore()),
                    new MeasurementResultResponse.AnimalScore("wolf", maleResult.getWolfScore())
                )
            );
        }
        else {
            return null;
        }
    }
}