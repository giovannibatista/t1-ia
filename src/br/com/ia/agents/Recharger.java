package br.com.ia.agents;

public class Recharger extends Agent {
	
	private Collector[] collectors;
	private static String icon = "img/recharge.png";

	public Recharger(String name, Integer axisX, Integer axisY) {
		super(name, icon, axisY, axisY);
		collectors = new Collector[2];
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		Recharger.icon = icon;
	}

	public Collector[] getCollectors() {
		return collectors;
	}

	public void setColllectors(Collector[] collectors) {
		this.collectors = collectors;
	}
}