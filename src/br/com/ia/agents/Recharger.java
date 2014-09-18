package br.com.ia.agents;

public class Recharger extends Agent {
	private static String icon = "img/recharger.png";
	
	private Collector[] collectors;

	public Recharger() {
		super(icon);
		collectors = new Collector[2];
	}

	public Collector[] getCollectors() {
		return collectors;
	}

	public void setColllectors(Collector[] collectors) {
		this.collectors = collectors;
	}
}