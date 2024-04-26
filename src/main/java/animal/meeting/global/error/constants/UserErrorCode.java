package animal.meeting.global.error.constants;

import org.springframework.http.HttpStatus;

import animal.meeting.global.error.ErrorCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum UserErrorCode implements ErrorCode {

	INVALID_PARAMETER(HttpStatus.BAD_REQUEST, "Invalid parameter included"),
	USER_NOT_FOUND(HttpStatus.NOT_FOUND, "등록되지 않은 회원입니다."),
	;
	private final HttpStatus httpStatus;
	private final String message;
}


