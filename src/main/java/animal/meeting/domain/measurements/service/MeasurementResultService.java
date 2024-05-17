package animal.meeting.domain.measurements.service;

import animal.meeting.domain.measurements.dto.request.FemaleMeasurementResultRequest;
import animal.meeting.domain.measurements.dto.request.MaleMeasurementResultRequest;
import animal.meeting.domain.measurements.dto.response.MeasurementResultResponse;
import animal.meeting.domain.measurements.entity.female.FemaleMeasurementResult;
import animal.meeting.domain.measurements.entity.male.MaleMeasurementResult;
import animal.meeting.domain.measurements.repository.FemaleMeasurementResultRepository;
import animal.meeting.domain.measurements.repository.MaleMeasurementResultRepository;
import animal.meeting.domain.measurements.s3.S3Service;
import animal.meeting.domain.measurements.service.mapper.MeasurementResultMapper;
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

    public MeasurementResultResponse saveMaleMeasurementResult(MaleMeasurementResultRequest request) throws IOException {
        String photoUrl = s3Service.uploadMultipartFile(request.animalPhoto());
        MaleMeasurementResult measurementResult = measurementResultMapper.toMaleEntity(request, photoUrl);
        maleMeasurementResultRepository.save(measurementResult);

        return new MeasurementResultResponse(
                photoUrl,
                request.animalType(),
                List.of(
                        new MeasurementResultResponse.AnimalScore("dog", request.dogScore()),
                        new MeasurementResultResponse.AnimalScore("cat", request.catScore()),
                        new MeasurementResultResponse.AnimalScore("rabbit", request.rabbitScore()),
                        new MeasurementResultResponse.AnimalScore("dinosaur", request.dinosaurScore()),
                        new MeasurementResultResponse.AnimalScore("bear", request.bearScore()),
                        new MeasurementResultResponse.AnimalScore("wolf", request.wolfScore())
                )
        );
    }

    public MeasurementResultResponse saveFemaleMeasurementResult(FemaleMeasurementResultRequest request) throws IOException {
        String photoUrl = s3Service.uploadMultipartFile(request.animalPhoto());
        FemaleMeasurementResult measurementResult = measurementResultMapper.toFemaleEntity(request, photoUrl);
        femaleMeasurementResultRepository.save(measurementResult);

        return new MeasurementResultResponse(
                photoUrl,
                request.animalType(),
                List.of(
                        new MeasurementResultResponse.AnimalScore("dog", request.dogScore()),
                        new MeasurementResultResponse.AnimalScore("cat", request.catScore()),
                        new MeasurementResultResponse.AnimalScore("rabbit", request.rabbitScore()),
                        new MeasurementResultResponse.AnimalScore("desertFox", request.desertFoxScore()),
                        new MeasurementResultResponse.AnimalScore("deer", request.deerScore()),
                        new MeasurementResultResponse.AnimalScore("hamster", request.hamsterScore())
                )
        );
    }
}
