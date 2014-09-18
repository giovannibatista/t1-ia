package br.com.ia.agents;

import br.com.ia.utils.TrashType;

public class Trash {
	private String icon;
	
	private TrashType trashType;

	public Trash(TrashType trashType, String icon) {
		this.trashType = trashType;
		this.icon = icon;
	}
	
	public String getIcon() {
		return icon;
	}
	
	public TrashType getTrashType() {
		return trashType;
	}
	
	public String toString() {
		return trashType.toString();
	}
}