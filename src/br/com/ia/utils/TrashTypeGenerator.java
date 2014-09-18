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
		if (index < trashTypes.length - 1) {
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
			case GLASS:
			case NONE:
				return "img/trashCanEmptyGreen.png";
			case PAPER:
				return "img/trashCanEmptyBlue.png";
			case METAL:
				return "img/trashCanEmptyYellow.png";
			case PLASTIC:
				return "img/trashCanEmptyRed.png";
			default:
				break;
		}
		
		return "";
	}

	public static String getTrashIcon(TrashType trashType) {

		switch (trashType) {
			case GLASS:
			case NONE:
				return "img/trashGreen.png";
			case PAPER:
				return "img/trashBlue.png";
			case METAL:
				return "img/trashYellow.png";
			case PLASTIC:
				return "img/trashRed.png";
			default:
				break;
		}
		
		return "";
	}
}