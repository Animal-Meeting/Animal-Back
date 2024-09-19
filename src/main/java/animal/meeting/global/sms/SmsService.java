package animal.meeting.global.sms;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;

import animal.meeting.domain.user.entity.SecretKey;
import animal.meeting.domain.user.entity.User;

/** SMS 메시지를 보내는 서비스를 제공합니다. Aligo API를 사용하여 SMS를 전송합니다. */
@Service
public class SmsService {
	private static final String ALIGO_BASE_URL = "https://apis.aligo.in";
	private final SecretKey secretKey;
	private final WebClient webClient;

	@Value("${aligo.api.key}")
	private String apiKey;

	@Value("${aligo.api.user-id}")
	private String apiUserId;

	@Value("${aligo.api.sender}")
	private String sender;

	@Autowired
	public SmsService(WebClient.Builder webClientBuilder, SecretKey secretKey) {
		this.webClient = webClientBuilder.baseUrl(ALIGO_BASE_URL).build();
		this.secretKey = secretKey;
	}

	/**
	 * 휴대폰 인증용 SMS 전송
	 */
	public String sendAuthCode(String receiver) {
		int randomSecretKey = secretKey.getRandomSecretKey();
		String msg = SmsText.CHECKING_NUMBER.getValue() + randomSecretKey;
		return sendSms(receiver, msg);
	}

	/**
	 * 매칭 결과 알림 SMS 전송
	 */
	public String sendMatchingResultSms(String receiver, User partner) {
		String msg = SmsText.MATCHING_RESULT.getValue() + partner.getKakao();
		return sendSms(receiver, msg);
	}

	public String sendSms(String receiver, String msg) {
		MultiValueMap<String, String> formData = new LinkedMultiValueMap<>();
		formData.add("key", apiKey);
		formData.add("user_id", apiUserId);
		formData.add("sender", sender);
		formData.add("receiver", receiver);
		formData.add("msg", msg);

		System.out.println("@@@@@@");
		System.out.println(sender);

		return webClient
			.post()
			.uri("/send/")
			.contentType(MediaType.APPLICATION_FORM_URLENCODED)
			.body(BodyInserters.fromMultipartData(formData))
			.retrieve()
			.bodyToMono(String.class)
			.block();
	}
}
