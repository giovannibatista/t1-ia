package br.com.ia.agents;

import java.util.ArrayList;

public class Recharger extends Agent {
	private static String icon = "img/recharger.png";

	private ArrayList<Collector> collectors;
	private static Integer capacity = 2;

	public Recharger() {
		super(icon);
		collectors = new ArrayList<Collector>();
	}

	public boolean isBusy() {
		return collectors.size() == capacity;
	}

	public boolean addCollector(Collector collector) {
		if (collector == null || isBusy())
			return false;

		collectors.add(collector);
		return true;
	}

	public boolean removeCollector(Collector collector) {
		return collectors.remove(collector);
	}

	public String getIcon() {
		return icon;
	}

	public static void setIcon(String icon) {
		Recharger.icon = icon;
	}

}