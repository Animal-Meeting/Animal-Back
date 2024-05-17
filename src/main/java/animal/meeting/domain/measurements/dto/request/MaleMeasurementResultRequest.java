package animal.meeting.domain.measurements.dto.request;

import animal.meeting.domain.user.entity.type.AnimalType;
import animal.meeting.domain.user.entity.type.Gender;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import org.springframework.web.multipart.MultipartFile;

public record MaleMeasurementResultRequest(
        @NotNull String studentId,
        @NotNull AnimalType animalType,
        @NotNull Gender gender,
        @NotNull Integer dogScore,
        @NotNull Integer catScore,
        @NotNull Integer rabbitScore,
        @NotNull Integer dinosaurScore,
        @NotNull Integer bearScore,
        @NotNull Integer wolfScore,
        @NotNull MultipartFile animalPhoto) {
    @Builder
    public MaleMeasurementResultRequest {
    }
}
