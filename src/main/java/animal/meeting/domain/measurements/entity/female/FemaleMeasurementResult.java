package animal.meeting.domain.measurements.entity.female;

import animal.meeting.domain.BaseTimeEntity;
import animal.meeting.domain.user.entity.type.AnimalType;
import animal.meeting.domain.user.entity.type.Gender;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Entity
@Table(name = "female_measurement_result")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Getter
@Builder
public class FemaleMeasurementResult extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    @NotNull
    private String studentId;

    private String photoUrl;

    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "enum('DOG', 'CAT', 'RABBIT', 'DESERT_FOX', 'DEER', 'HAMSTER')", nullable = false)
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
    private Integer desertFoxScore;

    @NotNull
    private Integer deerScore;

    @NotNull
    private Integer hamsterScore;
}
