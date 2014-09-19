package br.com.ia.agents;

import java.util.ArrayList;
import br.com.ia.utils.TrashType;

public class TrashCan extends Agent {
	private TrashType color;
	private Integer capacity;
	private ArrayList<Trash> content;
	
	public TrashCan(String name, String icon, Integer capacity,
			TrashType color) {
		super(name, icon);
		this.capacity = capacity;
		this.color = color;
		
		this.content = new ArrayList<Trash>();
	}
	
	public boolean addTrash(Trash t) {
		if (isFull()) {
			return false;
		}
		
		content.add(t);
		
		return true;
	}
	
	public boolean isFull() {
		boolean isFull = (capacity == content.size());
		if (isFull) {
			setIcon("img/trashCanFull.png");
		}
		
		return isFull;
	}

	public TrashType getColor() {
		return color;
	}
}