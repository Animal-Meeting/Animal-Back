package animal.meeting.domain.meeting.dto.response;

import java.util.List;

import animal.meeting.domain.meeting.entity.OneOnOneMeeting;
import animal.meeting.domain.meeting.entity.ThreeOnThreeMeeting;
import animal.meeting.domain.meeting.entity.TwoOnTwoMeeting;

public record MeetingResultResponse(
	List<OneOnOneMeeting> oneOnOneMeetings,
	List<TwoOnTwoMeeting> twoOnTwoMeetings,
	List<ThreeOnThreeMeeting> threeOnThreeMeetings
) {

}
