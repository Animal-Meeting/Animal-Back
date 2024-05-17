package animal.meeting.domain.measurements.service.mapper;

import animal.meeting.domain.measurements.dto.request.FemaleMeasurementResultRequest;
import animal.meeting.domain.measurements.dto.request.MaleMeasurementResultRequest;
import animal.meeting.domain.measurements.entity.female.FemaleMeasurementResult;
import animal.meeting.domain.measurements.entity.male.MaleMeasurementResult;
import org.springframework.stereotype.Component;

@Component
public class MeasurementResultMapper {

    public MaleMeasurementResult toMaleEntity(MaleMeasurementResultRequest request, String photoUrl) {
        return MaleMeasurementResult.builder()
                .studentId(request.studentId())
                .photoUrl(photoUrl)
                .animalType(request.animalType())
                .gender(request.gender())
                .dogScore(request.dogScore())
                .catScore(request.catScore())
                .rabbitScore(request.rabbitScore())
                .dinosaurScore(request.dinosaurScore())
                .bearScore(request.bearScore())
                .wolfScore(request.wolfScore())
                .build();
    }

    public FemaleMeasurementResult toFemaleEntity(FemaleMeasurementResultRequest request, String photoUrl) {
        return FemaleMeasurementResult.builder()
                .studentId(request.studentId())
                .photoUrl(photoUrl)
                .animalType(request.animalType())
                .gender(request.gender())
                .dogScore(request.dogScore())
                .catScore(request.catScore())
                .rabbitScore(request.rabbitScore())
                .desertFoxScore(request.desertFoxScore())
                .deerScore(request.deerScore())
                .hamsterScore(request.hamsterScore())
                .build();
    }
}
