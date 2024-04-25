package animal.meeting.global.response;

public record ErrorResponse(String errorClassName, String message) {

	public static ErrorResponse of(String errorClassName, String message) {
		return new ErrorResponse(errorClassName, message);
	}
}
