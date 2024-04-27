package animal.meeting.domain.meeting.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import animal.meeting.domain.meeting.entity.OneOnOneMeeting;
import animal.meeting.domain.meeting.entity.type.MeetingStatus;

public interface OneOnOneRepository extends JpaRepository<OneOnOneMeeting, String> {
	@Query("SELECT t FROM OneOnOneMeeting t WHERE (t.user1.id = :userId) AND t.status = :status")
	List<OneOnOneMeeting> findByUserIdAndStatus(@Param("userId") Long userId, @Param("status") MeetingStatus status);
}
