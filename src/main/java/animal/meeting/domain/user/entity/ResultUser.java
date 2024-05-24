package animal.meeting.domain.user.entity;

import animal.meeting.domain.user.entity.type.AnimalType;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class ResultUser {

	private Long userId;
	private AnimalType selfAnimalType;

	public static ResultUser create(User user) {
		return ResultUser.builder()
			.userId(user.getId())
			.selfAnimalType(user.getSelfAnimalType())
			.build();
	}
}
