package animal.meeting.domain.meeting.entity;

import org.hibernate.annotations.UuidGenerator;

import animal.meeting.domain.user.entity.User;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;

@Entity
@Getter
public class OneOnOneMeeting extends MeetingDetails {

	@Id
	@UuidGenerator
	private String id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id")
	private User user1;
}
