package br.com.ia.agents;

import br.com.ia.utils.Position;

public class Block {

	private Position position;
	
	public Block(Integer x, Integer y) {
		this.position = new Position(x, y);
	}
	
	public Position getPosition() {
		return position;
	}
	
	public String toString() {
		return "BLCK";
	}
}