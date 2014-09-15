package br.com.ia.controller;

import java.util.ArrayList;

import br.com.ia.agents.Agent;
import br.com.ia.agents.Block;
import br.com.ia.agents.Collector;
import br.com.ia.agents.Recharger;
import br.com.ia.agents.Trash;
import br.com.ia.agents.TrashCan;
import br.com.ia.utils.Position;
import br.com.ia.utils.TrashType;
import br.com.ia.utils.TrashTypeGenerator;

public class Matrix {

	private Block[][] matrix;
	private ArrayList<Collector> collectors;
	private Integer columns;
	private Integer rows;

	private Integer amountCollectors;
	private Integer amountTrashCans;
	private Integer amountRechargers;

	private Collector collector;

	public Matrix() {
		// Valores iniciais
		columns = 15;
		rows = columns;
		amountCollectors = 1;
		amountTrashCans = 4;
		amountRechargers = 1;
		collectors = new ArrayList<Collector>();
	}

	public Matrix(Integer amountCollectors, Integer amountTrashCans,
			Integer amountRechargers) {
		columns = 15;
		rows = columns;
		collectors = new ArrayList<Collector>();
		this.amountCollectors = amountCollectors;
		this.amountTrashCans = amountTrashCans;
		this.amountRechargers = amountRechargers;
	}

	public void createMatrix() {
		matrix = new Block[rows][columns];
		for (int i = 0; i < matrix.length; i++) {
			for (int j = 0; j < matrix[i].length; j++) {
				if (matrix[i][j] == null) {
					matrix[i][j] = new Block(i, j);
				}
			}
		}

		for (int i = 0; i < amountCollectors; i++) {
			insertCollector(i);
		}

		for (int i = 0; i < amountTrashCans; i++) {
			insertTrashCan();
		}

		for (int i = 0; i < amountRechargers; i++) {
			insertRecharges();
		}

		insertTrashes();
	}

	public void createMatrixTestMoving() {
		matrix = new Block[rows][columns];
		for (int i = 0; i < matrix.length; i++) {
			for (int j = 0; j < matrix[i].length; j++) {
				if (matrix[i][j] == null) {
					matrix[i][j] = new Block(i, j);
				}
			}
		}
		Position position = new Position(0, 0);
		Collector collector = new Collector("Coletor", position);

		matrix[position.getX()][position.getY()] = collector;
		collectors.add(collector);

	}

	public void next() {
		//Collector col = collectors.get(0);
		//col.getNeighbors(this);
		
		
		/*
		matrix[col.getPosition().getX()][col.getPosition().getY()] = new Block(
				col.getPosition());
		col.getPosition().setX(col.getPosition().getX());
		if (col.getPosition().getY() < columns - 1)
			col.getPosition().setY(col.getPosition().getY() + 1);
		else {
			col.getPosition().setX(col.getPosition().getX() + 1);
			col.getPosition().setY(0);
		}
		matrix[col.getPosition().getX()][col.getPosition().getY()] = col;
		*/
	}
	
	/**
	 * Get available neighbors of a specific position.
	 * @param pos
	 * @return list of neighbors
	 */
	public ArrayList<Position> getNeighbors(Position pos) {
		ArrayList<Position> neighbors = new ArrayList<Position>();

		for (int x = -2; x <= 2; x++) {
			for (int y = -2; y <= 2; y++) {
				// same position
				if ((x == 0) && (y == 0)) {
					continue;
				}
				
				int relX = (pos.getX() + x);
				int relY = (pos.getY() + y);
				
				// out of bounds for column
				if ((relX < 0) || (relX >= columns)) {
					continue;
				}
				
				// out of bounds for row
				if ((relY < 0) || (relY >= rows)) {
					continue;
				}
				
				// occupied
				if  (matrix[relX][relY] instanceof Agent) {
					continue;
				}
				
				// if it made this far, add :)
				neighbors.add(new Position(relX, relY));
			}
		}

		return neighbors;
	}

	private void insertCollector(Integer index) {
		Position position = Position.getRandomPosition(rows);

		if (hasElement(position))
			insertCollector(index);

		Collector collector = new Collector("Coletor" + index, position);

		matrix[position.getX()][position.getY()] = collector;
		collectors.add(collector);

	}
	private void insertTrashCan() {
		Position position = Position.getRandomPosition(rows);

		if (hasElement(position))
			insertTrashCan();

		TrashType trashCanType = TrashTypeGenerator.next();
		String img = TrashTypeGenerator.getTrashCanIcon(trashCanType);
		TrashCan trashCan = new TrashCan("Lixeira", img, 10, trashCanType, position);

		matrix[trashCan.getPosition().getX()][trashCan.getPosition().getY()] = trashCan;

		for (Collector collector : collectors) {
			collector.addTrashCan(trashCan.getColor(), trashCan.getPosition().getX(), trashCan.getPosition().getY());
		}
	}

	private void insertRecharges() {
		Position position = Position.getRandomPosition(rows);

		if (hasElement(position))
			insertRecharges();

		Recharger recharger = new Recharger(position);

		matrix[recharger.getPosition().getX()][recharger.getPosition().getY()] = recharger;

		for (Collector collector : collectors) {
			collector.addRecharger(recharger.getPosition().getX(), recharger
					.getPosition().getY());
		}
	}

	private void insertTrashes() {
		Integer agentsInMatrix = (rows/** rows */
		) - (amountTrashCans + amountRechargers + amountCollectors);
		Integer amountTrashes = (int) (Math.random() * agentsInMatrix);

		if (amountTrashes == 0)
			insertTrashes();

		for (int i = 0; i < amountTrashes; i++) {
			Position position = Position.getRandomPosition(rows);

			if (!hasElement(position)) {
				TrashType trashType = TrashTypeGenerator.getRandomTrashType();
				String img = TrashTypeGenerator.getTrashIcon(trashType);
				Trash trash = new Trash(img, trashType, position);
				matrix[trash.getPosition().getX()][trash.getPosition().getY()] = trash;
			}
		}
	}

	private boolean hasElement(Position position) {
		Block block = matrix[position.getX()][position.getY()];
		return (block instanceof Agent || block instanceof Trash
				|| block instanceof TrashCan || block instanceof Recharger);
	}

	public Block[][] getMatrix() {
		return matrix;
	}

	public void setMatrix(Block[][] matrix) {
		this.matrix = matrix;
	}

	public Integer getColumns() {
		return columns;
	}

	public void setColumns(Integer columns) {
		this.columns = columns;
	}

	public Integer getRows() {
		return rows;
	}

	public void setRows(Integer rows) {
		this.rows = rows;
	}

	public Integer getAmountCollectors() {
		return amountCollectors;
	}

	public void setAmountCollectors(Integer amountCollectors) {
		this.amountCollectors = amountCollectors;
	}

	public Integer getAmountTrashCans() {
		return amountTrashCans;
	}

	public void setAmountTrashCans(Integer amountTrashCans) {
		this.amountTrashCans = amountTrashCans;
	}

	public Integer getAmountRechargers() {
		return amountRechargers;
	}

	public void setAmountRechargers(Integer amountRechargers) {
		this.amountRechargers = amountRechargers;
	}

	public Collector getCollector() {
		return collector;
	}

	public void setCollector(Collector collector) {
		this.collector = collector;
	}
}
