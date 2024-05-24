package animal.meeting.domain.meeting.dto.response;

import java.util.List;

import animal.meeting.domain.meeting.entity.MatchingResult;
import animal.meeting.domain.user.entity.ResultUser;
import animal.meeting.domain.meeting.entity.type.MeetingGroupType;

public record MeetingResultResponse(
	List<ResultUser> femaleUsers,
	List<ResultUser> maleUsers,
	String kakaoLink,
	MeetingGroupType meetingGroupType

) {
	public static MeetingResultResponse of(
		List<ResultUser> femaleUsers,
		List<ResultUser> maleUsers,
		MatchingResult matchingResult) {
		return new MeetingResultResponse(
			femaleUsers,
			maleUsers,
			matchingResult.getKakaoLink(),
			matchingResult.getMeetingType()
		);
	}

}
