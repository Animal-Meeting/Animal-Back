package animal.meeting.global.error.constants;

import org.springframework.http.HttpStatus;

import animal.meeting.global.error.ErrorCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum MeetingErrorCode implements ErrorCode {
	INVALID_MEETING_PARAMETERS(HttpStatus.BAD_REQUEST, "입력된 유저 수와 미팅타입이 일치하지 않습니다."),
	;
	private final HttpStatus httpStatus;
	private final String message;
}
