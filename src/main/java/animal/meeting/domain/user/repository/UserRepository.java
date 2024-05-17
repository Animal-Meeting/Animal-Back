package animal.meeting.domain.user.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import animal.meeting.domain.user.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {

	Optional<User> findByNameAndPhoneNumber(String name, String phoneNumber);
}
