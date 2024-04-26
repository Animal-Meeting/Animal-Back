package animal.meeting.domain.user.service;

import org.springframework.stereotype.Service;

import animal.meeting.domain.user.dto.request.LoginRequest;
import animal.meeting.domain.user.dto.request.UserRegisterRequest;
import animal.meeting.domain.user.dto.response.LoginResponse;
import animal.meeting.domain.user.entity.User;
import animal.meeting.domain.user.entity.type.UserInfo;
import animal.meeting.domain.user.repository.UserRepository;
import animal.meeting.global.error.CustomException;
import animal.meeting.global.error.constants.UserErrorCode;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class UserService {
	private final UserRepository userRepository;

	public void registerUser(UserRegisterRequest request) {
		userRepository.findByPhoneNumber(request.phoneNumber())
			.ifPresentOrElse(
				user -> updateUser(user, request),
				() -> createUser(request)
			);
	}

	private void createUser(UserRegisterRequest request) {
		User newUser = User.create(request);
		userRepository.save(newUser);
	}

	private void updateUser(User user, UserRegisterRequest request) {
		UserInfo.SELF_ANIMAL_TYPE.executeUpdate(user, request.selfAnimalType());
		UserInfo.FIRST_ANIMAL_TYPE.executeUpdate(user, request.firstAnimalType());
		UserInfo.SECOND_ANIMAL_TYPE.executeUpdate(user, request.secondAnimalType());
	}

	public LoginResponse login(LoginRequest request) {
		User user =
			userRepository
				.findByPhoneNumber(request.phoneNumber())
				.orElseThrow(() -> new CustomException(UserErrorCode.USER_NOT_FOUND));

		return LoginResponse.of(user);
	}
}
