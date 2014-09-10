package br.com.ia.agents;

import br.com.ia.utils.TrashCanType;

public class TrashCan extends Agent {

	private boolean full;
	private Integer capacity;
	private TrashCanType color;

	public TrashCan(String name, Integer capacity, TrashCanType color, Integer axisX, Integer axisY) {
		super(name, "", axisY, axisY);
		this.capacity = capacity;
		this.color = color;
	}

	public TrashCan(String name, String icon, Integer capacity, TrashCanType color, Integer axisX, Integer axisY) {
		super(name, icon, axisY, axisY);
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

	public TrashCanType getColor() {
		return color;
	}

	public void setColor(TrashCanType color) {
		this.color = color;
	}
}