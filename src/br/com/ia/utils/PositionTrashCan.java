package br.com.ia.utils;

public class PositionTrashCan {
	private Integer x;
	private Integer y;
	private TrashType trashType;

	public PositionTrashCan(TrashType trashType, Integer x, Integer y) {
		this.x = x;
		this.y = y;
		this.trashType = trashType;
	}

	public int hashCode() {
		int hashX = (x != null ? x.hashCode() : 0);
		int hashY = (y != null ? y.hashCode() : 0);

		return (hashX + hashY) * hashY + hashX;
	}

	public boolean equals(Object other) {
		if (!(other instanceof PositionTrashCan)) {
			return false;
		}

		PositionTrashCan otherPair = (PositionTrashCan) other;

		return ((this.x == otherPair.x || (this.x != null
				&& otherPair.x != null && this.x.equals(otherPair.x))) && (this.y == otherPair.y || (this.y != null
				&& otherPair.y != null && this.y.equals(otherPair.y))));
	}

	public String toString() {
		return "(" + x + ", " + y + ")";
	}

	public Integer getX() {
		return x;
	}

	public void setX(Integer x) {
		this.x = x;
	}

	public Integer getY() {
		return y;
	}

	public void setY(Integer y) {
		this.y = y;
	}

	public TrashType getTrashType() {
		return trashType;
	}

	public void setTrashType(TrashType trashType) {
		this.trashType = trashType;
	}

}