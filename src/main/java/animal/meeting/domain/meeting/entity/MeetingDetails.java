package animal.meeting.domain.meeting.entity;

import org.hibernate.annotations.Comment;

import animal.meeting.domain.BaseAuditEntity;
import animal.meeting.domain.meeting.entity.type.MeetingStatus;
import animal.meeting.domain.user.entity.type.Gender;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.NoArgsConstructor;

@MappedSuperclass
@NoArgsConstructor(access = lombok.AccessLevel.PROTECTED)
@Getter
public abstract class MeetingDetails extends BaseAuditEntity implements MeetingGroup{

	@Comment("매칭 상태")
	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	protected MeetingStatus status;

	@Comment("그룹 성별")
	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	protected Gender gender;
}
