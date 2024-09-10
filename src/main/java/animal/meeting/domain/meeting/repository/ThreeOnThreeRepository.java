package animal.meeting.domain.meeting.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import animal.meeting.domain.meeting.entity.OneOnOneMeeting;
import animal.meeting.domain.meeting.entity.ThreeOnThreeMeeting;
import animal.meeting.domain.meeting.entity.type.MeetingStatus;
import animal.meeting.domain.user.entity.type.Gender;

public interface ThreeOnThreeRepository extends JpaRepository<ThreeOnThreeMeeting, String>, MeetingGroupRepository<ThreeOnThreeMeeting> {
	@Query("SELECT t FROM ThreeOnThreeMeeting t WHERE (t.user1.id = :userId OR t.user2.id = :userId OR t.user3.id = :userId) AND t.status = :status ORDER BY t.createdAt DESC LIMIT 1")
	Optional<ThreeOnThreeMeeting> findMostRecentByUserIdAndStatus(@Param("userId") Long userId, @Param("status") MeetingStatus status);

	List<ThreeOnThreeMeeting> findAllByGenderAndStatus(Gender gender, MeetingStatus status);

	@Query("SELECT m FROM ThreeOnThreeMeeting m WHERE m.status = :status AND DATE(m.createdAt) = CURDATE()")
	List<ThreeOnThreeMeeting> findMeetingsByStatusAndCreatedAtToday(@Param("status") MeetingStatus status);
}
