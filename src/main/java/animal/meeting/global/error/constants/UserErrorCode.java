package animal.meeting.global.error.constants;

import org.springframework.http.HttpStatus;

import animal.meeting.global.error.ErrorCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum UserErrorCode implements ErrorCode {

	DUPLICATED_NAME_AND_PHONE_NUMBER(HttpStatus.BAD_REQUEST, "이름과 전화번호가 중복입니다."),
	DUPLICATED_PHONE_NUMBER(HttpStatus.BAD_REQUEST, "중복된 전화번호가 존재합니다."),
	USER_NOT_FOUND(HttpStatus.NOT_FOUND, "등록되지 않은 회원입니다."),
	;
	private final HttpStatus httpStatus;
	private final String message;
}


