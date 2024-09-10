package animal.meeting.domain.meeting.entity;

import java.util.List;

import animal.meeting.domain.meeting.entity.type.MeetingStatus;
import animal.meeting.domain.user.entity.User;

public interface MeetingGroup {
	void addUser(List<User> userList);

	List<User> getUserList();

	String getGroupId();

	MeetingStatus getStatus();

	void changeStatus(MeetingStatus status);

}
