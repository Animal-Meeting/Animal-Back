package animal.meeting.global.sms;

public enum SmsText {
	CHECKING_NUMBER("인증 번호 : "),
	MATCHING_RESULT("매칭이 완료되었습니다!\n 상대방 카카오 ID : ");

	private final String value;

	SmsText(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}
}
