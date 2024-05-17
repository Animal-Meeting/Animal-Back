package animal.meeting.domain.meeting.entity;

import java.util.List;

import org.springframework.stereotype.Service;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ProgressingGroup {
	private String groupId;
	private List<String> opponentGourpList;
}
