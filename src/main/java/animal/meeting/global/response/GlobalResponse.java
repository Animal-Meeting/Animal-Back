package animal.meeting.global.response;

import java.time.LocalDateTime;

public record GlobalResponse(boolean success, int status, Object data, LocalDateTime timestamp) {
	public static GlobalResponse success(int status, Object data) {
		return new GlobalResponse(true, status, data, LocalDateTime.now().withNano(0));
	}
}

