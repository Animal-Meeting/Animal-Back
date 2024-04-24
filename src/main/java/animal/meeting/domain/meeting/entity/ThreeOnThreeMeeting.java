package animal.meeting.domain.meeting.entity;

import org.hibernate.annotations.UuidGenerator;

import animal.meeting.domain.user.entity.User;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class ThreeOnThreeMeeting extends MeetingDetails {
	@Id
	@UuidGenerator
	private String id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id1")
	private User user1;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id2")
	private User user2;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id3")
	private User user3;
}
