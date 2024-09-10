package animal.meeting.domain.meeting.entity;

import org.hibernate.annotations.Comment;

import animal.meeting.domain.BaseTimeEntity;
import animal.meeting.domain.meeting.entity.type.MeetingGroupType;
import animal.meeting.domain.user.dto.request.UserRegisterRequest;
import animal.meeting.domain.user.entity.User;
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
@NoArgsConstructor
public class MatchingResult extends BaseTimeEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Comment("남자 그룹 ID")
	@Column(nullable = false)
	private String manGroupId;

	@Comment("여자 그룹 ID")
	@Column(nullable = false)
	private String girlGroupId;

	@Comment("미팅 타입")
	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private MeetingGroupType meetingType;

	@Comment("오픈 카카오톡 링크")
	@Column(columnDefinition = "varchar(80)", unique = true)
	private String kakaoLink;


	@Builder(access = AccessLevel.PRIVATE)
	private MatchingResult(
		String manGroupId,
		String girlGroupId,
		MeetingGroupType meetingType,
		String kakaoLink) {
		this.manGroupId = manGroupId;
		this.girlGroupId = girlGroupId;
		this.meetingType = meetingType;
		this.kakaoLink = kakaoLink;
	}

	public static MatchingResult create(
		String manGroupId,
		String girlGroupId,
		MeetingGroupType meetingType
	) {
		return MatchingResult.builder()
			.manGroupId(manGroupId)
			.girlGroupId(girlGroupId)
			.meetingType(meetingType)
			.kakaoLink(null)
			.build();
	}
}
