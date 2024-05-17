package animal.meeting.domain.measurements.dto.response;

import animal.meeting.domain.user.entity.type.AnimalType;

import java.util.List;

public record MeasurementResultResponse(
        String photoUrl,
        AnimalType animalType,
        List<AnimalScore> scores) {

    public record AnimalScore(String animal, int score) {
    }
}
