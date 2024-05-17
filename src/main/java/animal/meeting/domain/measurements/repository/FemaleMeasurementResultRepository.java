package animal.meeting.domain.measurements.repository;

import java.util.Optional;

import animal.meeting.domain.measurements.entity.female.FemaleMeasurementResult;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface FemaleMeasurementResultRepository extends JpaRepository<FemaleMeasurementResult, Long> {
	Optional<FemaleMeasurementResult> findFemaleMeasurementResultByStudentId(String studentId);
}
