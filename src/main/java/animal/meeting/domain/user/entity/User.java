package animal.meeting.domain.user.entity;

import animal.meeting.domain.commons.BaseAuditEntity;
import animal.meeting.domain.meeting.entity.type.MeetingGroupType;
import animal.meeting.domain.user.dto.request.NewUserRegisterRequest;
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
	private String phoneNumber;

	@Enumerated(EnumType.STRING)
	@Column(columnDefinition = "enum('MALE', 'FEMALE')", nullable = false)
	private Gender gender;

	@Column(columnDefinition = "varchar(30)", nullable = false)
	private String kakao;

	@Enumerated(EnumType.STRING)
	private AnimalType firstAnimalType;

	@Enumerated(EnumType.STRING)
	private AnimalType secondAnimalType;

	@Enumerated(EnumType.STRING)
	private AnimalType selfAnimalType;

	@Enumerated(EnumType.STRING)
	private MeetingGroupType groupType;

	@Builder(access = AccessLevel.PUBLIC)
	private User(
		Long id,
		String phoneNumber,
		Gender gender,
		String kakao,
		AnimalType firstAnimalType,
		AnimalType secondAnimalType,
		AnimalType selfAnimalType,
		MeetingGroupType groupType) {
		this.id = id;
		this.phoneNumber = phoneNumber;
		this.gender = gender;
		this.kakao = kakao;
		this.firstAnimalType = firstAnimalType;
		this.secondAnimalType = secondAnimalType;
		this.selfAnimalType = selfAnimalType;
		this.groupType = groupType;
	}

	public static User create(
		NewUserRegisterRequest request,
		MeetingGroupType groupType
	) {
		return User.builder()
			.phoneNumber(request.phoneNumber())
			.gender(request.gender())
			.kakao(request.kakao())
			.firstAnimalType(request.firstAnimalType())
			.secondAnimalType(request.secondAnimalType())
			.selfAnimalType(request.selfAnimalType())
			.groupType(groupType)
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

	public void changeMeetingGroupType(MeetingGroupType groupType) {
		this.groupType = groupType;
	}
}

