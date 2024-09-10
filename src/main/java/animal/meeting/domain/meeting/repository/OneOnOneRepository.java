package animal.meeting.domain.meeting.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import animal.meeting.domain.meeting.entity.OneOnOneMeeting;
import animal.meeting.domain.meeting.entity.type.MeetingStatus;
import animal.meeting.domain.user.entity.type.Gender;

public interface OneOnOneRepository extends JpaRepository<OneOnOneMeeting, String>, MeetingGroupRepository<OneOnOneMeeting> {
	@Query("SELECT t FROM OneOnOneMeeting t WHERE t.user1.id = :userId AND t.status = :status ORDER BY t.createdAt DESC LIMIT 1")
	Optional<OneOnOneMeeting> findMostRecentByUserIdAndStatus(@Param("userId") Long userId,
		@Param("status") MeetingStatus status);

	List<OneOnOneMeeting> findAllByGenderAndStatus(Gender gender, MeetingStatus status);

	@Query("SELECT m FROM OneOnOneMeeting m WHERE m.status = :status AND DATE(m.createdAt) = CURDATE()")
	List<OneOnOneMeeting> findMeetingsByStatusAndCreatedAtToday(@Param("status") MeetingStatus status);
}
