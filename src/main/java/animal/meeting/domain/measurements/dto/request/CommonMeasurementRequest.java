package animal.meeting.domain.measurements.dto.request;

import org.springframework.web.multipart.MultipartFile;

import animal.meeting.domain.user.entity.type.AnimalType;
import animal.meeting.domain.user.entity.type.Gender;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public abstract class CommonMeasurementRequest {
	@NotBlank(message = "고유학번을 입력해주세요.") String studentId;
	@NotNull(message = "대표 동물상을 입력해주세요.") AnimalType animalType;
	@NotNull(message = "성별을 입력해주세요.") Gender gender;
	@NotNull(message = "강아지 점수를 입력해주세요.") Integer dogScore;
	@NotNull(message = "고양이 점수를 입력해주세요.") Integer catScore;
	@NotNull(message = "토끼 점수를 입력해주세요.") Integer rabbitScore;
	@NotNull(message = "이미지를 첨부해주세요.") MultipartFile animalPhoto;
}