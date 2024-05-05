package animal.meeting.domain.meeting.service;

import java.util.List;

import org.springframework.stereotype.Service;

import animal.meeting.domain.meeting.dto.response.MeetingResultResponse;
import animal.meeting.domain.meeting.entity.MatchingResult;
import animal.meeting.domain.meeting.entity.MeetingGroup;
import animal.meeting.domain.meeting.entity.OneOnOneMeeting;
import animal.meeting.domain.meeting.entity.ResultUser;
import animal.meeting.domain.meeting.entity.ThreeOnThreeMeeting;
import animal.meeting.domain.meeting.entity.TwoOnTwoMeeting;
import animal.meeting.domain.meeting.entity.type.MeetingGroupType;
import animal.meeting.domain.meeting.entity.type.MeetingStatus;
import animal.meeting.domain.meeting.repository.MatchingResultRepository;
import animal.meeting.domain.meeting.repository.OneOnOneRepository;
import animal.meeting.domain.meeting.repository.ThreeOnThreeRepository;
import animal.meeting.domain.meeting.repository.TwoOnTwoRepository;
import animal.meeting.domain.user.entity.User;
import animal.meeting.domain.user.entity.type.Gender;
import animal.meeting.domain.user.repository.UserRepository;
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
	private final MatchingResultRepository matchingResultRepository;
	private final UserRepository userRepository;

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

	public MeetingResultResponse getMeetingResultList(Long userId) {
		User user = userRepository.findById(userId)
			.orElseThrow(() -> new CustomException(ErrorCode.USER_NOT_FOUND));

		String myMeetingGroupId = getMeetingGroupIdByUser(user);
		MatchingResult matchingResult = getMatchingResult(myMeetingGroupId);
		String otherMeetingGroupId = getOtherMeetingGroupId(matchingResult, myMeetingGroupId);

		List<ResultUser> femaleList = getMatchedParticipantsDetails(myMeetingGroupId, user.getGroupType());
		List<ResultUser> maleList = getMatchedParticipantsDetails(otherMeetingGroupId, user.getGroupType());

		if (user.getGender() == Gender.MALE) {
			List<ResultUser> temp = femaleList;
			femaleList = maleList;
			maleList = temp;
		}

		return MeetingResultResponse.from(femaleList, maleList, matchingResult);
	}

	private String getMeetingGroupIdByUser(User user) {
		MeetingGroupType groupType = user.getGroupType();
		switch (groupType) {
			case ONE_ON_ONE:
				return getOneOnOneGroupId(user);
			case TWO_ON_TWO:
				return getTwoOnTwoGroupId(user);
			case THREE_ON_THREE:
				return geThreeOnThreeGroupId(user);
			default:
				throw new CustomException(ErrorCode.GROUP_NOT_MATCHED);
		}
	}

	private MatchingResult getMatchingResult(String groupId) {
		return matchingResultRepository.findByGroupId(groupId)
			.orElseThrow(() -> new CustomException(ErrorCode.GROUP_NOT_FOUND));
	}

	private List<ResultUser> getMatchedParticipantsDetails(String groupId, MeetingGroupType groupType) {
		switch (groupType) {
			case ONE_ON_ONE:
				return getOneOnOneUserDetails(groupId);
			case TWO_ON_TWO:
				return getTwoOnTwoUserDetails(groupId);
			case THREE_ON_THREE:
				return getThreeOnThreeDetails(groupId);
			default:
				throw new CustomException(ErrorCode.GROUP_NOT_MATCHED);
		}
	}

	private List<ResultUser> getOneOnOneUserDetails(String groupId) {
		OneOnOneMeeting oneOnOneMeeting = oneOnOneRepository.findById(groupId)
			.orElseThrow(() -> new CustomException(ErrorCode.GROUP_NOT_FOUND));

		ResultUser resultUser1 = ResultUser.create(oneOnOneMeeting.getUser1());

		return List.of(resultUser1);
	}

	private List<ResultUser> getTwoOnTwoUserDetails(String groupId) {
		TwoOnTwoMeeting twoOnTwoMeeting = twoOnTwoRepository.findById(groupId)
			.orElseThrow(() -> new CustomException(ErrorCode.GROUP_NOT_FOUND));

		ResultUser resultUser1 = ResultUser.create(twoOnTwoMeeting.getUser1());
		ResultUser resultUser2 = ResultUser.create(twoOnTwoMeeting.getUser2());

		return List.of(resultUser1, resultUser2);
	}

	private List<ResultUser> getThreeOnThreeDetails(String groupId) {
		ThreeOnThreeMeeting threeOnThreeMeeting = threeOnThreeRepository.findById(groupId)
			.orElseThrow(() -> new CustomException(ErrorCode.GROUP_NOT_FOUND));

		ResultUser resultUser1 = ResultUser.create(threeOnThreeMeeting.getUser1());
		ResultUser resultUser2 = ResultUser.create(threeOnThreeMeeting.getUser2());
		ResultUser resultUser3 = ResultUser.create(threeOnThreeMeeting.getUser3());

		return List.of(resultUser1, resultUser2, resultUser3);
	}

	private String getOtherMeetingGroupId(MatchingResult matchingResult, String myMeetingGroupId) {
		if (matchingResult.getGirlGroupId().equals(myMeetingGroupId)) {
			return matchingResult.getManGroupId();
		}
		return matchingResult.getGirlGroupId();
	}

	private OneOnOneMeeting getOneOnOneGroupByUser(User user) {
		return oneOnOneRepository
			.findMostRecentTodayByUserIdAndStatus(user.getId(), MeetingStatus.COMPLETED)
			.orElseThrow(() -> new CustomException(ErrorCode.GROUP_NOT_FOUND));
	}

	private String getOneOnOneGroupId(User user) {
		OneOnOneMeeting oneOnOneMeeting = getOneOnOneGroupByUser(user);
		return oneOnOneMeeting.getId();
	}

	private TwoOnTwoMeeting getTwoOnTwoGroupByUser(User user) {
		return twoOnTwoRepository
			.findMostRecentTodayByUserIdAndStatus(user.getId(), MeetingStatus.COMPLETED)
			.orElseThrow(() -> new CustomException(ErrorCode.GROUP_NOT_FOUND));
	}

	private String getTwoOnTwoGroupId(User user) {
		TwoOnTwoMeeting twoOnTwoMeeting = getTwoOnTwoGroupByUser(user);
		return twoOnTwoMeeting.getId();
	}

	private ThreeOnThreeMeeting getThreeOnThreeGroupByUser(User user) {
		return threeOnThreeRepository
			.findMostRecentTodayByUserIdAndStatus(user.getId(), MeetingStatus.COMPLETED)
			.orElseThrow(() -> new CustomException(ErrorCode.GROUP_NOT_FOUND));
	}

	private String geThreeOnThreeGroupId(User user) {
		ThreeOnThreeMeeting threeOnThreeMeeting = getThreeOnThreeGroupByUser(user);
		return threeOnThreeMeeting.getId();
	}
}
