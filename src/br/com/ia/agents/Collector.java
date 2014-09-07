package br.com.ia.agents;

import java.util.ArrayList;

public class Collector extends Agent {
	
	private ArrayList<TrashCan> trasCans;
	private ArrayList<Recharge> recharges;
	private static String icon = "img/collector.png";
		

	public Collector(String name, Integer axisX, Integer axisY) {
		super(name, icon, axisY, axisY);
		// TODO Auto-generated constructor stub
	}


	public ArrayList<TrashCan> getTrasCans() {
		return trasCans;
	}


	public void setTrasCans(ArrayList<TrashCan> trasCans) {
		this.trasCans = trasCans;
	}


	public ArrayList<Recharge> getRecharges() {
		return recharges;
	}


	public void setRecharges(ArrayList<Recharge> recharges) {
		this.recharges = recharges;
	}


	public String getIcon() {
		return icon;
	}


	public void setIcon(String icon) {
		Collector.icon = icon;
	}
	
	
	

}
