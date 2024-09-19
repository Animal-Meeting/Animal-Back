package animal.meeting.domain.user.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import animal.meeting.domain.meeting.entity.type.MeetingGroupType;
import animal.meeting.domain.user.dto.request.NewUserRegisterRequest;
import animal.meeting.domain.user.dto.request.PhoneAuthRequest;
import animal.meeting.domain.user.dto.request.PhoneNumberRequest;
import animal.meeting.domain.user.dto.response.ParticipantResponse;
import animal.meeting.domain.user.dto.response.SecretKeyResponse;
import animal.meeting.domain.user.service.UserService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v2/users")
public class UserController {

	private final UserService userService;

	@PostMapping("")
	public void registerUserAndMeeting(
		@Valid @RequestBody List<NewUserRegisterRequest> request,
		@RequestParam
		@NotNull
		MeetingGroupType groupType) {
		userService.registerUserAndMeeting(request, groupType);
	}

	@GetMapping("/participants/count")
	public ParticipantResponse getParticipantCount() {
		return userService.getParticipantCount();
	}

	@PostMapping("/auth/phone/varification")
	public SecretKeyResponse checkValidUser(@Valid @RequestBody PhoneAuthRequest request) {
		return userService.checkPhoneVerification(request);
	}

	@PostMapping("/auth/phone/request-code")
	public void requestVarificationCode(@Valid @RequestBody PhoneNumberRequest request) {
		userService.requestVarificationCode(request);
	}
}
