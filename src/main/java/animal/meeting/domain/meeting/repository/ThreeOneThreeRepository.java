package animal.meeting.domain.meeting.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import animal.meeting.domain.meeting.entity.ThreeOnThreeMeeting;

public interface ThreeOneThreeRepository extends JpaRepository<ThreeOnThreeMeeting, String> {
}
