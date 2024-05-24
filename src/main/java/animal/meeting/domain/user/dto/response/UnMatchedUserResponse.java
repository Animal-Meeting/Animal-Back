package animal.meeting.domain.user.dto.response;

import java.util.List;
import java.util.stream.Collectors;

import animal.meeting.domain.user.entity.UnMatchedUser;
import animal.meeting.domain.user.entity.User;

public record UnMatchedUserResponse(
List<UnMatchedUser> list
) {
	public static UnMatchedUserResponse from(List<User> userList) {

		List<UnMatchedUser> unmatchedUsers = userList.stream()
			.map(UnMatchedUser::create)
			.toList();
		return new UnMatchedUserResponse(unmatchedUsers);
	}
}
