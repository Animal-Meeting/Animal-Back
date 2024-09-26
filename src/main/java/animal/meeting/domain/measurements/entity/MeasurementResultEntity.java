package animal.meeting.domain.measurements.entity;

import animal.meeting.domain.commons.BaseTimeEntity;
import animal.meeting.domain.user.entity.type.AnimalType;
import animal.meeting.domain.user.entity.type.Gender;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.MappedSuperclass;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@MappedSuperclass
public abstract class MeasurementResultEntity extends BaseTimeEntity {

	@Column(unique = true)
	@NotNull
	private String studentId;

	private String photoUrl;

	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
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

	protected MeasurementResultEntity(String studentId, String photoUrl, AnimalType animalType, Gender gender,
		Integer dogScore, Integer catScore, Integer rabbitScore) {
		this.studentId = studentId;
		this.photoUrl = photoUrl;
		this.animalType = animalType;
		this.gender = gender;
		this.dogScore = dogScore;
		this.catScore = catScore;
		this.rabbitScore = rabbitScore;
	}
}
