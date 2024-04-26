package animal.meeting.global.error.handler;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.lang.Nullable;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import org.springframework.web.servlet.resource.NoResourceFoundException;

import animal.meeting.global.error.ErrorCode;
import animal.meeting.global.error.constants.CommonErrorCode;
import animal.meeting.global.response.ErrorResponse;
import animal.meeting.global.response.GlobalResponse;
import lombok.extern.slf4j.Slf4j;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

	@Override
	protected ResponseEntity<Object> handleExceptionInternal(
		Exception ex,
		Object body,
		HttpHeaders headers,
		HttpStatusCode statusCode,
		WebRequest request) {

		ErrorResponse errorResponse = ErrorResponse.of(ex.getClass().getSimpleName(), ex.getMessage());
		return super.handleExceptionInternal(ex, errorResponse, headers, statusCode, request);
	}

	/**
	 * enum 타입 String의 값을 잘못입력 햇을 때
	 */
	@Override
	protected ResponseEntity<Object> handleHttpMessageNotReadable(
		HttpMessageNotReadableException ex,
		HttpHeaders headers,
		HttpStatusCode status,
		WebRequest request) {

		log.error("HttpMessageNotReadableException : {}", ex.getMessage(), ex);
		final ErrorCode errorCode = CommonErrorCode.METHOD_ARGUMENT_TYPE_MISMATCH;
		final ErrorResponse errorResponse = ErrorResponse.of(ex.getClass().getSimpleName(), errorCode.getMessage());
		final GlobalResponse response = GlobalResponse.fail(errorCode.getHttpStatus().value(), errorResponse);
		return ResponseEntity.status(errorCode.getHttpStatus()).body(response);
	}

	/**
	 * 메소드 인자가 유효하지 않을 때 클라이언트에게 HTTP 400 상태 코드(Bad Request)를 반환.
	 * 주로 Valid, @valiated 에러시 발생
	 */
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(
		MethodArgumentNotValidException ex,
		HttpHeaders headers,
		HttpStatusCode status,
		WebRequest request) {

		log.error("MethodArgumentNotValidException : {}", ex.getMessage(), ex);
		String errorMessage = ex.getBindingResult().getAllErrors().get(0).getDefaultMessage();
		final ErrorResponse errorResponse = ErrorResponse.of(ex.getClass().getSimpleName(), errorMessage);
		final GlobalResponse response = GlobalResponse.fail(status.value(), errorResponse);
		return ResponseEntity.status(status).body(response);
	}

	/**
	 * 지원되지 않는 HTTP 요청 메서드를 사용했을 때 클라이언트에게 HTTP 405 상태 코드(Method Not Allowed)를 반환
	 */
	@Override
	protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(
		HttpRequestMethodNotSupportedException ex,
		HttpHeaders headers,
		HttpStatusCode status,
		WebRequest request) {

		log.error("HttpRequestMethodNotSupportedException : {}", ex.getMessage(), ex);
		final ErrorCode errorCode = CommonErrorCode.METHOD_NOT_ALLOWED;
		final ErrorResponse errorResponse = ErrorResponse.of(ex.getClass().getSimpleName(), errorCode.getMessage());
		final GlobalResponse response = GlobalResponse.fail(errorCode.getHttpStatus().value(), errorResponse);
		return ResponseEntity.status(errorCode.getHttpStatus()).body(response);
	}

	/**
	 *  요청 주소가 없는 주소일 경우 404 상태코드(Not Found)
	 */
	@Override
	protected ResponseEntity<Object> handleNoHandlerFoundException(
		NoHandlerFoundException ex,
		HttpHeaders headers,
		HttpStatusCode status,
		WebRequest request) {

		log.error("NoHandlerFoundException : {}", ex.getMessage(), ex);
		final ErrorCode errorCode = CommonErrorCode.NOT_FOUND_REQUEST_ADDRESS;
		final ErrorResponse errorResponse = ErrorResponse.of(ex.getClass().getSimpleName(), errorCode.getMessage());
		final GlobalResponse response = GlobalResponse.fail(errorCode.getHttpStatus().value(), errorResponse);
		return ResponseEntity.status(errorCode.getHttpStatus()).body(response);
	}

	/**
	 * 요청 리소스가 없을 경우
	 */
	@Override
	protected ResponseEntity<Object> handleNoResourceFoundException(
		NoResourceFoundException ex,
		HttpHeaders headers,
		HttpStatusCode status,
		WebRequest request) {

		log.error("NoResourceFoundException : {}", ex.getMessage(), ex);
		final ErrorCode errorCode = CommonErrorCode.NOT_FOUND_REQUEST_RESOURCE;
		final ErrorResponse errorResponse = ErrorResponse.of(ex.getClass().getSimpleName(), errorCode.getMessage());
		final GlobalResponse response = GlobalResponse.fail(errorCode.getHttpStatus().value(), errorResponse);
		return ResponseEntity.status(errorCode.getHttpStatus()).body(response);
	}

	/**
	 * @RequestParam의 값이 없을 때
	 */
	@Override
	protected ResponseEntity<Object> handleMissingServletRequestParameter(
		MissingServletRequestParameterException ex,
		HttpHeaders headers,
		HttpStatusCode status,
		WebRequest request) {

		log.error("MissingServletRequestParameterException : {}", ex.getMessage(), ex);

		final ErrorCode errorCode = CommonErrorCode.REQUESTED_PARAM_NOT_VALIDATE;
		final ErrorResponse errorResponse = ErrorResponse.of(ex.getClass().getSimpleName(), errorCode.getMessage());
		final GlobalResponse response = GlobalResponse.fail(errorCode.getHttpStatus().value(), errorResponse);
		return ResponseEntity.status(errorCode.getHttpStatus()).body(response);
	}

}
