package animal.meeting.domain.meeting.dto.request;

import animal.meeting.domain.meeting.entity.type.MeetingGroupType;

public record ProgressingMeetingRequest(
	Long password,
	MeetingGroupType groupType
) {
}
