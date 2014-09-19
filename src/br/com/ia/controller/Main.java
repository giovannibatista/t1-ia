package br.com.ia.controller;

import java.util.ArrayList;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.primefaces.context.RequestContext;

import br.com.ia.agents.Block;
import br.com.ia.agents.Collector;
import br.com.ia.agents.Recharger;
import br.com.ia.agents.Trash;
import br.com.ia.agents.TrashCan;
import br.com.ia.utils.Position;
import br.com.ia.utils.TrashType;
import br.com.ia.utils.TrashTypeGenerator;

@ViewScoped
@ManagedBean
public class Main {

	private Integer amountCollectors;
	private Integer amountTrashCans;
	private Integer amountRechargers;
	
	private Integer maxTrashCanCapacity;
	private Integer maxTrashCapacity;
	private Integer maxBatteryCapacity;
	
	private Matrix matrix;
	private ArrayList<Collector> collectors;
	private ArrayList<TrashCan> trashCans;
	private ArrayList<Recharger> rechargers;
	
	@PostConstruct
	public void init() {
		System.out.println(" Online! ");
		amountCollectors = 1;
		amountTrashCans = 4;
		amountRechargers = 1;
		maxTrashCanCapacity = 5;
		maxTrashCapacity = 3;
		maxBatteryCapacity = 10;
		initArrayList();
	}

	public void createMatrix() {
		initArrayList();
		matrix = new Matrix(amountCollectors, amountTrashCans, amountRechargers);
		matrix.createMatrix();

		createEnvironment();
		RequestContext.getCurrentInstance().update("agents");
	}

	public void createMatrixTestMoving() {
		amountCollectors = 1;
		amountTrashCans = 0;
		amountRechargers = 0;
		maxTrashCanCapacity = 5;
		maxTrashCapacity = 3;
		maxBatteryCapacity = 10;
		initArrayList();
		matrix = new Matrix(amountCollectors, amountTrashCans, amountRechargers);
		matrix.createMatrix();
		//matrix.createMatrixTestMoving();
		
		createEnvironment();
		RequestContext.getCurrentInstance().update("agents");
	}
	
	

	public void next() {
		for (Collector c : collectors) {
			System.out.println("\nColetor: " + c.getName() + " em " + c.getPosition() + " Status: " + c.getStatus());
			
			Block from = matrix.getBlock(c.getPosition());
			Block to = c.run(matrix.getNeighbors(c.getPosition()));
			
			if (to != null) {
				matrix.move(c, from, to);
				System.out.println("* Moveu para " + to.getPosition());
			}
			
			System.out.println("Status: " + c.toString());
			System.out.println();
		}
		
		RequestContext.getCurrentInstance().update("agents");
	}

	private void createEnvironment() {
		for (int i = 0; i < amountCollectors; i++) {
			insertCollector(i);
		}

		for (int i = 0; i < amountTrashCans; i++) {
			insertTrashCan(i);
		}

		for (int i = 0; i < amountRechargers; i++) {
			insertRechargers();
		}
	
		insertTrash();
	}
	
	private void insertCollector(Integer index) {
		Position position = Position.getRandomPosition(matrix.getSize());

		if (matrix.hasAgent(position))
			insertCollector(index);
		
		Collector collector = new Collector("C" + index, maxBatteryCapacity, maxTrashCapacity, matrix.getBlock(position));

		matrix.add(position, collector);
		collectors.add(collector);
	}
	
	private void insertTrashCan(Integer index) {
		Position position = Position.getRandomPosition(matrix.getSize());

		if (matrix.hasAgent(position))
			insertTrashCan(index);

		TrashType trashCanType = TrashTypeGenerator.next();
		
		String img = TrashTypeGenerator.getTrashCanIcon(trashCanType);
		TrashCan trashCan = new TrashCan("L" + index, img, maxTrashCanCapacity, trashCanType);

		matrix.add(position, trashCan);
		trashCans.add(trashCan);

		for (Collector collector : collectors) {
			collector.addTrashCan(trashCan.getColor(), position.getX(), position.getY());
		}
	}

	private void insertRechargers() {
		Position position = Position.getRandomPosition(matrix.getSize());

		if (matrix.hasAgent(position))
			insertRechargers();

		Recharger recharger = new Recharger();

		matrix.add(position, recharger);
		rechargers.add(recharger);

		for (Collector collector : collectors) {
			collector.addRecharger(position.getX(), position.getY());
		}
	}
	
	private void insertTrash() {
		Integer freeBlocks = (matrix.getSize() ^ 2) - (amountTrashCans + amountRechargers + amountCollectors);
		Integer amountTrashes = (int)(Math.random() * freeBlocks) + (matrix.getSize() / 2);

		if (amountTrashes == 0) {
			insertTrash();
		}

		for (int i = 0; i < amountTrashes; i++) {
			Position position = Position.getRandomPosition(matrix.getSize());

			if (!matrix.hasAgent(position)) {
				TrashType trashType = TrashTypeGenerator.getRandomTrashType();
				String img = TrashTypeGenerator.getTrashIcon(trashType);
				Trash trash = new Trash(trashType, img);
				matrix.add(position, trash);
			}
		}
	}
	
	private void initArrayList(){
		collectors = new ArrayList<Collector>();
		trashCans = new ArrayList<TrashCan>();
		rechargers = new ArrayList<Recharger>();
	}
	
	/* GETTERS AND SETTERS */
	public Matrix getMatrix() {
		return matrix;
	}

	public void setMatrix(Matrix matrix) {
		this.matrix = matrix;
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

	public Integer getMaxTrashCanCapacity() {
		return maxTrashCanCapacity;
	}

	public void setMaxTrashCanCapacity(Integer maxTrashCanCapacity) {
		this.maxTrashCanCapacity = maxTrashCanCapacity;
	}

	public Integer getMaxTrashCapacity() {
		return maxTrashCapacity;
	}

	public void setMaxTrashCapacity(Integer maxTrashCapacity) {
		this.maxTrashCapacity = maxTrashCapacity;
	}

	public Integer getMaxBatteryCapacity() {
		return maxBatteryCapacity;
	}

	public void setMaxBatteryCapacity(Integer maxBatteryCapacity) {
		this.maxBatteryCapacity = maxBatteryCapacity;
	}
	
	
}