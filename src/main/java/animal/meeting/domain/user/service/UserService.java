package animal.meeting.domain.user.service;

import org.springframework.stereotype.Service;

import animal.meeting.domain.user.dto.request.UserRegisterRequest;
import animal.meeting.domain.user.entity.User;
import animal.meeting.domain.user.entity.type.UserInfo;
import animal.meeting.domain.user.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class UserService {
	private final UserRepository userRepository;

	public void registerUser(UserRegisterRequest request) {
		User user =
			userRepository
				.findByPhoneNumber(request.phoneNumber())
				.orElse(null);

		if (user == null) {
			User newUser = User.create(request);
			userRepository.save(newUser);
		} else {
			updateUser(user, request);
		}
	}

	private void updateUser(User user, UserRegisterRequest request) {
		UserInfo.SELF_ANIMAL_TYPE.executeUpdate(user, request.selfAnimalType());
		UserInfo.FIRST_ANIMAL_TYPE.executeUpdate(user, request.firstAnimalType());
		UserInfo.SECOND_ANIMAL_TYPE.executeUpdate(user, request.secondAnimalType());
	}
}
