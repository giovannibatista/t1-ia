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

	public ArrayList<Position> getNeighbors(Matrix matrix) {
		neighbors = new ArrayList<Position>();

		for (int x = -2; x <= 2; x++) {
			for (int y = -2; y <= 2; y++) {
				if ((((this.getPosition().getX() + x) >= 0) && (this
						.getPosition().getX() + x < matrix.getMatrix()[x].length))
						&& ((this.getPosition().getY() + y) >= 0)
						&& (this.getPosition().getY() + y < matrix.getMatrix()[x].length)) {
					neighbors.add(new Position(this.getPosition().getX() + x, this.getPosition().getY() + y));

				}

			}
		}

		/*
		 * if (((celula.X + i) >= 0 && (celula.X + i <
		 * matrix.Ambient.GetLength(0))) && (celula.Y + j) >= 0 && (celula.Y + j
		 * < matrix.Ambient.GetLength(1)))
		 * NeighborsCells.Add(matrix.Ambient[celula.X + i, celula.Y + j]); }
		 */

		return neighbors;
	}

	public Integer defaultMove() {

		return 0;
	}

	public void run(ArrayList<Position> neighbors) {

	}
}