package animal.meeting.domain.user.entity;

import animal.meeting.domain.BaseAuditEntity;
import animal.meeting.domain.user.dto.request.UserRegisterRequest;
import animal.meeting.domain.user.entity.type.AnimalType;
import animal.meeting.domain.user.entity.type.Gender;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = lombok.AccessLevel.PROTECTED)
public class User extends BaseAuditEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(columnDefinition = "varchar(30)", nullable = false)
	private String name;

	@Column(columnDefinition = "varchar(30)", nullable = false, unique = true)
	private String phoneNumber;

	@Enumerated(EnumType.STRING)
	@Column(columnDefinition = "enum('MALE', 'FEMALE')", nullable = false)
	private Gender gender;

	@Enumerated(EnumType.STRING)
	private AnimalType firstAnimalType;

	@Enumerated(EnumType.STRING)
	private AnimalType secondAnimalType;

	@Enumerated(EnumType.STRING)
	private AnimalType selfAnimalType;

	@Builder(access = AccessLevel.PRIVATE)
	private User(
		String name,
		String phoneNumber,
		Gender gender,
		AnimalType firstAnimalType,
		AnimalType secondAnimalType,
		AnimalType selfAnimalType) {
		this.name = name;
		this.phoneNumber = phoneNumber;
		this.gender = gender;
		this.firstAnimalType = firstAnimalType;
		this.secondAnimalType = secondAnimalType;
		this.selfAnimalType = selfAnimalType;
	}

	public static User create(
		UserRegisterRequest request
	) {
		return User.builder()
			.name(request.name())
			.phoneNumber(request.phoneNumber())
			.gender(request.gender())
			.firstAnimalType(request.firstAnimalType())
			.secondAnimalType(request.secondAnimalType())
			.selfAnimalType(request.selfAnimalType())
			.build();
	}

	public void changeFirstAnimalType(AnimalType firstAnimalType) {
		this.firstAnimalType = firstAnimalType;
	}

	public void changeSecondAnimalType(AnimalType secondAnimalType) {
		this.secondAnimalType = secondAnimalType;
	}

	public void changeSelfAnimalType(AnimalType selfAnimalType) {
		this.selfAnimalType = selfAnimalType;
	}

}

