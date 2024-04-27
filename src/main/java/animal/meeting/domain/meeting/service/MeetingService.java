package animal.meeting.domain.meeting.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import animal.meeting.domain.meeting.entity.MeetingGroup;
import animal.meeting.domain.meeting.entity.OneOnOneMeeting;
import animal.meeting.domain.meeting.entity.ThreeOnThreeMeeting;
import animal.meeting.domain.meeting.entity.TwoOnTwoMeeting;
import animal.meeting.domain.meeting.entity.type.MeetingGroupType;
import animal.meeting.domain.meeting.entity.type.MeetingStatus;
import animal.meeting.domain.meeting.repository.OneOnOneRepository;
import animal.meeting.domain.meeting.repository.ThreeOnThreeRepository;
import animal.meeting.domain.meeting.repository.TwoOnTwoRepository;
import animal.meeting.domain.user.entity.User;
import animal.meeting.domain.user.entity.type.Gender;
import animal.meeting.global.error.CustomException;
import animal.meeting.global.error.constants.ErrorCode;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class MeetingService {
	private final OneOnOneRepository oneOnOneRepository;
	private final TwoOnTwoRepository twoOnTwoRepository;
	private final ThreeOnThreeRepository threeOnThreeRepository;

	public void joinMeeting(List<User> userList, MeetingGroupType groupType) {

		Gender groupGender = getFirstUserGender(userList);
		MeetingGroup meeting = createMeetingGroupByType(groupType, groupGender);

		meeting.addUser(userList);
		saveMeeting(meeting);
	}

	private Gender getFirstUserGender(List<User> userList) {
		return userList.get(0).getGender();
	}

	private MeetingGroup createMeetingGroupByType(MeetingGroupType groupType, Gender groupGender) {
		switch (groupType) {
			case ONE_ON_ONE:
				return OneOnOneMeeting.create(groupGender);
			case TWO_ON_TWO:
				return TwoOnTwoMeeting.create(groupGender);
			case THREE_ON_THREE:
				return ThreeOnThreeMeeting.create(groupGender);
			default:
				throw new CustomException(ErrorCode.INVALID_MEETING_PARAMETERS);
		}
	}

	private void saveMeeting(MeetingGroup meeting) {
		if (meeting instanceof OneOnOneMeeting oneOnOneMeeting) {
			oneOnOneRepository.save(oneOnOneMeeting);
		} else if (meeting instanceof TwoOnTwoMeeting twoOnTwoMeeting) {
			twoOnTwoRepository.save(twoOnTwoMeeting);
		} else if (meeting instanceof ThreeOnThreeMeeting threeOnThreeMeeting) {
			threeOnThreeRepository.save(threeOnThreeMeeting);
		}
	}


	public List<MeetingGroup> getMeetingResultList(Long userId) {
		List<OneOnOneMeeting> oneOnOneMeetings = oneOnOneRepository.findByUserIdAndStatus(userId, MeetingStatus.COMPLETED);
		List<TwoOnTwoMeeting> twoOnTwoMeetings = twoOnTwoRepository.findByUserIdAndStatus(userId, MeetingStatus.COMPLETED);
		List<ThreeOnThreeMeeting> threeOnThreeMeetings = threeOnThreeRepository.findByUserIdAndStatus(userId, MeetingStatus.WAITING);

		List<MeetingGroup> meetings = new ArrayList<>();
		meetings.addAll(oneOnOneMeetings);
		meetings.addAll(twoOnTwoMeetings);
		meetings.addAll(threeOnThreeMeetings);

		return meetings;
	}
}
