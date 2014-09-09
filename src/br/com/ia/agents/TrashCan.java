package br.com.ia.agents;

import br.com.ia.utils.TrashCanType;

public class TrashCan extends Agent {
	
	private boolean isFull;
	private Integer capacity;
	private TrashCanType color;
	
	public TrashCan(String name, boolean isFull, Integer capacity, TrashCanType color, Integer axisX, Integer axisY) {
		super(name, "", axisY, axisY);
		this.isFull = isFull;
		this.capacity = capacity;
		this.color = color;
	}
	
	public TrashCan(String name,String icon, boolean isFull, Integer capacity, TrashCanType color, Integer axisX, Integer axisY) {
		super(name, icon, axisY, axisY);
		this.isFull = isFull;
		this.capacity = capacity;
		this.color = color;
	}

	public boolean isFull() {
		return isFull;
	}
	public void setFull(boolean isFull) {
		this.isFull = isFull;
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
