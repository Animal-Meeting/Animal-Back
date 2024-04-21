package animal.meeting.entity.type;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum AninmalType {

	DOG("강아지"),
	CAT("고양이"),
	RABBIT("토끼"),
	DESERT_FOX("사막여우"),
	DEER("사슴"),
	HAMSTER("햄스터"),
	DINOSAUR("공룡"),
	BEAR("곰"),
	WOLF("늑대");

	private final String value;
}
