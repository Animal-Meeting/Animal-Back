package animal.meeting.domain.measurements.controller;

import animal.meeting.domain.measurements.dto.request.FemaleMeasurementResultRequest;
import animal.meeting.domain.measurements.dto.request.MaleMeasurementResultRequest;
import animal.meeting.domain.measurements.dto.response.MeasurementResultResponse;
import animal.meeting.domain.measurements.service.MeasurementResultService;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/api/v1/measurements")
@RequiredArgsConstructor
public class MeasurementResultController {

    private final MeasurementResultService measurementResultService;

    @PostMapping("/upload/male")
    public void uploadMaleMeasurementResult(@ModelAttribute MaleMeasurementResultRequest request) throws IOException {
        measurementResultService.saveMaleMeasurementResult(request);
    }

    @PostMapping("/upload/female")
    public void uploadFemaleMeasurementResult(@ModelAttribute FemaleMeasurementResultRequest request) throws IOException {
        measurementResultService.saveFemaleMeasurementResult(request);
    }

    @GetMapping("/download")
    public MeasurementResultResponse downloadMeasurementResult(
        @RequestParam
        @NotNull
        String studentId) throws IOException {
        return measurementResultService.getMaleMeasurementResult(studentId);
    }
}
