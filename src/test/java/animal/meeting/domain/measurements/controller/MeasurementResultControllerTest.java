package animal.meeting.domain.measurements.controller;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureWebMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;

import animal.meeting.domain.measurements.dto.request.CommonMeasurementRequest;
import animal.meeting.domain.measurements.dto.request.MaleMeasurementResultRequest;
import animal.meeting.domain.measurements.service.MeasurementResultService;
import animal.meeting.domain.user.entity.type.AnimalType;
import animal.meeting.domain.user.entity.type.Gender;

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

		MockMultipartFile animalPhoto = new MockMultipartFile(
			"animalPhoto",
			"test.jpg",
			"image/jpeg",
			"test".getBytes()
		);

		Mockito.doNothing().when(measurementResultService).saveMeasurementResult(any(CommonMeasurementRequest.class));

		String studentId = "12345678";
		AnimalType animalType = AnimalType.BEAR;
		Gender gender = Gender.MALE;


		mockMvc.perform(multipart("/api/v2/measurements/male")
				.file(animalPhoto)
				.param("studentId", studentId.toString())
				.param("animalType", animalType.name())
				.param("gender", gender.name())
				.param("dogScore", "10")
				.param("catScore", "10")
				.param("rabbitScore", "10")
				.param("dinosaurScore", "10")
				.param("bearScore", "10")
				.param("wolfScore", "10")
				.contentType("multipart/form-data"))
			.andDo(print())
			.andExpect(status().isOk());


		Mockito.verify(measurementResultService, times(1)).saveMeasurementResult(any(CommonMeasurementRequest.class));
	}

	@Test
	@Tag("v1")
	@DisplayName("여자 측정 결과 이미지 등록하기")
	void uploadFemaleMeasurementResult() throws Exception {
		MockMultipartFile animalPhoto = new MockMultipartFile(
			"animalPhoto",
			"test.jpg",
			"image/jpeg",
			"test".getBytes()
		);

		Mockito.doNothing().when(measurementResultService).saveMeasurementResult(any(CommonMeasurementRequest.class));

		mockMvc.perform(multipart("/api/v2/measurements/female")
				.file(animalPhoto)
				.param("studentId", "1234567".toString())
				.param("animalType", AnimalType.CAT.toString())
				.param("gender", Gender.FEMALE.toString())
				.param("dogScore", "10")
				.param("catScore", "20")
				.param("rabbitScore", "30")
				.param("desertFoxScore", "40")
				.param("deerScore", "20")
				.param("hamsterScore", "30")
				.contentType("multipart/form-data"))
			.andExpect(status().isOk());

		Mockito.verify(measurementResultService, times(1)).saveMeasurementResult(any(CommonMeasurementRequest.class));
	}

	@Test
	@Tag("v1")
	@DisplayName("측정 결과 이미지 다운하기")
	void downloadMeasurementResult() {
	}
}