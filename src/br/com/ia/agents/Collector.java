package br.com.ia.agents;

import java.util.ArrayList;

import br.com.ia.utils.Position;

public class Collector extends Agent {

	private ArrayList<Position> trashCans;
	private ArrayList<Position> rechargers;
	
	private ArrayList<Position> neighbors;
	
	private String status;
	
	private static String icon = "img/collector.png";

	public Collector(String name, int axisX, int axisY) {
		super(name, icon, axisY, axisY);
		trashCans = new ArrayList<Position>();
		rechargers = new ArrayList<Position>();
	}
	
	public Collector(String name, Position position) {
		super(name, icon, position);
		trashCans = new ArrayList<Position>();
		rechargers = new ArrayList<Position>();
	}

	public boolean addTrashCan(int x, int y) {
		Position pos = new Position(x, y);
		
		if (trashCans.contains(pos)) {
			return false;
		}
		
		return trashCans.add(pos);
	}
	
	public boolean addRecharger(int x, int y) {
		Position pos = new Position(x, y);
		
		if (rechargers.contains(pos)) {
			return false;
		}
		
		return rechargers.add(pos);
	}
	
	public void run(ArrayList<Position> neighbors) {
		
	}
}