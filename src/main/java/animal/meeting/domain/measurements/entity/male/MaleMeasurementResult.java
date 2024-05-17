package animal.meeting.domain.measurements.entity.male;

import animal.meeting.domain.BaseTimeEntity;
import animal.meeting.domain.user.entity.type.AnimalType;
import animal.meeting.domain.user.entity.type.Gender;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Entity
@Table(name = "male_measurement_result")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Getter
@Builder
public class MaleMeasurementResult extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    @NotNull
    private String studentId;

    private String photoUrl;

    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "enum('DOG', 'CAT', 'RABBIT', 'DINOSAUR', 'BEAR', 'WOLF')", nullable = false)
    private AnimalType animalType;

    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "enum('MALE', 'FEMALE')", nullable = false)
    private Gender gender;

    @NotNull
    private Integer dogScore;

    @NotNull
    private Integer catScore;

    @NotNull
    private Integer rabbitScore;

    @NotNull
    private Integer dinosaurScore;

    @NotNull
    private Integer bearScore;

    @NotNull
    private Integer wolfScore;
}
