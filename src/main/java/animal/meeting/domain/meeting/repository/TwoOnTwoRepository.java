package animal.meeting.domain.meeting.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import animal.meeting.domain.meeting.entity.TwoOnTwoMeeting;
import animal.meeting.domain.meeting.entity.type.MeetingStatus;
import animal.meeting.domain.user.entity.type.Gender;

public interface TwoOnTwoRepository extends JpaRepository<TwoOnTwoMeeting, String> {
	@Query("SELECT t FROM TwoOnTwoMeeting t WHERE (t.user1.id = :userId OR t.user2.id = :userId) AND t.status = :status ORDER BY t.createdAt DESC LIMIT 1")
	Optional<TwoOnTwoMeeting> findMostRecentByUserIdAndStatus(@Param("userId") Long userId, @Param("status") MeetingStatus status);

	List<TwoOnTwoMeeting> findAllByGenderAndStatus(Gender gender, MeetingStatus status);
}
