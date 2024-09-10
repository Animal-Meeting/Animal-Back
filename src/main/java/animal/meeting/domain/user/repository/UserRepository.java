package animal.meeting.domain.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import animal.meeting.domain.user.entity.User;
import animal.meeting.domain.user.entity.type.Gender;

public interface UserRepository extends JpaRepository<User, Long> {

	@Query("SELECT COUNT(u) FROM User u WHERE u.gender = :gender AND FUNCTION('DATE', u.createdAt) = FUNCTION('DATE', CONVERT_TZ(NOW(), 'UTC', 'Asia/Seoul'))")
	long countByGenderAndCreatedAtToday(@Param("gender") Gender gender);
}
