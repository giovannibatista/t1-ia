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

		//insertDefaultTrashCan();
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
		Collector col = collectors.get(0);
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
	}

	private void insertCollector(Integer index) {
		Position position = Position.getRandomPosition(rows);

		if (hasElement(position))
			insertCollector(index);

		Collector collector = new Collector("Coletor" + index, position);

		matrix[position.getX()][position.getY()] = collector;
		collectors.add(collector);

	}

	private void insertDefaultTrashCan() {

		Position position1 = Position.getRandomPosition(rows);
		Position position2 = Position.getRandomPosition(rows);
		Position position3 = Position.getRandomPosition(rows);
		Position position4 = Position.getRandomPosition(rows);

		if (hasElement(position1) || hasElement(position2)
				|| hasElement(position3) || hasElement(position4))
			insertTrashCan();

		String img = TrashTypeGenerator.getTrashCanIcon(TrashType.GREEN);
		TrashCan trashCan1 = new TrashCan("Lixeira", img, 10, TrashType.GREEN,
				position1);

		img = TrashTypeGenerator.getTrashCanIcon(TrashType.BLUE);
		TrashCan trashCan2 = new TrashCan("Lixeira", img, 10, TrashType.BLUE,
				position2);

		img = TrashTypeGenerator.getTrashCanIcon(TrashType.RED);
		TrashCan trashCan3 = new TrashCan("Lixeira", img, 10, TrashType.RED,
				position3);

		img = TrashTypeGenerator.getTrashCanIcon(TrashType.YELLOW);
		TrashCan trashCan4 = new TrashCan("Lixeira", img, 10, TrashType.YELLOW,
				position4);

		matrix[trashCan1.getPosition().getX()][trashCan1.getPosition().getY()] = trashCan1;
		matrix[trashCan2.getPosition().getX()][trashCan2.getPosition().getY()] = trashCan2;
		matrix[trashCan3.getPosition().getX()][trashCan3.getPosition().getY()] = trashCan3;
		matrix[trashCan4.getPosition().getX()][trashCan4.getPosition().getY()] = trashCan4;

		for (Collector collector : collectors) {
			collector.addTrashCan(trashCan1.getColor(), trashCan1.getPosition()
					.getX(), trashCan1.getPosition().getY());
			collector.addTrashCan(trashCan2.getColor(), trashCan2.getPosition()
					.getX(), trashCan1.getPosition().getY());
			collector.addTrashCan(trashCan3.getColor(), trashCan3.getPosition()
					.getX(), trashCan1.getPosition().getY());
			collector.addTrashCan(trashCan4.getColor(), trashCan4.getPosition()
					.getX(), trashCan1.getPosition().getY());
		}

	}

	private void insertTrashCan() {
		Position position = Position.getRandomPosition(rows);

		if (hasElement(position))
			insertTrashCan();

		TrashType trashCanType = TrashTypeGenerator.next();
		String img = TrashTypeGenerator.getTrashCanIcon(trashCanType);
		TrashCan trashCan = new TrashCan("Lixeira", img, 10,
				trashCanType, position);

		matrix[trashCan.getPosition().getX()][trashCan.getPosition().getY()] = trashCan;

		for (Collector collector : collectors) {
			collector.addTrashCan(trashCan.getColor(), trashCan.getPosition()
					.getX(), trashCan.getPosition().getY());
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
