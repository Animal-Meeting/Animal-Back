package animal.meeting.domain.meeting.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import animal.meeting.domain.meeting.entity.MatchingResult;

public interface MatchingResultRepository extends JpaRepository<MatchingResult, Long> {

	@Query("SELECT m FROM MatchingResult m WHERE m.girlGroupId = :groupId OR m.manGroupId = :groupId")
	Optional<MatchingResult> findByGroupId(@Param("groupId") String groupId);
}
