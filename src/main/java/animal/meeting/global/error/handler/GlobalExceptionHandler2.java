package animal.meeting.global.error.handler;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import animal.meeting.global.error.CustomException;
import animal.meeting.global.error.ErrorCode;
import animal.meeting.global.error.constants.CommonErrorCode;
import animal.meeting.global.response.ErrorResponse;
import animal.meeting.global.response.GlobalResponse;
import jakarta.validation.ConstraintViolationException;
import lombok.extern.slf4j.Slf4j;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler2 {

	/** CustomException 예외 처리 */
	@ExceptionHandler(CustomException.class)
	public ResponseEntity<GlobalResponse> handleCustomException(CustomException ex) {

		log.error("CustomException : {}", ex.getMessage(), ex);
		final ErrorCode errorCode = ex.getErrorCode();
		final ErrorResponse errorResponse = ErrorResponse.of(errorCode.name(), errorCode.getMessage());
		final GlobalResponse response = GlobalResponse.fail(errorCode.getHttpStatus().value(), errorResponse);
		return ResponseEntity.status(errorCode.getHttpStatus()).body(response);
	}

	/** 500번대 에러 처리 */
	@ExceptionHandler(Exception.class)
	protected ResponseEntity<GlobalResponse> handleException(Exception ex) {

		log.error("Internal Server Error : {}", ex.getMessage(), ex);
		final ErrorCode internalServerError = CommonErrorCode.INTERNAL_SERVER_ERROR;
		final ErrorResponse errorResponse = ErrorResponse.of(ex.getClass().getSimpleName(), internalServerError.getMessage());
		final GlobalResponse response = GlobalResponse.fail(internalServerError.getHttpStatus().value(), errorResponse);
		return ResponseEntity.status(internalServerError.getHttpStatus()).body(response);
	}

	/** enum type 일치하지 않아 binding 못할 경우 발생 주로 @RequestParam enum으로 binding 못했을 경우 발생 */
	@ExceptionHandler(MethodArgumentTypeMismatchException.class)
	protected ResponseEntity<GlobalResponse> handleMethodArgumentTypeMismatchException(
		MethodArgumentTypeMismatchException ex) {

		log.error("MethodArgumentTypeMismatchException : {}", ex.getMessage(), ex);
		final ErrorCode errorCode = CommonErrorCode.METHOD_ARGUMENT_TYPE_MISMATCH;
		final ErrorResponse errorResponse = ErrorResponse.of(ex.getClass().getSimpleName(), errorCode.getMessage());
		final GlobalResponse response = GlobalResponse.fail(errorCode.getHttpStatus().value(), errorResponse);
		return ResponseEntity.status(errorCode.getHttpStatus()).body(response);
	}

	/** Request Param Validation 예외 처리 */
	@ExceptionHandler(ConstraintViolationException.class)
	public ResponseEntity<GlobalResponse> handleConstraintViolationException(
		ConstraintViolationException e) {
		log.error("ConstraintViolationException : {}", e.getMessage(), e);

		Map<String, Object> bindingErrors = new HashMap<>();
		e.getConstraintViolations()
			.forEach(
				constraintViolation -> {
					List<String> propertyPath =
						List.of(
							constraintViolation
								.getPropertyPath()
								.toString()
								.split("\\."));
					String path =
						propertyPath.stream()
							.skip(propertyPath.size() - 1L)
							.findFirst()
							.orElse(null);
					bindingErrors.put(path, constraintViolation.getMessage());
				});

		final ErrorResponse errorResponse = ErrorResponse.of(e.getClass().getSimpleName(), bindingErrors.toString());
		final GlobalResponse response = GlobalResponse.fail(HttpStatus.BAD_REQUEST.value(), errorResponse);
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
	}

	// @valid 유효성 검증에 실패했을 경우 발생하는 예외 처리
	@ExceptionHandler(MethodArgumentNotValidException.class)
	protected ResponseEntity<GlobalResponse> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
		log.error("handleMethodArgumentNotValidException", ex);
		BindingResult bindingResult = ex.getBindingResult();
		StringBuilder stringBuilder = new StringBuilder();
		for (FieldError fieldError : bindingResult.getFieldErrors()) {
			stringBuilder.append(fieldError.getField()).append(":");
			stringBuilder.append(fieldError.getDefaultMessage());
			stringBuilder.append(", ");
		}
		final ErrorResponse a = ErrorResponse.of(String.valueOf(CommonErrorCode.METHOD_NOT_ALLOWED), String.valueOf(stringBuilder));
		final GlobalResponse response = GlobalResponse.fail(HttpStatus.BAD_REQUEST.value(), a);
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
	}

}
