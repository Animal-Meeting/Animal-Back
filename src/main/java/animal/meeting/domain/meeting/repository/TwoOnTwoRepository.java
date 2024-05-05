package animal.meeting.domain.meeting.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import animal.meeting.domain.meeting.entity.TwoOnTwoMeeting;
import animal.meeting.domain.meeting.entity.type.MeetingStatus;

public interface TwoOnTwoRepository extends JpaRepository<TwoOnTwoMeeting, String> {

	@Query("SELECT t FROM TwoOnTwoMeeting t WHERE t.user1.id = :userId AND t.status = :status AND DATE(t.createdAt) = CURRENT_DATE ORDER BY t.createdAt DESC")
	Optional<TwoOnTwoMeeting> findMostRecentTodayByUserIdAndStatus(@Param("userId") Long userId, @Param("status") MeetingStatus status);
}
