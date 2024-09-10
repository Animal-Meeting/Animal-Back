package animal.meeting.domain.meeting.dto;

import java.util.List;

import animal.meeting.domain.user.entity.ResultUser;

// 성별에 따라 교환된 그룹 정보를 담는 클래스
public class MatchingGroups {
	private final List<ResultUser> myGroupDetails;
	private final List<ResultUser> otherGroupDetails;

	public MatchingGroups(List<ResultUser> myGroupDetails, List<ResultUser> otherGroupDetails) {
		this.myGroupDetails = myGroupDetails;
		this.otherGroupDetails = otherGroupDetails;
	}

	public List<ResultUser> getMyGroupDetails() {
		return myGroupDetails;
	}

	public List<ResultUser> getOtherGroupDetails() {
		return otherGroupDetails;
	}
}