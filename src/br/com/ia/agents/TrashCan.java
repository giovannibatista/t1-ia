package br.com.ia.agents;

import br.com.ia.utils.TrashType;

public class TrashCan extends Agent {
	private TrashType color;
	private Integer capacity;
	private Integer used;

	public TrashCan(String name, String icon, Integer capacity,
			TrashType color) {
		super(name, icon);
		this.capacity = capacity;
		this.color = color;
	}
	
	public boolean addTrash() {
		if (isFull()) {
			return false;
		}
		
		used++;
		return true;
	}
	
	public boolean isFull() {
		boolean isFull = capacity == used;
		if(isFull){
			setIcon("img/trashCanFull.png");
		}
		return isFull;
	}

	public TrashType getColor() {
		return color;
	}
}