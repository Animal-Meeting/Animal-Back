package animal.meeting.domain.user.dto.response;

public record SecretKeyResponse(Boolean isPassed) {
	public static SecretKeyResponse from(Boolean isPassed) {
		return new SecretKeyResponse(isPassed);
	}
}
