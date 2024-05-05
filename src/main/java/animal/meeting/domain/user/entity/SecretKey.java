package animal.meeting.domain.user.entity;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;


@Component
public class SecretKey {
	private final long secretKey1;
	private final long secretKey2;
	private final long secretKey3;

	public SecretKey(
		@Value("${secret.key1}") long secretKey1,
		@Value("${secret.key2}") long secretKey2,
		@Value("${secret.key3}") long secretKey3) {
		this.secretKey1 = secretKey1;
		this.secretKey2 = secretKey2;
		this.secretKey3 = secretKey3;
	}

	public boolean isValidSecretKey(long inputKey) {
		return inputKey == secretKey1 || inputKey == secretKey2 || inputKey == secretKey3;
	}
}