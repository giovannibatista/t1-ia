package br.com.ia.agents;

import br.com.ia.utils.Position;
import br.com.ia.utils.TrashType;

public class Trash extends Block {

	private String name;

	public Trash(String icon, TrashType trashType, Position position) {
		super(icon, position);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String toString() {
		return name;
	}
}