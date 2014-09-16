package br.com.ia.agents;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import br.com.ia.utils.CollectorStatus;
import br.com.ia.utils.Position;
import br.com.ia.utils.PositionTrashCan;
import br.com.ia.utils.TrashType;

public class Collector extends Agent {

	private ArrayList<PositionTrashCan> trashCans;
	private ArrayList<Position> rechargers;

	private ArrayList<Block> neighbors;
	private ArrayList<Block> possibleBlocks;
	private ArrayList<Block> visitedBlocks;
	private List<Trash> trashes;

	private CollectorStatus status;

	private int battery;
	private boolean batteryLow;

	private Integer capacity;
	private boolean isFull;

	private Block objective;

	private static String icon = "img/collector.png";

	public Collector(String name, Integer capacity, Position position) {
		super(name, icon, position);
		trashCans = new ArrayList<PositionTrashCan>();
		rechargers = new ArrayList<Position>();
		trashes = Arrays.asList(new Trash[capacity]);
	}

	public Collector(String name, Position position) {
		super(name, icon, position);
		trashCans = new ArrayList<PositionTrashCan>();
		rechargers = new ArrayList<Position>();
		trashes = Arrays.asList(new Trash[(int) Math.random() * 20]);
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

	public void run(ArrayList<Block> neighbors) {
		if ((neighbors == null) || (neighbors.size() == 0)) {
			// No romm to walk. :(
			return;
		}
		
		this.neighbors = neighbors;

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
		
		plan();
		
		act();
	}

	/**
	 * Plans next step.
	 */
	private void plan() {
		if (status != CollectorStatus.LOOKINGRECHARGER && batteryLow) {
			status = CollectorStatus.LOOKINGRECHARGER;
		}
		
		switch (status) {
			case LOOKINGTRASH:
				break;
			case LOOKINGRECHARGER:
				break;
			case LOOKINGTRASHCAN:
				break;
			case WANDER:
				break;
			default:
				break;
		}

		return;
	}

	/**
	 * Act according to what was planned
	 */
	private void act() {

	}
	
	private boolean hasTrash() {
		for (Block block : neighbors) {
			if (block instanceof Trash) {
				// TODO: Verificar o lixo mais perto
				objective = (Block)block;
				return true;
			}
		}
		
		return false;
	}

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	public CollectorStatus getStatus() {
		return status;
	}

	public void setStatus(CollectorStatus status) {
		this.status = status;
	}

	public int getBattery() {
		return battery;
	}

	public void setBattery(int battery) {
		this.battery = battery;
	}

	public boolean isBatteryLow() {
		return batteryLow;
	}

	public void setBatteryLow(boolean batteryLow) {
		this.batteryLow = batteryLow;
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