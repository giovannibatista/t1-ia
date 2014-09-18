package br.com.ia.agents;

import br.com.ia.utils.Position;

public class Block {

	private Position position;
	
	private Agent agent;
	private Trash trash;

	public Block(Position position) {
		this.position = position;
		this.setAgent(null);
		this.setTrash(null);
	}
	
	public Block(Integer x, Integer y) {
		this.position = new Position(x, y);
		this.setAgent(null);
		this.setTrash(null);
	}
	
	public Block(Position position, Agent agent, Trash trash) {
		this.position = position;
		this.setAgent(agent);
		this.setTrash(trash);
	}
	
	public Block(Integer x, Integer y, Agent agent, Trash trash) {
		this.position = new Position(x, y);
		this.setAgent(agent);
		this.setTrash(trash);
	}
	
	public String getIcon() {
		if (agent != null) {
			return agent.getIcon();
		}
		
		if (trash != null) {
			return trash.getIcon();
		}
		
		return null;
	}
	
	public String toString() {
		return "(" + position.getX() + ", " + position.getY() + ")";
	}	
	
	public Position getPosition() {
		return position;
	}
	
	public void setPosition(Position position) {
		this.position = position;
	}

	public Agent getAgent() {
		return agent;
	}

	public void setAgent(Agent agent) {
		this.agent = agent;
	}

	public Trash getTrash() {
		return trash;
	}

	public void setTrash(Trash trash) {
		this.trash = trash;
	}
}