package animal.meeting.domain.meeting.entity;

import java.util.UUID;

import org.hibernate.annotations.Comment;

import animal.meeting.domain.BaseTimeEntity;
import animal.meeting.domain.meeting.entity.type.MeetingGroupType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class MatchingResult extends BaseTimeEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Comment("남자 그룹 ID")
	@Column(nullable = false)
	private UUID manGroupId;

	@Comment("여자 그룹 ID")
	@Column(nullable = false)
	private UUID girlGroupId;

	@Comment("미팅 타입")
	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private MeetingGroupType meetingType;

	@Comment("오픈 카카오톡 링크")
	@Column(columnDefinition = "varchar(80)", unique = true)
	private String kakaoLink;
}
