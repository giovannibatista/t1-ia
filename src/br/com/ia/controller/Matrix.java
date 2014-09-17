package br.com.ia.controller;

import java.util.ArrayList;

import br.com.ia.agents.Agent;
import br.com.ia.agents.Block;
import br.com.ia.agents.Collector;
import br.com.ia.agents.Recharger;
import br.com.ia.agents.Trash;
import br.com.ia.agents.TrashCan;
import br.com.ia.utils.Position;

public class Matrix {

	private Block[][] matrix;
	
	private ArrayList<Collector> collectors;
	private ArrayList<TrashCan> trashCans;
	private ArrayList<Recharger> rechargers;
	
	private Integer size;

	private Integer amountCollectors;
	private Integer amountTrashCans;
	private Integer amountRechargers;

	private Collector collector;

	public Matrix() {
		// Valores iniciais
		size = 15;
		amountCollectors = 1;
		amountTrashCans = 4;
		amountRechargers = 1;
		collectors = new ArrayList<Collector>();
		trashCans = new ArrayList<TrashCan>();
		rechargers = new ArrayList<Recharger>();
	}

	public Matrix(Integer amountCollectors, Integer amountTrashCans,
			Integer amountRechargers) {
		size = 15;
		this.amountCollectors = amountCollectors;
		this.amountTrashCans = amountTrashCans;
		this.amountRechargers = amountRechargers;
		collectors = new ArrayList<Collector>();
		trashCans = new ArrayList<TrashCan>();
		rechargers = new ArrayList<Recharger>();
	}

	public void createMatrix() {
		matrix = new Block[size][size];
		for (int i = 0; i < matrix.length; i++) {
			for (int j = 0; j < matrix[i].length; j++) {
				if (matrix[i][j] == null) {
					matrix[i][j] = new Block(i, j);
				}
			}
		}
	}
	
	public void add(Block b) {
		if (b == null)
			return;
		
		if (b instanceof Collector) {
			collectors.add((Collector) b);
		} else if (b instanceof TrashCan) {
			trashCans.add((TrashCan) b);
		} else if (b instanceof Recharger) {
			rechargers.add((Recharger) b);
		}
		
		matrix[b.getPosition().getX()][b.getPosition().getY()] = b;
	}
	
	public void move(Block from, Block to) {
		
		matrix[from.getPosition().getX()][from.getPosition().getY()] = new Block(from.getPosition());
		from.setPosition(to.getPosition());
		matrix[from.getPosition().getX()][from.getPosition().getY()] = from;
		
	}
	
	/**
	 * Get available neighbors of a specific position.
	 * @param pos
	 * @return list of neighbors
	 */
	public ArrayList<Block> getNeighbors(Position pos) {
		ArrayList<Block> neighbors = new ArrayList<Block>();

		for (int x = -2; x <= 2; x++) {
			for (int y = -2; y <= 2; y++) {
				// same position
				if ((x == 0) && (y == 0)) {
					continue;
				}
				
				int relX = (pos.getX() + x);
				int relY = (pos.getY() + y);
				
				// out of bounds for column
				if ((relX < 0) || (relX >= size)) {
					continue;
				}
				
				// out of bounds for row
				if ((relY < 0) || (relY >= size)) {
					continue;
				}
				
				// occupied
				if  (matrix[relX][relY] instanceof Agent) {
					continue;
				} 
				
				// if it made this far, add :)
				neighbors.add(matrix[relX][relY]);
			}
		}

		return neighbors;
	}

	public boolean hasElement(Position position) {
		Block block = matrix[position.getX()][position.getY()];
		return (block instanceof Agent
			|| block instanceof Trash
			|| block instanceof TrashCan
			|| block instanceof Recharger);
	}

	
	
	
	
	
	
	
	
	public Block[][] getMatrix() {
		return matrix;
	}

	public void setMatrix(Block[][] matrix) {
		this.matrix = matrix;
	}

	public Integer getSize() {
		return size;
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
