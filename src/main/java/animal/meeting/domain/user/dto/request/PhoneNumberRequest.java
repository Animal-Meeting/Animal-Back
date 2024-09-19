package animal.meeting.domain.user.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record PhoneNumberRequest(
	@NotBlank(message = "전화번호는 비워둘 수 없습니다.")
	@Pattern(regexp = "^010\\d{8}$", message = "휴대폰 번호를 정확히 입력해 주세요.")
	String phoneNumber
) {
}
