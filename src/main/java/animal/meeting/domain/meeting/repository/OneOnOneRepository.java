package animal.meeting.domain.meeting.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import animal.meeting.domain.meeting.entity.OneOnOneMeeting;

public interface OneOnOneRepository extends JpaRepository<OneOnOneMeeting, String> {
}
