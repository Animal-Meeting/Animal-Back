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
                .studentId(request.getStudentId())
                .photoUrl(photoUrl)
                .animalType(request.getAnimalType())
                .gender(request.getGender())
                .dogScore(request.getDogScore())
                .catScore(request.getCatScore())
                .rabbitScore(request.getRabbitScore())
                .dinosaurScore(request.getDinosaurScore())
                .bearScore(request.getBearScore())
                .wolfScore(request.getWolfScore())
                .build();
    }

    public FemaleMeasurementResult toFemaleEntity(FemaleMeasurementResultRequest request, String photoUrl) {
        return FemaleMeasurementResult.builder()
                .studentId(request.getStudentId())
                .photoUrl(photoUrl)
                .animalType(request.getAnimalType())
                .gender(request.getGender())
                .dogScore(request.getDogScore())
                .catScore(request.getCatScore())
                .rabbitScore(request.getRabbitScore())
                .desertFoxScore(request.getCatScore())
                .deerScore(request.getCatScore())
                .hamsterScore(request.getCatScore())
                .build();
    }
}
