package br.com.ia.agents;

import br.com.ia.utils.Position;

public abstract class Agent extends Block {
	private String name;
	
	public Agent(String name, String icon, Integer x, Integer y) {
		super(icon, x, y);
		this.name = name;
	}

	public Agent(String name, String icon, Position position) {
		super(icon, position);
		this.name = name;
	}

	public Agent(Position position) {
		super(position);
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