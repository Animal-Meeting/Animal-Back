package animal.meeting.domain.user.dto.request;

import jakarta.validation.constraints.NotBlank;

public record LoginRequest(
	@NotBlank(message = "전화번호를 입력해주세요.") String phoneNumber,
	@NotBlank(message = "이름을 입력해주세요.") String name
) {

}
