package br.com.ia.agents;

public class Trash extends Block {

	private String type;
	private String name;
	private static String icon = "img/trash.png";
	
	private int quantity;

	public Trash(String name, int quantity, String type, Integer x, Integer y) {
		super(x, y);
		this.type = type;
		this.quantity = quantity;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	public String toString() {
		return name;
	}
}