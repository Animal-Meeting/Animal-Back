package animal.meeting.domain.measurements.dto.response;

import animal.meeting.domain.measurements.entity.female.FemaleMeasurementResult;
import animal.meeting.domain.measurements.entity.male.MaleMeasurementResult;
import animal.meeting.domain.user.entity.type.AnimalType;

import java.util.List;

public record MeasurementResultResponse(
        String photoUrl,
        AnimalType animalType,
        List<AnimalScore> scores) {

    public record AnimalScore(String animal, int score) {
    }

    public static MeasurementResultResponse createMeasurementResultResponse(FemaleMeasurementResult result) {
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

    public static MeasurementResultResponse createMeasurementResultResponse(MaleMeasurementResult result) {
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



