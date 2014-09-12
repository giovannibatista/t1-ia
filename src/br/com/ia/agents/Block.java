package br.com.ia.agents;

import br.com.ia.utils.Position;

public class Block {

	private Position position;
	private String icon;

	public Block(Integer x, Integer y) {
		this.position = new Position(x, y);
	}

	public Block(Position position) {
		this.position = position;
	}

	public Block(String icon, Integer x, Integer y) {
		this.icon = icon;
		this.position = new Position(x, y);
	}

	public Block(String icon, Position position) {
		this.icon = icon;
		this.position = position;
	}

	public Position getPosition() {
		return position;
	}

	public String toString() {
		return "BLCK";
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public void setPosition(Position position) {
		this.position = position;
	}

}