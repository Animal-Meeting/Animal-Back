package animal.meeting.domain.user.dto.response;

import animal.meeting.domain.user.entity.User;

public record LoginResponse(
	Long id,
	String name,
	String phoneNumber)
{
	public static LoginResponse from(User user) {
		return new LoginResponse(
			user.getId(),
			user.getName(),
			user.getPhoneNumber()
		);
	}
}
