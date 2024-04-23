package animal.meeting.domain.meeting.entity.type;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum MeetingGroupType {

	ONE_ON_ONE("1대1 미팅"),
	TWO_ON_TWO("2대2 미팅"),
	THREE_ON_THREE("3대3 미팅");

	private final String value;
}
