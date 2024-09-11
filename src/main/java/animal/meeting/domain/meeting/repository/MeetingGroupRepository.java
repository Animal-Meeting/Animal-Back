package animal.meeting.domain.meeting.repository;

import java.util.Optional;

import animal.meeting.domain.meeting.entity.type.MeetingStatus;

public interface MeetingGroupRepository<T> {
    Optional<T> findMostRecentByUserIdAndStatus(Long userId, MeetingStatus status);
}