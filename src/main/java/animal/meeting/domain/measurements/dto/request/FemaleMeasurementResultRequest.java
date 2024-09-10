package animal.meeting.domain.measurements.dto.request;

import jakarta.validation.constraints.NotNull;

public class FemaleMeasurementResultRequest extends CommonMeasurementRequest {
	@NotNull(message = "여우 점수를 입력해주세요.") Integer desertFoxScore;
	@NotNull(message = "사슴 점수를 입력해주세요.")  Integer deerScore;
	@NotNull(message = "햄스터 점수를 입력해주세요.")  Integer hamsterScore;
}