package br.com.ia.agents;

import br.com.ia.utils.Position;

public class Recharger extends Agent {
	
	private Collector[] collectors;
	private static String icon = "img/recharger.png";

	public Recharger(Position position) {
		super(icon, position);
		collectors = new Collector[2];
	}

	public Collector[] getCollectors() {
		return collectors;
	}

	public void setColllectors(Collector[] collectors) {
		this.collectors = collectors;
	}
}