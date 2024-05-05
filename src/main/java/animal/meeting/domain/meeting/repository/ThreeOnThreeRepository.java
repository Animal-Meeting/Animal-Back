package animal.meeting.domain.meeting.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import animal.meeting.domain.meeting.entity.ThreeOnThreeMeeting;
import animal.meeting.domain.meeting.entity.type.MeetingStatus;

public interface ThreeOnThreeRepository extends JpaRepository<ThreeOnThreeMeeting, String> {
	@Query("SELECT t FROM ThreeOnThreeMeeting t WHERE t.user1.id = :userId AND t.status = :status AND DATE(t.createdAt) = CURRENT_DATE ORDER BY t.createdAt DESC")
	Optional<ThreeOnThreeMeeting> findMostRecentTodayByUserIdAndStatus(@Param("userId") Long userId, @Param("status") MeetingStatus status);
}
