package animal.meeting.domain.user.dto.response;

import animal.meeting.domain.user.entity.User;
import animal.meeting.domain.user.entity.type.Gender;

public record LoginResponse(
	Long id,
	String name,
	String phoneNumber,
	Gender gender
	)
{
	public static LoginResponse from(User user) {
		return new LoginResponse(
			user.getId(),
			user.getName(),
			user.getPhoneNumber(),
			user.getGender()
		);
	}
}
