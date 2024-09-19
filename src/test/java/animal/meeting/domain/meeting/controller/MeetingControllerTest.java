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

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureWebMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;

import animal.meeting.domain.meeting.dto.response.MeetingResultResponse;
import animal.meeting.domain.meeting.entity.MatchingResult;
import animal.meeting.domain.meeting.entity.type.MeetingGroupType;
import animal.meeting.domain.meeting.service.MeetingService;
import animal.meeting.domain.user.entity.ResultUser;

@WebMvcTest(MeetingController.class)
@AutoConfigureWebMvc
public class MeetingControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	MeetingService meetingService;

	@Test
	@DisplayName("미팅결과 가져오기")
	void getMeetingResultList() throws Exception{

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