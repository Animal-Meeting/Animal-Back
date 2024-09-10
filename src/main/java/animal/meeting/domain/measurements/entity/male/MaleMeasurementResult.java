package animal.meeting.domain.measurements.entity.male;

import animal.meeting.domain.measurements.entity.MeasurementResultEntity;
import animal.meeting.domain.user.entity.type.AnimalType;
import animal.meeting.domain.user.entity.type.Gender;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Entity
@Table(name = "male_measurement_result")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class MaleMeasurementResult extends MeasurementResultEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private Integer dinosaurScore;

    @NotNull
    private Integer bearScore;

    @NotNull
    private Integer wolfScore;

    @Builder
    public MaleMeasurementResult(String studentId, String photoUrl,
        AnimalType animalType, Gender gender,
        Integer dogScore, Integer catScore, Integer rabbitScore,
        Integer dinosaurScore, Integer bearScore, Integer wolfScore) {
        super(studentId, photoUrl, animalType, gender, dogScore, catScore, rabbitScore);
        this.dinosaurScore = dinosaurScore;
        this.bearScore = bearScore;
        this.wolfScore = wolfScore;
    }
}
