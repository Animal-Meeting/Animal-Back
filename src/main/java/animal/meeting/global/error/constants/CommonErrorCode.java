package animal.meeting.global.error.constants;

import org.springframework.http.HttpStatus;

import animal.meeting.global.error.ErrorCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum CommonErrorCode implements ErrorCode {
	METHOD_NOT_ALLOWED(HttpStatus.METHOD_NOT_ALLOWED, "지원하지 않는 HTTP method 입니다."),
	INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "서버 오류, 관리자에게 문의하세요"),
	METHOD_ARGUMENT_TYPE_MISMATCH(HttpStatus.BAD_REQUEST, "Enum Type이 일치하지 않아 Binding에 실패하였습니다."),
	REQUESTED_PARAM_NOT_VALIDATE(HttpStatus.BAD_REQUEST, "Reqeust Param 값이 유효하지 않습니다"),
	NOT_FOUND_REQUEST_ADDRESS(HttpStatus.NOT_FOUND, "잘못된 요청 주소입니다."),
	NOT_FOUND_REQUEST_RESOURCE(HttpStatus.NOT_FOUND, "존재하지 않은 요청 주소입니다."),
	;
	private final HttpStatus httpStatus;
	private final String message;
}
