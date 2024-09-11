package animal.meeting.domain.meeting.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import animal.meeting.domain.meeting.dto.request.ProgressingMeetingRequest;
import animal.meeting.domain.meeting.dto.response.MeetingResultResponse;
import animal.meeting.domain.meeting.service.MeetingService;
import animal.meeting.domain.user.dto.response.UnMatchedUserResponse;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/meetings")
public class MeetingController {

	private final MeetingService meetingService;
	@GetMapping("/matching-result")
	public MeetingResultResponse getMeetingResultList(
		@RequestParam
		@NotNull
		Long userId) {
		return meetingService.getMeetingResultList(userId);
	}

	@PostMapping("/matching-start")
	public void progressAllMatching(
		@Valid @RequestBody ProgressingMeetingRequest request) {
		meetingService.progressAllMatching(request);
	}

	@GetMapping("/unmatched")
	public UnMatchedUserResponse getUnmatchedUsers() {
		return meetingService.getUnmatchedUsers();
	}
}
