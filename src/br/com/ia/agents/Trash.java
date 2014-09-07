package br.com.ia.agents;

public class Trash extends Agent {
	
	private String type;
	private static String icon = "img/trash.png";
	
	public Trash(String name, String type, Integer axisX, Integer axisY) {
		super(name, icon, axisX, axisX);
		this.type = type;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	

}
