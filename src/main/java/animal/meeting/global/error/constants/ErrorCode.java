package animal.meeting.global.error.constants;

import org.springframework.http.HttpStatus;

import animal.meeting.global.error.ErrorCodeStatus;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ErrorCode implements ErrorCodeStatus {
	// 서버 오류
	METHOD_NOT_ALLOWED(HttpStatus.METHOD_NOT_ALLOWED, "지원하지 않는 HTTP method 입니다."),
	INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "서버 오류, 관리자에게 문의하세요"),
	METHOD_ARGUMENT_TYPE_MISMATCH(HttpStatus.BAD_REQUEST, "Enum Type이 일치하지 않거나 값이 없습니다."),
	REQUESTED_PARAM_NOT_VALIDATE(HttpStatus.BAD_REQUEST, "Reqeust Param 값이 유효하지 않습니다"),
	REQUESTED_VALUE_NOT_VALIDATE(HttpStatus.BAD_REQUEST, "메서드의 인자가 유효하지 않습니다."),
	NOT_FOUND_REQUEST_ADDRESS(HttpStatus.NOT_FOUND, "잘못된 요청 주소입니다."),
	NOT_FOUND_REQUEST_RESOURCE(HttpStatus.NOT_FOUND, "존재하지 않은 요청 주소입니다."),

	// 미팅 오류
	INVALID_MEETING_PARAMETERS(HttpStatus.BAD_REQUEST, "입력된 유저 수와 미팅타입이 일치하지 않습니다."),
	GROUP_NOT_FOUND(HttpStatus.BAD_REQUEST, "유저에 대한 미팅그룹을 찾을 수 없습니다."),
	GROUP_NOT_MATCHED(HttpStatus.BAD_REQUEST, "그룹 정보가 잘못되었습니다."),
	MATCHING_PWD_NOT_MATCHED(HttpStatus.BAD_REQUEST, "패스워드가 올바르지 않습니다."),
	MATCHING_GROUP_NOT_FOUND(HttpStatus.NOT_FOUND ,"매칭된 그룹의 정보가 없습니다."),
	RESULT_NOT_FOUND(HttpStatus.NOT_FOUND ,"매칭 결과 정보가 없습니다."),

	// AWS 오류
	INVALID_MULTIPARTFILE(HttpStatus.BAD_REQUEST, "이미지 파일이 올바르지 않습니다."),

	// 결과 이미지
	DUPLICATED_STUDENT_ID(HttpStatus.BAD_REQUEST, "중복된 학번입니다."),
	// 유저 오류
	DUPLICATED_PHONE_NUMBER(HttpStatus.BAD_REQUEST, "중복된 전화번호가 존재합니다."),
	USER_NOT_FOUND(HttpStatus.NOT_FOUND, "등록되지 않은 회원입니다."),
	;

	private final HttpStatus httpStatus;
	private final String message;
}
