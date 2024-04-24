package animal.meeting.domain.user.entity.type;

import java.util.function.Function;

import animal.meeting.domain.user.dto.request.UserRegisterRequest;
import animal.meeting.domain.user.entity.User;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum UserInfo {

	SELF_ANIMAL_TYPE(request -> request.selfAnimalType()) {
		@Override
		protected void update(User user, AnimalType value) {
			user.changeSelfAnimalType(value);
		}
	},
	FIRST_ANIMAL_TYPE(request -> request.firstAnimalType()) {
		@Override
		protected void update(User user, AnimalType value) {
			user.changeFirstAnimalType(value);
		}
	},
	SECOND_ANIMAL_TYPE(request -> request.secondAnimalType()) {
		@Override
		protected void update(User user, AnimalType value) {
			user.changeSecondAnimalType(value);
		}
	};
	protected abstract void update(User user, AnimalType value);

	private final Function<UserRegisterRequest, AnimalType> valueExtractor;

	public void executeUpdate(User user, AnimalType value) {
		if (value != null) {
			update(user, value);
		}
	}
}


