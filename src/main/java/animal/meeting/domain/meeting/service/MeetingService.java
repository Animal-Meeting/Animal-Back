package animal.meeting.domain.meeting.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import animal.meeting.domain.meeting.dto.request.ProgressingMeetingRequest;
import animal.meeting.domain.meeting.dto.response.MeetingResultResponse;
import animal.meeting.domain.meeting.entity.MatchingResult;
import animal.meeting.domain.meeting.entity.MeetingGroup;
import animal.meeting.domain.meeting.entity.OneOnOneMeeting;
import animal.meeting.domain.meeting.entity.ProgressingGroup;
import animal.meeting.domain.user.dto.response.UnMatchedUserResponse;
import animal.meeting.domain.user.entity.ResultUser;
import animal.meeting.domain.meeting.entity.ThreeOnThreeMeeting;
import animal.meeting.domain.meeting.entity.TwoOnTwoMeeting;
import animal.meeting.domain.meeting.entity.type.MeetingGroupType;
import animal.meeting.domain.meeting.entity.type.MeetingStatus;
import animal.meeting.domain.meeting.repository.MatchingResultRepository;
import animal.meeting.domain.meeting.repository.OneOnOneRepository;
import animal.meeting.domain.meeting.repository.ThreeOnThreeRepository;
import animal.meeting.domain.meeting.repository.TwoOnTwoRepository;
import animal.meeting.domain.user.entity.UnMatchedUser;
import animal.meeting.domain.user.entity.User;
import animal.meeting.domain.user.entity.type.AnimalType;
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

	@Value("${MATCHING_PASSWORD}")
	private Long matchingPassword;

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

		return MeetingResultResponse.of(femaleList, maleList, matchingResult);
	}

	private String getMeetingGroupIdByUser(User user) {
		MeetingGroupType groupType = user.getGroupType();
		switch (groupType) {
			case ONE_ON_ONE:
				return getOneOnOneGroupByUser(user).getId();
			case TWO_ON_TWO:
				return getTwoOnTwoGroupByUser(user).getId();
			case THREE_ON_THREE:
				return getThreeOnThreeGroupByUser(user).getId();
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
			.findMostRecentByUserIdAndStatus(user.getId(), MeetingStatus.COMPLETED)
			.orElseThrow(() -> new CustomException(ErrorCode.GROUP_NOT_FOUND));
	}

	private TwoOnTwoMeeting getTwoOnTwoGroupByUser(User user) {
		return twoOnTwoRepository
			.findMostRecentByUserIdAndStatus(user.getId(), MeetingStatus.COMPLETED)
			.orElseThrow(() -> new CustomException(ErrorCode.GROUP_NOT_FOUND));
	}

	private ThreeOnThreeMeeting getThreeOnThreeGroupByUser(User user) {
		return threeOnThreeRepository
			.findMostRecentByUserIdAndStatus(user.getId(), MeetingStatus.COMPLETED)
			.orElseThrow(() -> new CustomException(ErrorCode.GROUP_NOT_FOUND));
	}

	public void progressAllMatching(ProgressingMeetingRequest request) {
		checkMeetingProgressPwd(request.password());

		switch (request.groupType()) {
			case ONE_ON_ONE:
				matchSingleMeetingByType(MeetingGroupType.ONE_ON_ONE);
				break;
			case TWO_ON_TWO:
				matchSingleMeetingByType(MeetingGroupType.TWO_ON_TWO);
				break;
			case THREE_ON_THREE:
				matchSingleMeetingByType(MeetingGroupType.THREE_ON_THREE);
				break;
			default:
				throw new CustomException(ErrorCode.INVALID_MEETING_PARAMETERS);
		}
	}

	private void matchSingleMeetingByType(MeetingGroupType groupType) {
		List<? extends MeetingGroup> maleGroups = getMeetingGroupsByType(groupType, Gender.MALE, MeetingStatus.WAITING);
		List<? extends MeetingGroup> femaleGroups = getMeetingGroupsByType(groupType, Gender.FEMALE, MeetingStatus.WAITING);

		// weight값 계산해서 key : female group id, value : male group의 리스트
		Map<String, List<ProgressingGroup>> map = createWeightedGroupMap(femaleGroups, maleGroups);

		List<MatchingResult> matchingResultsToSave = new ArrayList<>();

		for (double standard = groupType.getUserCount() ; standard >= 0 ; standard-=0.5) {
			for (MeetingGroup femaleGroup : femaleGroups) {
				if (femaleGroup.getStatus() != MeetingStatus.WAITING) {
					continue;
				}
				List<ProgressingGroup> progressingGroups = map.get(femaleGroup.getGroupId());
				for (ProgressingGroup elem : progressingGroups) {
					if (standard == elem.getWeightValue()) {
						// null일 때 막기
						MeetingGroup maleGroup = getGroupById(maleGroups, elem.getGroupId());
						if (maleGroup != null
							&& maleGroup.getStatus() == MeetingStatus.WAITING
							&& femaleGroup.getStatus() == MeetingStatus.WAITING) {
							MatchingResult matchingResult = MatchingResult.create(maleGroup.getGroupId(), femaleGroup.getGroupId(), groupType);
							matchingResultsToSave.add(matchingResult);
							femaleGroup.changeStatus(MeetingStatus.COMPLETED);
							maleGroup.changeStatus(MeetingStatus.COMPLETED);
							break;
						}
					}
				}
			}
		}
		matchingResultRepository.saveAll(matchingResultsToSave);
	}

	private List<? extends MeetingGroup> getMeetingGroupsByType(MeetingGroupType groupType, Gender gender, MeetingStatus status) {
		switch (groupType) {
			case ONE_ON_ONE:
				return oneOnOneRepository.findAllByGenderAndStatus(gender, status);
			case TWO_ON_TWO:
				return twoOnTwoRepository.findAllByGenderAndStatus(gender, status);
			case THREE_ON_THREE:
				return threeOnThreeRepository.findAllByGenderAndStatus(gender, status);
			default:
				throw new CustomException(ErrorCode.INVALID_MEETING_PARAMETERS);
		}
	}

	private Map<String, List<ProgressingGroup>> createWeightedGroupMap(List<? extends MeetingGroup> femaleGroups, List<? extends MeetingGroup> maleGroups) {
		Map<String, List<ProgressingGroup>> map = new HashMap<>();

		for (MeetingGroup femaleGroup : femaleGroups) {
			List<User> femaleUsers = femaleGroup.getUserList();
			List<ProgressingGroup> progressingGroupList = new ArrayList<>();

			for (MeetingGroup maleGroup : maleGroups) {
				List<User> maleUsers = maleGroup.getUserList();

				double weightValue = calculateWeight(femaleUsers, maleUsers);
				progressingGroupList.add(new ProgressingGroup(maleGroup.getGroupId(), weightValue));
			}
			map.put(femaleGroup.getGroupId(), progressingGroupList);
		}
		return map;
	}

	private MeetingGroup getGroupById(List<? extends MeetingGroup> groupList, String groupId) {
		Optional<? extends MeetingGroup> matchingGroup = groupList.stream()
			.filter(group -> group.getGroupId().equals(groupId))
			.findFirst();

		return matchingGroup.orElse(null);
	}

	private void checkMeetingProgressPwd(Long password) {
		if (password != matchingPassword.longValue()) {
			throw new CustomException(ErrorCode.MATCHING_PWD_NOT_MATCHED);
		}
	}

	private double calculateWeight(List<User> femaleUsers, List<User> maleUsers) {
		double sum = 0;

		for (User female : femaleUsers) {
			AnimalType firstAnimal = female.getFirstAnimalType();
			AnimalType secondAnimal = female.getSecondAnimalType();
			for (User male : maleUsers) {
				if (firstAnimal == male.getSelfAnimalType()) {
					sum += 1;
					break;
				}
				else if (secondAnimal == male.getSecondAnimalType()) {
					sum += 0.5;
					break;
				}
			}
		}
		return sum;
	}

	public UnMatchedUserResponse getUnmatchedUsers() {

		List<UnMatchedUser> unMatchedResultList = new ArrayList<>();

		for (MeetingGroupType groupType : MeetingGroupType.values()) {
			List<? extends MeetingGroup> meetingGroups = getTodayMeetingGroupsByStatus(groupType, MeetingStatus.WAITING);


			for (MeetingGroup group : meetingGroups) {
				List<User> unMatchedUserList = group.getUserList();

				for (User user : unMatchedUserList) {
					UnMatchedUser unMatchedUser = UnMatchedUser.create(user);
					unMatchedResultList.add(unMatchedUser);
				}
			}
		}
		return new UnMatchedUserResponse(unMatchedResultList);
	}
	private List<? extends MeetingGroup> getTodayMeetingGroupsByStatus(MeetingGroupType groupType, MeetingStatus status) {
		switch (groupType) {
			case ONE_ON_ONE:
				return oneOnOneRepository.findMeetingsByStatusAndCreatedAtToday(status);
			case TWO_ON_TWO:
				return twoOnTwoRepository.findMeetingsByStatusAndCreatedAtToday(status);
			case THREE_ON_THREE:
				return threeOnThreeRepository.findMeetingsByStatusAndCreatedAtToday(status);
			default:
				throw new CustomException(ErrorCode.INVALID_MEETING_PARAMETERS);
		}
	}

	public boolean hasUserMatchedGroup(User user) {
		Optional<? extends MeetingGroup> matchedMeetingGroup = getMeetingGroupByUserAndStatus(user);
		return matchedMeetingGroup.isPresent();
	}

	private Optional<? extends MeetingGroup> getMeetingGroupByUserAndStatus(User user) {
		switch (user.getGroupType()) {
			case ONE_ON_ONE:
				return oneOnOneRepository.findMostRecentByUserIdAndStatus(user.getId(), MeetingStatus.COMPLETED);
			case TWO_ON_TWO:
				return twoOnTwoRepository.findMostRecentByUserIdAndStatus(user.getId(), MeetingStatus.COMPLETED);
			case THREE_ON_THREE:
				return threeOnThreeRepository.findMostRecentByUserIdAndStatus(user.getId(), MeetingStatus.COMPLETED);
			default:
				throw new CustomException(ErrorCode.INVALID_MEETING_PARAMETERS);
		}
	}
}
