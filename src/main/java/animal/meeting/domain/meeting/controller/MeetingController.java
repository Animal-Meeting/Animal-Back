package animal.meeting.domain.meeting.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import animal.meeting.domain.meeting.dto.response.MeetingResultResponse;
import animal.meeting.domain.meeting.entity.type.MeetingGroupType;
import animal.meeting.domain.meeting.service.MeetingService;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/meeting")
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
		@RequestParam
		@NotNull
		Long password,
		@RequestParam
		@NotNull
		MeetingGroupType type) {
		meetingService.progressAllMatching(password, type);
	}
}
