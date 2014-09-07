package br.com.ia.agents;

public class Recharge extends Agent {
	
	private Collector[] colllectors;
	private static String icon = "img/recharge.png";

	public Recharge(String name, Integer axisX, Integer axisY) {
		
		super(name, icon, axisY, axisY);
		colllectors = new Collector[2];
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		Recharge.icon = icon;
	}

	public Collector[] getColllectors() {
		return colllectors;
	}

	public void setColllectors(Collector[] colllectors) {
		this.colllectors = colllectors;
	}
	
	

}
