package animal.meeting.global.error;

import java.io.Serializable;

import lombok.Getter;

@Getter
public class CustomException extends RuntimeException implements Serializable {

	private final transient ErrorCode errorCode;

	public CustomException(ErrorCode errorCode) {
		super(errorCode.getMessage());
		this.errorCode = errorCode;
	}
}