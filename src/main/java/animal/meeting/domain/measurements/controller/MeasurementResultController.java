package animal.meeting.domain.measurements.controller;

import animal.meeting.domain.measurements.dto.request.FemaleMeasurementResultRequest;
import animal.meeting.domain.measurements.dto.request.MaleMeasurementResultRequest;
import animal.meeting.domain.measurements.dto.response.MeasurementResultResponse;
import animal.meeting.domain.measurements.service.MeasurementResultService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/api/v1/measurements")
@RequiredArgsConstructor
public class MeasurementResultController {

    private final MeasurementResultService measurementResultService;

    @PostMapping("/male")
    public void uploadMaleMeasurementResult(@Valid @ModelAttribute MaleMeasurementResultRequest request) throws IOException {
        measurementResultService.saveMeasurementResult(request);
    }

    @PostMapping("/female")
    public void uploadFemaleMeasurementResult(@Valid @ModelAttribute FemaleMeasurementResultRequest request) throws IOException {
        measurementResultService.saveMeasurementResult(request);
    }

    @GetMapping("/download")
    public MeasurementResultResponse downloadMeasurementResult(
        @RequestParam
        @NotNull
        String studentId)  {
        return measurementResultService.getMaleMeasurementResult(studentId);
    }
}
