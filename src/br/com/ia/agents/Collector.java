package br.com.ia.agents;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import br.com.ia.utils.CollectorStatus;
import br.com.ia.utils.Direction;
import br.com.ia.utils.Position;
import br.com.ia.utils.PositionTrashCan;
import br.com.ia.utils.TrashType;

public class Collector extends Agent {

	private ArrayList<Position> glassTrashCans;
	private List<Trash> glassTrash;
	
	private ArrayList<Position> metalTrashCans;
	private List<Trash> metalTrash;
	
	private ArrayList<Position> paperTrashCans;
	private List<Trash> paperTrash;
	
	private ArrayList<Position> plasticTrashCans;
	private List<Trash> plasticTrash;
	
	private ArrayList<Position> rechargers;

	private ArrayList<Block> neighbors;
	private ArrayList<Block> possibleBlocks;
	private ArrayList<Block> visitedBlocks;
	
	private CollectorStatus status;

	private int batteryCharge;

	private Integer capacity;
	private boolean isFull;

	private Position objective;

	private static String icon = "img/collector.png";

	public Collector(String name, Integer capacity, Position position) {
		super(name, icon, position);

		// rechargers
		rechargers = new ArrayList<Position>();
		
		// trash cans
		glassTrashCans = new ArrayList<Position>();
		metalTrashCans = new ArrayList<Position>();
		paperTrashCans = new ArrayList<Position>();
		plasticTrashCans = new ArrayList<Position>();
		
		// garbage
		glassTrash = Arrays.asList(new Trash[capacity]);
		metalTrash = Arrays.asList(new Trash[capacity]);
		paperTrash = Arrays.asList(new Trash[capacity]);
		plasticTrash = Arrays.asList(new Trash[capacity]);
	}

	public Collector(String name, Position position) {
		super(name, icon, position);
		
		// rechargers
		rechargers = new ArrayList<Position>();

		// trash cans
		glassTrashCans = new ArrayList<Position>();
		metalTrashCans = new ArrayList<Position>();
		paperTrashCans = new ArrayList<Position>();
		plasticTrashCans = new ArrayList<Position>();
		
		int rdn = (int) Math.random() * 20;
		glassTrash = Arrays.asList(new Trash[rdn]);
		metalTrash = Arrays.asList(new Trash[rdn]);
		paperTrash = Arrays.asList(new Trash[rdn]);
		plasticTrash = Arrays.asList(new Trash[rdn]);
	}

	public boolean addTrashCan(TrashType trashType, int x, int y) {
		Position pos = new Position(x, y);

		switch (trashType) {
			case GLASS:
				if (glassTrashCans.contains(pos)) {
					return false;
				}
				
				glassTrashCans.add(pos);
				break;
			case METAL:
				if (metalTrashCans.contains(pos)) {
					return false;
				}
				
				metalTrashCans.add(pos);
				break;
			case PAPER:
				if (paperTrashCans.contains(pos)) {
					return false;
				}
				
				paperTrashCans.add(pos);
				break;
			case PLASTIC:
				if (plasticTrashCans.contains(pos)) {
					return false;
				}
				
				plasticTrashCans.add(pos);
			default:
				break;
		}

		return true;
	}

	public boolean addRecharger(int x, int y) {
		Position pos = new Position(x, y);

		if (rechargers.contains(pos)) {
			return false;
		}

		return rechargers.add(pos);
	}

	/**
	 * Execute action
	 * @param neighbors
	 */
	public void run(ArrayList<Block> neighbors) {
		if ((neighbors == null) || (neighbors.size() == 0)) {
			// No romm to walk. :(
			return;
		}

		this.neighbors = neighbors;
		this.possibleBlocks = getPossibleBlocks();

		/*
		if (this.isBatteryLow()) {
			// TODO:verificar bateria
		} else if (this.isFull) {
			status = CollectorStatus.LOOKINGTRASHCAN;
			// TODO: Ver o que ele deve fazer
		}

		if (hasTrash()) {
			status = CollectorStatus.LOOKINGTRASH;
		} else {
			status = CollectorStatus.WANDER;
		}
		*/
		
		observe();

		plan();

		act();
	}
	
	private void observe() {
		if (status != CollectorStatus.LOOKINGRECHARGER && batteryLow()) {
			status = CollectorStatus.LOOKINGRECHARGER;
			return;
		}
		
		//if (trashFull()) {
			
		//}
	}
	
	/**
	 * Plans next step.
	 */
	private void plan() {
		switch (status) {
			case LOOKINGRECHARGER:
				Position.getPseudoNearest(this.getPosition(), rechargers);
				break;
			case WALKINGRECHARGER:
				
				break;
			case LOOKINGTRASHCAN:
				//getNearestTrashCan();
				break;
			case WALKINGTRASHCAN:
				
				break;
			case LOOKINGTRASH:
				
				break;
			case WANDER:
	
				break;
			default:
				// Oops. :(
				break;
		}

		return;
	}

	/**
	 * Act according to what was planned
	 */
	private void act() {

	}

	//Incompleto....
	public Block defaultMovement(){
		Block block = null;
		if(possibleBlocks.size() ==0)
			return block;
		
		for (Block possibleBlock : possibleBlocks) {
			if(validateLimit(possibleBlock, Direction.RIGHT)){
				block = possibleBlock;
				
				visitedBlocks.add(possibleBlock);
				possibleBlocks.remove(possibleBlock);
				
				block.getPosition().setX(block.getPosition().getX() + 1);
				continue;
			}else if(validateLimit(possibleBlock, Direction.DOWN)){
				block = possibleBlock;
				block.getPosition().setY(block.getPosition().getY() + 1);
				continue;
			}
		}

		return block;
	}

	//Incompleto...
	private boolean validateLimit(Block block, Direction direction) {
		for (Block neighbor : neighbors) {
			switch (direction) {
			case UP:
				if (neighbor.getPosition().getX() == block.getPosition().getX()
						&& neighbor.getPosition().getY() == block.getPosition()
								.getY() - 1)
					return true;
			case RIGHT:
				if (neighbor.getPosition().getX() == block.getPosition().getX() + 1
						&& neighbor.getPosition().getY() == block.getPosition()
								.getY())
					return true;
			case DOWN:
				if (neighbor.getPosition().getX() == block.getPosition().getX()
						&& neighbor.getPosition().getY() == block.getPosition()
								.getY() + 1)
					return true;
			case LEFT:
				if (neighbor.getPosition().getX() == block.getPosition().getX() - 1
						&& neighbor.getPosition().getY() == block.getPosition()
								.getY())
					return true;
			default:
				break;
			}
		}

		return true;
	}

	private boolean hasTrash() {
		for (Block block : neighbors) {
			if (block instanceof Trash) {
				// TODO: Verificar o lixo mais perto
				objective = block.getPosition();
				return true;
			}
		}

		return false;
	}

	private ArrayList<Block> getPossibleBlocks() {
		ArrayList<Block> possibleBlocks = new ArrayList<Block>();

		for (Block block : neighbors) {
			for (int x = -1; x <= 1; x++) {
				for (int y = -1; y <= 1; y++) {
					if ((x == 0) && (y == 0)) {
						continue;
					}

					int relX = (getPosition().getX() + x);
					int relY = (getPosition().getY() + y);

					if (block.getPosition().getX() == relX
							&& block.getPosition().getY() == relY) {
						possibleBlocks.add(block);
					}
				}
			}
		}
		return possibleBlocks;
	}

	private boolean batteryLow() {
		double res = Double.MAX_VALUE;
		for (Position position : rechargers) {
			double p = Position.getDiference(this.getPosition(), position);
			if (res < p) {
				res = p;
			}
		}
		
		return batteryCharge <= ((int)res) + 5;
	}
	
	private boolean trashCanFull() {
		// TODO: Impl.
		return false;
	}
	
	private void getNearestTrashCan(TrashType type) {
		switch (type) {
			case GLASS:
				objective = Position.getPseudoNearest(this.getPosition(), glassTrashCans);
				break;
			case METAL:
				objective = Position.getPseudoNearest(this.getPosition(), metalTrashCans);
				break;
			case PAPER:
				objective = Position.getPseudoNearest(this.getPosition(), paperTrashCans);
				break;
			case PLASTIC:
				objective = Position.getPseudoNearest(this.getPosition(), plasticTrashCans);
				break;
			default:
				// Oops. :(
				break;
		}
	}
	
	
	
	
	public CollectorStatus getStatus() {
		return status;
	}

	public void setStatus(CollectorStatus status) {
		this.status = status;
	}
	

	public int getBattery() {
		return batteryCharge;
	}
	

	public void setBatteryCharge(int batteryCharge) {
		this.batteryCharge = batteryCharge;
	}
	

	public Integer getCapacity() {
		return capacity;
	}
	

	public void setCapacity(Integer capacity) {
		this.capacity = capacity;
	}
	

	public boolean isFull() {
		return isFull;
	}
	

	public void setIsFull(boolean isFull) {
		this.isFull = isFull;
	}
}