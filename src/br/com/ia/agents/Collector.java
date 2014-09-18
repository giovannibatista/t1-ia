package br.com.ia.agents;

import java.util.ArrayList;
import br.com.ia.utils.CollectorStatus;
import br.com.ia.utils.Direction;
import br.com.ia.utils.Position;
import br.com.ia.utils.TrashType;
import br.com.ia.utils.Way;

public class Collector extends Agent {
	private static String icon = "img/collector.png";
	
	/* KNOWLEDGE ABOUT THE WORLD */
	private ArrayList<Position> glassTrashCans;
	private ArrayList<Position> metalTrashCans;
	private ArrayList<Position> paperTrashCans;
	private ArrayList<Position> plasticTrashCans;
	private ArrayList<Position> rechargers;

	private ArrayList<Block> neighbors;
	
	/* KNOWLEDGE ABOUT ITSELF */
	private ArrayList<Trash> glassTrash;
	private ArrayList<Trash> metalTrash;
	private ArrayList<Trash> paperTrash;
	private ArrayList<Trash> plasticTrash;
	
	private Block currentBlock;
	
	private int maxBatteryCapacity;
	private int maxTrashCapacity;
	
	private int batteryCharge;
	
	private CollectorStatus status;
	
	private Way way;
	private Direction direction;
	
	/* CONTROL VARIABLES */	
	private Position objective;
	private TrashType trashType;
	private ArrayList<Block> possibleBlocks;
	private ArrayList<Block> visitedBlocks;
	
	/* CONSTRUCTOR */
	public Collector(String name, Integer batteryCapacity, Integer trashCapacity, Block current) {
		super(name, icon);

		// current position
		this.currentBlock = current;
		
		// rechargers
		rechargers = new ArrayList<Position>();
		
		// trash cans
		glassTrashCans = new ArrayList<Position>();
		metalTrashCans = new ArrayList<Position>();
		paperTrashCans = new ArrayList<Position>();
		plasticTrashCans = new ArrayList<Position>();
		
		// garbage
		glassTrash = new ArrayList<Trash>();
		metalTrash = new ArrayList<Trash>();
		paperTrash = new ArrayList<Trash>();
		plasticTrash = new ArrayList<Trash>();
		
		maxBatteryCapacity = batteryCapacity;
		maxTrashCapacity = trashCapacity;
		
		batteryCharge = batteryCapacity;
		
		direction = Direction.NONE;
		
		status = CollectorStatus.WANDER;
	}
	
	/* CONFIGURATION METHODS */
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

	/* PUBLIC METHODS */
	/**
	 * Execute action
	 * @param neighbors
	 */
	public Block run(ArrayList<Block> neighbors) {
		if ((neighbors == null) || (neighbors.size() == 0)) {
			// No romm to walk. :(
			return null;
		}

		this.neighbors = neighbors;
		this.possibleBlocks = getPossibleBlocks();

		observe();

		plan();

		return act();
	}
	
	/**
	 * Observe its state and prepare to plan the next move.
	 */
	private void observe() {
		if (status != CollectorStatus.LOOKINGRECHARGER && batteryLow()) {
			status = CollectorStatus.LOOKINGRECHARGER;
			return;
		}
		
		if (hasFullTrash()) {
			status = CollectorStatus.LOOKINGTRASHCAN;
			return;
		}
		
		if (hasTrash()) {
			status = CollectorStatus.LOOKINGTRASH;
			return;
		}
	}
	
	/**
	 * Plans the next move.
	 */
	private void plan() {
		switch (status) {
			case LOOKINGRECHARGER:
				objective = Position.getPseudoNearest(currentBlock.getPosition(), rechargers);
				status = CollectorStatus.WALKINGRECHARGER;
				break;
			case WALKINGRECHARGER:
				
				break;
			case LOOKINGTRASHCAN:
				objective = getNearestTrashCan();
				status = CollectorStatus.WALKINGTRASHCAN;
				break;
			case WALKINGTRASHCAN:
				
				break;
			case LOOKINGTRASH:
				objective = Position.getPseudoNearest(currentBlock.getPosition(), getTrashFound());
				status = CollectorStatus.WALKINGTRASH;
				break;
			case WALKINGTRASH:
				
				break;
			case WANDER:
			default:
				status = CollectorStatus.WANDER;
				objective = wander().getPosition();
				break;
		}
	}

	/**
	 * Act according to what was planned
	 */
	private Block act() {
		if (currentBlock.equals(objective)) {
			if (status == CollectorStatus.WALKINGTRASH) {
				collectTrash();
				return null;
			}
		}
		
		for (Block block : possibleBlocks) {
			if (block.getPosition().equals(objective)) {
				return block;
			}
		}
		
		// A*
		
		
		return null;
	}
	
	private void collectTrash() {
		Trash trash = currentBlock.collectTrash();
		switch (trash.getTrashType()) {
			case GLASS:
				glassTrash.add(trash);
				break;
			case METAL:
				metalTrash.add(trash);
				break;
			case PAPER:
				paperTrash.add(trash);
				break;
			case PLASTIC:
				plasticTrash.add(trash);
				break;
			default:
				break;
		}
		
		status = CollectorStatus.WANDER;
	}

	private Block wander() {
		Block block = null;
		if (possibleBlocks.size() == 0) {
			return block;
		}
		
		if (direction == Direction.NONE || direction == Direction.RIGHT) {
			block = goRight();
			if (block != null) return block;
			
			block = goDown();
			if (block != null) return block;
			
			block = goLeft();
			if (block != null) return block;
			
			block = goUp();
			if (block != null) return block;
		} else if (direction == Direction.DOWN) {
			block = goLeft();
			if (block != null) return block;
			
			block = goRight();
			if (block != null) return block;
			
			block = goDown();
			if (block != null) return block;
			
			block = goUp();
			if (block != null) return block;
		} else if (direction == Direction.LEFT) {
			block = goLeft();
			if (block != null) return block;
			
			block = goDown();
			if (block != null) return block;
			
			block = goUp();
			if (block != null) return block;
		} else if (direction == Direction.UP) {
			block = goRight();
			if (block != null) return block;
			
			block = goLeft();
			if (block != null) return block;
			
			block = goUp();
			if (block != null) return block;
		}
		
		
		return block;
	}
	
	private Block goRight() {
		for (Block possibleBlock : possibleBlocks) {	
			if (possibleBlock.getPosition().getX() == currentBlock.getPosition().getX()
				&& possibleBlock.getPosition().getY() > currentBlock.getPosition().getY()) {
				direction = Direction.RIGHT;
				return possibleBlock;
			}
		}
		
		return null;
	}
	
	private Block goDown() {
		for (Block possibleBlock : possibleBlocks) {
			if (possibleBlock.getPosition().getX() > currentBlock.getPosition().getX()
				&& possibleBlock.getPosition().getY() == currentBlock.getPosition().getY()) {
				direction = Direction.DOWN;
				return possibleBlock;
			}
		}
		
		return null;
	}
	
	private Block goLeft() {
		for (Block possibleBlock : possibleBlocks) {
			if (possibleBlock.getPosition().getX() == currentBlock.getPosition().getX()
				&& possibleBlock.getPosition().getY() < currentBlock.getPosition().getY()) {
				direction = Direction.LEFT;
				return possibleBlock;
			}
		}
		
		return null;
	}
	
	private Block goUp() {
		for (Block possibleBlock : possibleBlocks) {
			if (possibleBlock.getPosition().getX() < currentBlock.getPosition().getX()
				&& possibleBlock.getPosition().getY() == currentBlock.getPosition().getY()) {
				direction = Direction.UP;
				return possibleBlock;
			}
		}
		
		return null;
	}

	private ArrayList<Block> getPossibleBlocks() {
		ArrayList<Block> possibleBlocks = new ArrayList<Block>();

		for (Block block : neighbors) {
			for (int x = -1; x <= 1; x++) {
				for (int y = -1; y <= 1; y++) {
					if ((x == 0) && (y == 0)) {
						continue;
					}

					int relX = (currentBlock.getPosition().getX() + x);
					int relY = (currentBlock.getPosition().getY() + y);

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
			double p = Position.getDiference(currentBlock.getPosition(), position);
			if (res < p) {
				res = p;
			}
		}
		
		return maxBatteryCapacity <= ((int)res) + 5;
	}
	
	private boolean hasFullTrash() {
		
		if (glassTrash.size() == maxTrashCapacity) {
			trashType = TrashType.GLASS;
			return true;
		}
		
		if (metalTrash.size() == maxTrashCapacity) {
			trashType = TrashType.METAL;
			return true;
		}
		
		if (paperTrash.size() == maxTrashCapacity) {
			trashType = TrashType.PAPER;
			return true;
		}
		
		if (plasticTrash.size() == maxTrashCapacity) {
			trashType = TrashType.PLASTIC;
			return true;
		}
		
		return false;
	}
	
	private boolean hasTrash() {
		for (Block block : neighbors) {
			if (block.hasTrash()) {
				return true;
			}
		}

		return false;
	}
	
	private ArrayList<Position> getTrashFound() {
		ArrayList<Position> trashFound = new ArrayList<Position>();
		
		for (Block block : neighbors) {
			if (block.hasTrash()) {
				trashFound.add(block.getPosition());
			}
		}
		
		return trashFound;
	}
	
	private Position getNearestTrashCan() {
		switch (trashType) {
			case GLASS:
				return Position.getPseudoNearest(currentBlock.getPosition(), glassTrashCans);
			case METAL:
				return Position.getPseudoNearest(currentBlock.getPosition(), metalTrashCans);
			case PAPER:
				return Position.getPseudoNearest(currentBlock.getPosition(), paperTrashCans);
			case PLASTIC:
				return Position.getPseudoNearest(currentBlock.getPosition(), plasticTrashCans);
			default:
				// Oops. :(
				break;
		}
		
		return null;
	}
	
	public Position getPosition() {
		return currentBlock.getPosition();
	}
	
	public void setBlock(Block block) {
		this.currentBlock = block;
	}
	
	public CollectorStatus getStatus() {
		return status;
	}
}