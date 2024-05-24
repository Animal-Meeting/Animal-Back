package animal.meeting.domain.user.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import animal.meeting.domain.meeting.entity.type.MeetingGroupType;
import animal.meeting.domain.user.dto.request.LoginRequest;
import animal.meeting.domain.user.dto.request.UserRegisterRequest;
import animal.meeting.domain.user.dto.response.LoginResponse;
import animal.meeting.domain.user.dto.response.ParticipantResponse;
import animal.meeting.domain.user.dto.response.SecretKeyResponse;
import animal.meeting.domain.user.service.UserService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/user")
public class UserController {

	private final UserService userService;

	@PostMapping("/register")
	public void registerUserAndMeeting(
		@Valid @RequestBody List<UserRegisterRequest> request,
		@RequestParam
		@NotNull
		MeetingGroupType groupType) {
		userService.registerUserAndMeeting(request, groupType);
	}

	@GetMapping("/check")
	public SecretKeyResponse checkValidUser(@RequestParam @NotNull Long secretKey) {
		return userService.checkValidUser(secretKey);
	}

	@PostMapping("/login")
	public LoginResponse login(@Valid @RequestBody LoginRequest request) {
		return userService.login(request);
	}

	@GetMapping("/participant-count")
	public ParticipantResponse getParticipantCount() {
		return userService.getParticipantCount();
	}
}
