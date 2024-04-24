package animal.meeting.domain.user.entity;

import animal.meeting.domain.BaseAuditEntity;
import animal.meeting.domain.BaseTimeEntity;
import animal.meeting.domain.user.entity.type.AninmalType;
import animal.meeting.domain.user.entity.type.Gender;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;

@Entity
@Getter
public class User extends BaseAuditEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(columnDefinition = "varchar(30)", nullable = false)
	private String name;

	@Column(columnDefinition = "varchar(30)", nullable = false)
	private String phoneNumber;

	@Enumerated(EnumType.STRING)
	@Column(columnDefinition = "enum('MALE', 'FEMALE')", nullable = false)
	private Gender gender;

	@Enumerated(EnumType.STRING)
	private AninmalType firstChoiceAnimalType;

	@Enumerated(EnumType.STRING)
	private AninmalType secondChoiceAnimalType;

	@Enumerated(EnumType.STRING)
	private AninmalType selfAnimalType;
}
