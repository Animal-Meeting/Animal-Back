package animal.meeting.domain.user.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import animal.meeting.domain.user.dto.request.UserRegisterRequest;
import animal.meeting.domain.user.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/user")
public class UserController {

	private final UserService userService;

	@PostMapping("/register")
	public void registerMeeting(@Valid @RequestBody UserRegisterRequest request) {
		userService.registerUser(request);
	}
}
