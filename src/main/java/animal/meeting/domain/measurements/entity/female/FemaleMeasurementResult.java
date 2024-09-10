package animal.meeting.domain.measurements.entity.female;

import animal.meeting.domain.measurements.entity.MeasurementResultEntity;
import animal.meeting.domain.user.entity.type.AnimalType;
import animal.meeting.domain.user.entity.type.Gender;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Entity
@Table(name = "female_measurement_result")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class FemaleMeasurementResult extends MeasurementResultEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private Integer desertFoxScore;

    @NotNull
    private Integer deerScore;

    @NotNull
    private Integer hamsterScore;

    @Builder
    public FemaleMeasurementResult(String studentId, String photoUrl,
        AnimalType animalType, Gender gender,
        Integer dogScore, Integer catScore, Integer rabbitScore,
        Integer desertFoxScore, Integer deerScore, Integer hamsterScore) {
        super(studentId, photoUrl, animalType, gender, dogScore, catScore, rabbitScore);
        this.desertFoxScore = desertFoxScore;
        this.deerScore = deerScore;
        this.hamsterScore = hamsterScore;
    }
}
