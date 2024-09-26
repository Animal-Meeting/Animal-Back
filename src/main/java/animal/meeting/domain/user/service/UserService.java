package animal.meeting.domain.user.service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import animal.meeting.domain.meeting.entity.type.MeetingGroupType;
import animal.meeting.domain.meeting.service.MeetingService;
import animal.meeting.domain.user.dto.request.NewUserRegisterRequest;
import animal.meeting.domain.user.dto.request.PhoneAuthRequest;
import animal.meeting.domain.user.dto.request.PhoneNumberRequest;
import animal.meeting.domain.user.dto.response.ParticipantResponse;
import animal.meeting.domain.user.dto.response.SecretKeyResponse;
import animal.meeting.domain.user.entity.SecretKey;
import animal.meeting.domain.user.entity.User;
import animal.meeting.domain.user.entity.type.Gender;
import animal.meeting.domain.user.repository.UserRepository;
import animal.meeting.global.error.CustomException;
import animal.meeting.global.error.constants.ErrorCode;
import animal.meeting.global.sms.SmsService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class UserService {
	private final UserRepository userRepository;
	private final MeetingService meetingService;
	private final SecretKey secretKey;
	private final SmsService smsService;

	/**
	 * 유저 미팅 등록
	 */
	public void registerUserAndMeeting(List<NewUserRegisterRequest> requests, MeetingGroupType groupType) {
		validateRegistration(requests, groupType);
		List<User> userList = createUser(requests, groupType);
		meetingService.joinMeeting(userList, groupType);
	}

	private List<User> createUser(List<NewUserRegisterRequest> requests, MeetingGroupType groupType) {
		List<User> userList =  requests.stream()
			.map(request -> User.create(request, groupType))
			.toList();
		return userRepository.saveAll(userList);
	}

	/**
	 * 회원가입 가능 여부 검사
	 **/
	private void validateRegistration(List<NewUserRegisterRequest> requests, MeetingGroupType groupType) {
		validateUserCountAndGroupType(requests, groupType);
		validateDupilicatedPhoneNumber(requests);
	}

	/**
	 * 인원수 유효성 검사
	 **/
	private void validateUserCountAndGroupType(List<NewUserRegisterRequest> requests, MeetingGroupType groupType) {
		int expectedUserCount = groupType.getUserCount();
		int intputUserCount = requests.size();

		if (expectedUserCount != intputUserCount) {
			throw new CustomException(ErrorCode.INVALID_MEETING_PARAMETERS);
		}
	}

	/**
	 * 그룹 내 휴대번호 중복 검사
	 **/
	private void validateDupilicatedPhoneNumber(List<NewUserRegisterRequest> requests) {
		Set<String> phoneNumbers = requests.stream()
			.map(NewUserRegisterRequest::phoneNumber)
			.collect(Collectors.toSet());

		if (phoneNumbers.size() != requests.size()) {
			throw new CustomException(ErrorCode.DUPLICATED_PHONE_NUMBER);
		}
	}

	public ParticipantResponse getParticipantCountForToday() {
		Long manCount = userRepository.countByGenderAndCreatedAtToday(Gender.MALE);
		Long girlCount =  userRepository.countByGenderAndCreatedAtToday(Gender.FEMALE);

		return ParticipantResponse.of(manCount, girlCount);
	}

	public SecretKeyResponse checkPhoneVerification(PhoneAuthRequest request) {
		return SecretKeyResponse.from(secretKey.isValidSecretKey(request.authKey()));
	}

	public void requestVarificationCode(PhoneNumberRequest request) {
		smsService.sendAuthCode(request.phoneNumber());
	}

}
