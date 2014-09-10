package br.com.ia.agents;

public abstract class Agent extends Block {
	private String name;
	private String icon;
	
	public Agent(String name, String icon, Integer x, Integer y) {
		super(x, y);
		this.name = name;
		this.icon = icon;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}
	
	public String toString() {
		return name;
	}
}