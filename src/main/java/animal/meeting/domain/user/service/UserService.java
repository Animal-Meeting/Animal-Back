package animal.meeting.domain.user.service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Service;

import animal.meeting.domain.meeting.entity.type.MeetingGroupType;
import animal.meeting.domain.meeting.service.MeetingService;
import animal.meeting.domain.user.dto.request.LoginRequest;
import animal.meeting.domain.user.dto.request.UserRegisterRequest;
import animal.meeting.domain.user.dto.response.LoginResponse;
import animal.meeting.domain.user.entity.User;
import animal.meeting.domain.user.entity.type.UserInfo;
import animal.meeting.domain.user.repository.UserRepository;
import animal.meeting.global.error.CustomException;
import animal.meeting.global.error.constants.ErrorCode;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class UserService {
	private final UserRepository userRepository;
	private final MeetingService meetingService;

	public void registerUserAndMeeting(List<UserRegisterRequest> requests, MeetingGroupType groupType) {

		validateRegistration(requests, groupType);
		List<User> userList = processRegistraion(requests);
		meetingService.joinMeeting(userList, groupType);
	}

	private List<User> processRegistraion(List<UserRegisterRequest> requests) {
		return requests.stream()
			.map(this::createOrUpdateUser)
			.toList();
	}

	private User createOrUpdateUser(UserRegisterRequest request) {
		return userRepository.findByPhoneNumber(request.phoneNumber())
			.map(existingUser -> updateUser(existingUser, request))
			.orElseGet(() -> createUser(request));
	}

	private User createUser(UserRegisterRequest request) {
		User newUser = User.create(request);
		return userRepository.save(newUser);
	}

	private User updateUser(User user, UserRegisterRequest request) {
		UserInfo.SELF_ANIMAL_TYPE.executeUpdate(user, request.selfAnimalType());
		UserInfo.FIRST_ANIMAL_TYPE.executeUpdate(user, request.firstAnimalType());
		UserInfo.SECOND_ANIMAL_TYPE.executeUpdate(user, request.secondAnimalType());
		return userRepository.save(user);
	}

	public LoginResponse login(LoginRequest request) {
		User user =
			userRepository
				.findByPhoneNumber(request.phoneNumber())
				.orElseThrow(() -> new CustomException(ErrorCode.USER_NOT_FOUND));

		return LoginResponse.of(user);
	}

	private void validateRegistration(List<UserRegisterRequest> requests, MeetingGroupType groupType) {
		validateUserCountAndGroupType(requests, groupType);
		validatePhoneNumber(requests);
	}

	private void validateUserCountAndGroupType(List<UserRegisterRequest> requests, MeetingGroupType groupType) {
		int expectedUserCount = groupType.getUserCount();
		int intputUserCount = requests.size();

		if (expectedUserCount != intputUserCount) {
			throw new CustomException(ErrorCode.INVALID_MEETING_PARAMETERS);
		}
	}

	private void validatePhoneNumber(List<UserRegisterRequest> requests) {
		Set<String> phoneNumbers = new HashSet<>();

		for (UserRegisterRequest request : requests) {
			String phoneNumber = request.phoneNumber();

			if (!phoneNumbers.add(phoneNumber)) {
				throw new CustomException(ErrorCode.DUPLICATED_PHONE_NUMBER);
			}
		}
	}
}
