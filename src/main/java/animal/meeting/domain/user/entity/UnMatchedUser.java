package animal.meeting.domain.user.entity;

import animal.meeting.domain.meeting.entity.type.MeetingGroupType;
import animal.meeting.domain.user.entity.type.Gender;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class UnMatchedUser {

	String phoneNumber;
	Gender gender;
	MeetingGroupType type;

	public static UnMatchedUser create(User user) {

		return UnMatchedUser.builder()
			.phoneNumber(user.getPhoneNumber())
			.gender(user.getGender())
			.type(user.getGroupType())
			.build();
	}
}
