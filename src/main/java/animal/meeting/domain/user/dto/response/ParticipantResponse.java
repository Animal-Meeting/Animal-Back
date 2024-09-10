package animal.meeting.domain.user.dto.response;

public record ParticipantResponse(
	Long man,
	Long girl
) {
	public static ParticipantResponse of(Long man, Long girl){
		return new ParticipantResponse(
			man,
			girl
		);
	}
}
