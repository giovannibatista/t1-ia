package br.com.ia.agents;

import java.util.ArrayList;

import br.com.ia.controller.Matrix;
import br.com.ia.utils.Position;
import br.com.ia.utils.PositionTrashCan;
import br.com.ia.utils.TrashType;

public class Collector extends Agent {

	private ArrayList<PositionTrashCan> trashCans;
	private ArrayList<Position> rechargers;

	private ArrayList<Position> neighbors;
	private ArrayList<Position> possibleBlocks;
	private ArrayList<Position> visitedBlocks;

	String status;
	int battery;
	boolean batteryLow;

	private static String icon = "img/collector.png";

	public Collector(String name, Position position) {
		super(name, icon, position);
		trashCans = new ArrayList<PositionTrashCan>();
		rechargers = new ArrayList<Position>();
	}

	public boolean addTrashCan(TrashType trashType, int x, int y) {
		PositionTrashCan pos = new PositionTrashCan(trashType, x, y);

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

	public Integer defaultMove() {

		return 0;
	}

	public void run(ArrayList<Position> neighbors) {
		if ((neighbors == null) || (neighbors.size() == 0)) {
			// No romm to walk. :(
		}
		
		this.neighbors = neighbors;
	}
	
	/**
	 * Plans next step.
	 */
	private void plan() {
		if (status == "goingRecharge") {
			// Just keep going
		} else if (status == "wander") {
			if (batteryLow) {
				status = "walkRecharge";
				return;
			}
			
		}
	}
	
	/**
	 * Act according to what was planned
	 */
	private void act() {
		
	}
}