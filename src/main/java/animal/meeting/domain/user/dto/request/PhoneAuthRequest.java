package animal.meeting.domain.user.dto.request;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public record PhoneAuthRequest(

	@NotNull(message = "인증번호를 입력해 주세요")
	@Min(1000)
	@Max(9999)
	int authKey
) {

}
