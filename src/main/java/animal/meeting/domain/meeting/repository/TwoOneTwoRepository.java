package animal.meeting.domain.meeting.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import animal.meeting.domain.meeting.entity.TwoOnTwoMeeting;

public interface TwoOneTwoRepository extends JpaRepository<TwoOnTwoMeeting, String> {
}
