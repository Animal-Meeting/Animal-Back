package animal.meeting;

import java.time.LocalDateTime;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;

@EntityListeners(AuditingEntityListener.class)
@MappedSuperclass
@Getter
public abstract class BaseTimeEntity {

	//생성일자는 수정하면 안되니까 update = false
	@CreatedDate
	@Column(updatable = false)
	private LocalDateTime createAt;

	// 조회한 Entity 값을 변경할 때 시간이 자동 저장
	@LastModifiedDate
	private LocalDateTime updatedAt;
}
