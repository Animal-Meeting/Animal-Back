package animal.meeting.entity;

import animal.meeting.BaseTimeEntity;
import animal.meeting.entity.type.Gender;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.TemporalType;
import lombok.Getter;

@Entity
@Getter
public class User extends BaseTimeEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(columnDefinition = "varchar(30)", nullable = false)
	private String name;

	@Column(columnDefinition = "varchar(30)", nullable = false)
	private String phoneNumber;

	@Enumerated(EnumType.STRING)
	@Column(columnDefinition = "enum('MALE', 'FEMALE')" , nullable = false)
	private Gender gender;

	@Column(name = "first_choice_animal_type")
	private String firstChoiceAnimalType;

	@Column(name = "second_choice_animal_type")
	private String secondChoiceAnimalType;

}