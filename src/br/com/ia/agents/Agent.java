package br.com.ia.agents;

public abstract class Agent {
	private String name;
	private String icon;

	public Agent(String icon) {
		this.icon = icon;
	}
	
	public Agent(String name, String icon) {
		this.name = name;
		this.icon = icon;
	}
	
	public String getIcon() {
		return icon;
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