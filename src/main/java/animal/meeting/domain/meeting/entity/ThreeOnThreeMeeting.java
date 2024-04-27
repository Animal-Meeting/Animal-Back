package animal.meeting.domain.meeting.entity;

import java.util.List;

import org.hibernate.annotations.UuidGenerator;

import animal.meeting.domain.meeting.entity.type.MeetingStatus;
import animal.meeting.domain.user.entity.User;
import animal.meeting.domain.user.entity.type.Gender;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class ThreeOnThreeMeeting extends MeetingDetails{
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

	@Override
	public void addUser(List<User> userList) {
		this.user1 = userList.get(0);
		this.user2 = userList.get(1);
		this.user3 = userList.get(2);
	}

	@Builder(access = AccessLevel.PRIVATE)
	private ThreeOnThreeMeeting(
		MeetingStatus status,
		Gender gender
	) {
		this.gender = gender;
		this.status = status;
	}

	public static ThreeOnThreeMeeting create(Gender gender) {
		return ThreeOnThreeMeeting.builder()
			.gender(gender)
			.status(MeetingStatus.WAITING)
			.build();
	}
}
