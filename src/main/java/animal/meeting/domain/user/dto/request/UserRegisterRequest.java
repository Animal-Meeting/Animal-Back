package animal.meeting.domain.user.dto.request;

import animal.meeting.domain.user.entity.type.AnimalType;
import animal.meeting.domain.user.entity.type.Gender;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Builder;

@Builder
public record UserRegisterRequest(

	@NotBlank(message = "이름은 비워둘 수 없습니다.")
	@Size(max = 30, message = "이름은 30자 이하로 작성해 주세요.")
	String name,
	@NotBlank(message = "전화번호는 비워둘 수 없습니다.")
	@Pattern(regexp = "^010\\d{8}$", message = "휴대폰 번호를 정확히 입력해 주세요.")
	String phoneNumber,
	@NotNull(message = "성별을 선택해 주세요.")
	Gender gender,
	@NotNull(message = "선호하는 1순위 동물상을 선택해 주세요.")
	AnimalType firstAnimalType,
	@NotNull(message = "선호하는 2순위 동물상을 선택해 주세요.")
	AnimalType secondAnimalType,
	@NotNull(message = "본인의 동물상을 선택해 주세요.")
	AnimalType selfAnimalType
) {
}
