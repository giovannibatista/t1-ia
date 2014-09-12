package br.com.ia.agents;

import br.com.ia.utils.Position;
import br.com.ia.utils.TrashType;

public class TrashCan extends Agent {

	private boolean full;
	private Integer capacity;
	private TrashType color;

	public TrashCan(String name, String icon, Integer capacity, TrashType color, Position position) {
		super(name, icon, position);
		this.capacity = capacity;
		this.color = color;
	}

	public boolean isFull() {
		return full;
	}

	public void setFull(boolean full) {
		this.full = full;
	}

	public Integer getCapacity() {
		return capacity;
	}

	public void setCapacity(Integer capacity) {
		this.capacity = capacity;
	}

	public TrashType getColor() {
		return color;
	}

	public void setColor(TrashType color) {
		this.color = color;
	}
}