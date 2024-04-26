package animal.meeting.domain.meeting.entity.type;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum MeetingGroupType {
	ONE_ON_ONE(1),
	TWO_ON_TWO(2),
	THREE_ON_THREE(3);

	private final int userCount;
}
