package animal.meeting.domain.measurements.controller;

import animal.meeting.domain.measurements.dto.request.FemaleMeasurementResultRequest;
import animal.meeting.domain.measurements.dto.request.MaleMeasurementResultRequest;
import animal.meeting.domain.measurements.dto.response.MeasurementResultResponse;
import animal.meeting.domain.measurements.service.MeasurementResultService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/api/measurements")
@RequiredArgsConstructor
public class MeasurementResultController {

    private final MeasurementResultService measurementResultService;

    @PostMapping("/upload/male")
    public ResponseEntity<MeasurementResultResponse> uploadMaleMeasurementResult(@ModelAttribute MaleMeasurementResultRequest request) {
        try {
            MeasurementResultResponse result = measurementResultService.saveMaleMeasurementResult(request);
            return ResponseEntity.ok(result);
        } catch (IOException e) {
            return ResponseEntity.status(500).body(null);
        }
    }

    @PostMapping("/upload/female")
    public ResponseEntity<MeasurementResultResponse> uploadFemaleMeasurementResult(@ModelAttribute FemaleMeasurementResultRequest request) {
        try {
            MeasurementResultResponse result = measurementResultService.saveFemaleMeasurementResult(request);
            return ResponseEntity.ok(result);
        } catch (IOException e) {
            return ResponseEntity.status(500).body(null);
        }
    }
}
