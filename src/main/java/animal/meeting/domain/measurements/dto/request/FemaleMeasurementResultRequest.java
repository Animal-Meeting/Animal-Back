package animal.meeting.domain.measurements.dto.request;

import animal.meeting.domain.user.entity.type.AnimalType;
import animal.meeting.domain.user.entity.type.Gender;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import org.springframework.web.multipart.MultipartFile;

public record FemaleMeasurementResultRequest(
        @NotNull String studentId,
        @NotNull AnimalType animalType,
        @NotNull Gender gender,
        @NotNull Integer dogScore,
        @NotNull Integer catScore,
        @NotNull Integer rabbitScore,
        @NotNull Integer desertFoxScore,
        @NotNull Integer deerScore,
        @NotNull Integer hamsterScore,
        @NotNull MultipartFile animalPhoto) {
    @Builder
    public FemaleMeasurementResultRequest {
    }
}
