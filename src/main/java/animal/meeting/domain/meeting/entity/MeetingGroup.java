package animal.meeting.domain.meeting.entity;

import java.util.List;

import animal.meeting.domain.user.entity.User;

public interface MeetingGroup {
	void addUser(List<User> userList);
}
