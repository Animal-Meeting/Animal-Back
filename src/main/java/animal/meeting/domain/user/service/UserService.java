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
import animal.meeting.domain.user.dto.response.SecretKeyResponse;
import animal.meeting.domain.user.entity.SecretKey;
import animal.meeting.domain.user.entity.User;
import animal.meeting.domain.user.repository.UserRepository;
import animal.meeting.global.error.CustomException;
import animal.meeting.global.error.constants.ErrorCode;
import jakarta.transaction.Transactional;

@Service
@Transactional
public class UserService {
	private final UserRepository userRepository;
	private final MeetingService meetingService;
	private final SecretKey secretKey;

	public UserService(UserRepository userRepository, MeetingService meetingService, SecretKey secretKey) {
		this.userRepository = userRepository;
		this.meetingService = meetingService;
		this.secretKey = secretKey;
	}

	public void registerUserAndMeeting(List<UserRegisterRequest> requests, MeetingGroupType groupType) {

		validateRegistration(requests, groupType);
		List<User> userList = processRegistraion(requests, groupType);
		meetingService.joinMeeting(userList, groupType);
	}

	private List<User> processRegistraion(List<UserRegisterRequest> requests, MeetingGroupType groupType) {
		return requests.stream()
			.map(request -> createUser(request, groupType))
			.toList();
	}

	private User createUser(UserRegisterRequest request, MeetingGroupType groupType) {
		User newUser = User.create(request, groupType);
		return userRepository.save(newUser);
	}

	public LoginResponse login(LoginRequest request) {
		User user =
			userRepository
				.findByNameAndPhoneNumber(request.name(), request.phoneNumber())
				.orElseThrow(() -> new CustomException(ErrorCode.USER_NOT_FOUND));
		return LoginResponse.of(user);
	}

	private void validateRegistration(List<UserRegisterRequest> requests, MeetingGroupType groupType) {
		validateUserCountAndGroupType(requests, groupType);
		validateDupilicatedPhoneNumber(requests);
	}

	private void validateUserCountAndGroupType(List<UserRegisterRequest> requests, MeetingGroupType groupType) {
		int expectedUserCount = groupType.getUserCount();
		int intputUserCount = requests.size();

		if (expectedUserCount != intputUserCount) {
			throw new CustomException(ErrorCode.INVALID_MEETING_PARAMETERS);
		}
	}

	private void validateDupilicatedPhoneNumber(List<UserRegisterRequest> requests) {
		Set<String> phoneNumbers = new HashSet<>();

		for (UserRegisterRequest request : requests) {
			String phoneNumber = request.phoneNumber();

			if (!phoneNumbers.add(phoneNumber)) {
				throw new CustomException(ErrorCode.DUPLICATED_PHONE_NUMBER);
			}
		}
	}

	public SecretKeyResponse checkValidUser(Long inputSecretKey) {
		return SecretKeyResponse.from(secretKey.isValidSecretKey(inputSecretKey));
	}
}
