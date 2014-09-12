package br.com.ia.agents;

import java.util.ArrayList;

import br.com.ia.utils.Position;
import br.com.ia.utils.PositionTrashCan;
import br.com.ia.utils.TrashType;

public class Collector extends Agent {

	private ArrayList<PositionTrashCan> trashCans;
	private ArrayList<Position> rechargers;

	ArrayList<Position> neighbors;

	String status;

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

	public void run(ArrayList<Position> neighbors) {

	}
}