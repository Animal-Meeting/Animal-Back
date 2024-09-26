package animal.meeting.domain.meeting.controller;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;
import java.util.Optional;

import javax.print.attribute.standard.Media;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureWebMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.deser.DataFormatReaders;

import animal.meeting.domain.meeting.dto.request.ProgressingMeetingRequest;
import animal.meeting.domain.meeting.dto.response.MeetingResultResponse;
import animal.meeting.domain.meeting.entity.MatchingResult;
import animal.meeting.domain.meeting.entity.type.MeetingGroupType;
import animal.meeting.domain.meeting.service.MeetingService;
import animal.meeting.domain.user.dto.response.UnMatchedUserResponse;
import animal.meeting.domain.user.entity.ResultUser;
import animal.meeting.domain.user.entity.User;
import animal.meeting.domain.user.repository.UserRepository;
import animal.meeting.global.error.CustomException;
import animal.meeting.global.error.constants.ErrorCode;

@WebMvcTest(MeetingController.class)
@AutoConfigureWebMvc
public class MeetingControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	MeetingService meetingService;

	@Test
	@Tag("v2")
	@DisplayName("미팅결과 가져오기")
	void getMeetingResultList() throws Exception{

		Long userId = 1L;
		MeetingResultResponse mockResponse = new MeetingResultResponse(
			List.of(), List.of(), "kakaoLink", MeetingGroupType.ONE_ON_ONE
		);
		Mockito.when(meetingService.getMeetingResultList(userId)).thenReturn(mockResponse);

		mockMvc.perform(get("/api/v2/meetings/matching-result")
				.param("userId", userId.toString())
				.contentType(MediaType.APPLICATION_JSON))
			.andExpect(status().isOk())
			.andExpect(jsonPath("$.data.kakaoLink").value("kakaoLink"))
			.andExpect(jsonPath("$.data.meetingGroupType").value("ONE_ON_ONE"));

		verify(meetingService, times(1)).getMeetingResultList(userId);

	}


	@Test
	@DisplayName("매칭로직 실행")
	void progressAllMatching() {
	}

	@Test
	@DisplayName("매칭이 안된 유저들 가져오기")
	void getUnmatchedUsers() {
	}
}