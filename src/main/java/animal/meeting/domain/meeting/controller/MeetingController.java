package animal.meeting.domain.meeting.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import animal.meeting.domain.meeting.entity.MeetingGroup;
import animal.meeting.domain.meeting.entity.ThreeOnThreeMeeting;
import animal.meeting.domain.meeting.service.MeetingService;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/meeting")
public class MeetingController {

	private final MeetingService meetingService;
	@GetMapping("/matching-result")
	public List<ThreeOnThreeMeeting> getMeetingResultList(
		@RequestParam
		@NotNull
		Long userId) {
		return meetingService.getMeetingResultList(userId);
	}
}
