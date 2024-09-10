package animal.meeting.global.sms;

public enum SmsText {
	CHECKING_NUMBER("인증 번호 : ");

	private final String value;

	SmsText(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}
}
