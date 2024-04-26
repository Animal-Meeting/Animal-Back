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
public class OneOnOneMeeting extends MeetingDetails implements MeetingGroup{

	@Id
	@UuidGenerator
	private String id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id")
	private User user1;



	@Override
	public void addUser(List<User> userList) {
		this.user1 = userList.get(0);
	}

	@Builder(access = AccessLevel.PRIVATE)
	private OneOnOneMeeting(
		MeetingStatus status,
		Gender gender
	) {
		this.gender = gender;
		this.status = status;
	}

	public static OneOnOneMeeting create(Gender gender) {
		return OneOnOneMeeting.builder()
			.gender(gender)
			.status(MeetingStatus.WAITING)
			.build();
	}
}
