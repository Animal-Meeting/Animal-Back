package animal.meeting.domain.measurements.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class MaleMeasurementResultRequest extends CommonMeasurementRequest {
	@NotNull(message = "공룡 점수를 입력해주세요.") Integer dinosaurScore;
	@NotNull(message = "곰 점수를 입력해주세요.") Integer bearScore;
	@NotNull(message = "늑대 점수를 입력해주세요.") Integer wolfScore;
}