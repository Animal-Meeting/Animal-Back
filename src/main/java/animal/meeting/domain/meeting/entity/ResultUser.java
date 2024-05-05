package animal.meeting.domain.meeting.entity;

import animal.meeting.domain.user.entity.User;
import animal.meeting.domain.user.entity.type.AnimalType;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class ResultUser {

	private String phoneNumber;
	private AnimalType selfAnimalType;

	public static ResultUser create(User user) {
		return ResultUser.builder()
			.phoneNumber(user.getPhoneNumber())
			.selfAnimalType(user.getSelfAnimalType())
			.build();
	}
}
