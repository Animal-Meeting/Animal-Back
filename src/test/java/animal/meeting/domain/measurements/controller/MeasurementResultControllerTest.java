package animal.meeting.domain.measurements.controller;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureWebMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;

import animal.meeting.domain.measurements.dto.request.MaleMeasurementResultRequest;
import animal.meeting.domain.measurements.service.MeasurementResultService;

@WebMvcTest(MeasurementResultController.class)
@AutoConfigureWebMvc
class MeasurementResultControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	MeasurementResultService  measurementResultService;

	@Autowired
	private ObjectMapper objectMapper;

	@Test
	@Tag("v1")
	@DisplayName("남자 측정 결과 이미지 등록하기")
	void uploadMaleMeasurementResult() throws Exception {
		MaleMeasurementResultRequest maleMeasurementResultRequest =
	}

	@Test
	@Tag("v1")
	@DisplayName("여자 측정 결과 이미지 등록하기")
	void uploadFemaleMeasurementResult() {
	}

	@Test
	@Tag("v1")
	@DisplayName("측정 결과 이미지 다운하기")
	void downloadMeasurementResult() {
	}
}