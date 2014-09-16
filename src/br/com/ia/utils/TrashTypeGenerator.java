package br.com.ia.utils;

public class TrashTypeGenerator {

	private static Integer index = 0;
	private static TrashType[] trashTypes = TrashType.values();

	public static TrashType getRandomTrashType() {
		Integer index = (int) (Math.random() * trashTypes.length);
		return trashTypes[index];
	}

	public static TrashType next() {
		TrashType trashType;
		if (index < trashTypes.length) {
			trashType = trashTypes[index];
			index++;
		} else {
			index = 0;
			trashType = trashTypes[index];
		}

		return trashType;

	}

	public static String getTrashCanIcon(TrashType trashType) {

		switch (trashType) {
		case GREEN:
			return "img/trashCanEmptyGreen.png";
		case BLUE:
			return "img/trashCanEmptyBlue.png";
		case YELLOW:
			return "img/trashCanEmptyYellow.png";
		case RED:
			return "img/trashCanEmptyRed.png";
		default:
			break;
		}
		return "";
	}

	public static String getTrashIcon(TrashType trashType) {

		switch (trashType) {
		case GREEN:
			return "img/trashGreen.png";
		case BLUE:
			return "img/trashBlue.png";
		case YELLOW:
			return "img/trashYellow.png";
		case RED:
			return "img/trashRed.png";
		default:
			break;
		}
		return "";
	}

}
