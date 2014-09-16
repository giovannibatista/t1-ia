package br.com.ia.utils;

public class PositionTrashCan extends Position {
	private TrashType trashType;

	public PositionTrashCan(TrashType trashType, Integer x, Integer y) {
		super(x, y);
		this.trashType = trashType;
	}

	public TrashType getTrashType() {
		return trashType;
	}

	public void setTrashType(TrashType trashType) {
		this.trashType = trashType;
	}
}