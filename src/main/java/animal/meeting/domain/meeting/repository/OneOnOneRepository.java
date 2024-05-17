package animal.meeting.domain.meeting.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import animal.meeting.domain.meeting.entity.OneOnOneMeeting;
import animal.meeting.domain.meeting.entity.type.MeetingStatus;
import animal.meeting.domain.user.entity.type.Gender;

public interface OneOnOneRepository extends JpaRepository<OneOnOneMeeting, String> {
	@Query("SELECT t FROM OneOnOneMeeting t WHERE t.user1.id = :userId AND t.status = :status AND DATE(t.createdAt) = CURRENT_DATE ORDER BY t.createdAt DESC")
	Optional<OneOnOneMeeting> findMostRecentTodayByUserIdAndStatus(@Param("userId") Long userId, @Param("status") MeetingStatus status);

	Long countByGenderAndStatus(Gender gender, MeetingStatus status);
}
