package animal.meeting.domain.measurements.repository;

import java.util.Optional;

import animal.meeting.domain.measurements.entity.male.MaleMeasurementResult;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MaleMeasurementResultRepository extends JpaRepository<MaleMeasurementResult, Long> {
	Optional<MaleMeasurementResult> findMaleMeasurementResultByStudentId(String studentId);
}
