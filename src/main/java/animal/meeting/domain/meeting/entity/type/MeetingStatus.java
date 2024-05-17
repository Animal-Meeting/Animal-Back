package animal.meeting.domain.meeting.entity.type;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum MeetingStatus {

	COMPLETED("매칭 완료"),
	WAITING("매칭 대기"),
	FAIL("매칭 실패");

	private final String value;
}
