package animal.meeting.domain.user.controller;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import java.util.List;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.NotWritablePropertyException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureWebMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;

import animal.meeting.domain.meeting.entity.MeetingGroup;
import animal.meeting.domain.meeting.entity.type.MeetingGroupType;
import animal.meeting.domain.user.dto.request.NewUserRegisterRequest;
import animal.meeting.domain.user.entity.type.AnimalType;
import animal.meeting.domain.user.entity.type.Gender;
import animal.meeting.domain.user.service.UserService;
import animal.meeting.global.error.constants.ErrorCode;

@WebMvcTest(UserController.class)
@AutoConfigureWebMvc
class UserControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	UserService userService;

	@Autowired
	private ObjectMapper objectMapper;

	@Test
	@Tag("v2")
	@DisplayName("v2 미팅 등록하기")
	void registerUserAndMeeting() throws Exception {

		// given
		NewUserRegisterRequest user1 = NewUserRegisterRequest.builder()
			.phoneNumber("01011111111")
			.kakao("kakao1")
			.gender(Gender.MALE)
			.firstAnimalType(AnimalType.DOG)
			.secondAnimalType(AnimalType.CAT)
			.selfAnimalType(AnimalType.DINOSAUR)
			.build();

		NewUserRegisterRequest user2 = NewUserRegisterRequest.builder()
			.phoneNumber("01022222222")
			.kakao("kakao2")
			.gender(Gender.MALE)
			.firstAnimalType(AnimalType.RABBIT)
			.secondAnimalType(AnimalType.CAT)
			.selfAnimalType(AnimalType.BEAR)
			.build();

		List<NewUserRegisterRequest> userRegisterRequestList = List.of(user1, user2);

		MeetingGroupType groupType = MeetingGroupType.TWO_ON_TWO;

		// 메소드 호출 여부 확인
		Mockito.doNothing().when(userService).registerUserAndMeeting(userRegisterRequestList, groupType);

		mockMvc.perform(post("/api/v2/users")
			.contentType(MediaType.APPLICATION_JSON)
			.param("groupType", groupType.name())
			.content(objectMapper.writeValueAsString(userRegisterRequestList))) 	// 객체를 Json 문자열로 변환
			.andExpect(status().isOk());

		// 호출 검증 (1번 실행 되었는지 검증)
		Mockito.verify(userService, Mockito.times(1)).registerUserAndMeeting(userRegisterRequestList, groupType);
	}

	@Test
	@Tag("v1")
	@DisplayName("v1 잘못된 그룹 타입으로 미팅 등록 실패")
	void registerUserAndMeeting_InvalidGroupType() throws Exception {

		// given
		NewUserRegisterRequest user1 = NewUserRegisterRequest.builder()
			.phoneNumber("01011111111")
			.kakao("kakao1")
			.gender(Gender.MALE)
			.firstAnimalType(AnimalType.DOG)
			.secondAnimalType(AnimalType.CAT)
			.selfAnimalType(AnimalType.DINOSAUR)
			.build();

		List<NewUserRegisterRequest> userRegisterRequestList = List.of(user1);

		// 올바르지 않은 그룹타입
		MeetingGroupType groupType = MeetingGroupType.TWO_ON_TWO;

		mockMvc.perform(post("/api/v2/users")
			.contentType(MediaType.APPLICATION_JSON)
			.param("groupType", groupType.name())
			.content(objectMapper.writeValueAsString(userRegisterRequestList)))
			.andExpect(status().isBadRequest())
			.andExpect(jsonPath("$.message").value(ErrorCode.INVALID_MEETING_PARAMETERS));

		// register을 호출되면 안됨
		Mockito.verify(userService, Mockito.times(0)).registerUserAndMeeting(userRegisterRequestList, groupType);
	}

	@Test
	@DisplayName("참가자 수 가져오기")
	void getParticipantCount() {
	}

	@Test
	@DisplayName("휴대폰 인증하기")
	void checkValidUser() {
	}

	@Test
	@DisplayName("휴대폰 인증코드 요청하기")
	void requestVarificationCode() {
	}
}