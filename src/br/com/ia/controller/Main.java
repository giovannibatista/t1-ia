package br.com.ia.controller;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.ia.agents.Agent;
import br.com.ia.agents.Collector;
import br.com.ia.agents.Recharger;
import br.com.ia.agents.TrashCan;
import br.com.ia.utils.TrashCanType;
import br.com.ia.utils.TrashCanTypeGenerator;

@ViewScoped
@ManagedBean
public class Main {

	private Agent[][] agents;
	private Integer columns;
	private Integer rows;

	private Integer amountTrashCans;
	private Integer amountRecharges;

	private Collector collector;

	@PostConstruct
	public void init() {
		System.out.println(" Bean executado! ");

		// Valores iniciais
		columns = 10;
		amountTrashCans = 4;
		amountRecharges = 1;
	}

	public void run() {
		rows = columns;
		agents = new Agent[rows][columns];

		collector = new Collector("Coletor1", 0, 0);

		agents[0][0] = collector;

		for (int i = 0; i < amountTrashCans; i++) {
			insertTrashCan();
		}

		for (int i = 0; i < amountRecharges; i++) {
			insertRecharges();
		}

	}

	public void next() {

		/*if (!hasElement(collector.getAxisX(), collector.getAxisY() + 1)) {
			agents[collector.getAxisX()][collector.getAxisY()] = null;
			collector.setAxisY(collector.getAxisY() + 1);
			agents[collector.getAxisX()][collector.getAxisY()] = collector;
		}*/

	}

	public void insertTrashCan() {
		Integer x = (int) (Math.random() * 10);
		Integer y = (int) (Math.random() * 10);

		if (hasElement(x, y))
			insertTrashCan();

		TrashCanType trashCanType = TrashCanTypeGenerator.next();
		String img = TrashCanTypeGenerator.getTrashCanTypeIcon(trashCanType);
		TrashCan trashCan = new TrashCan("Lixeira", img, 10, TrashCanTypeGenerator.next(), x, y);

		agents[x][y] = trashCan;

		collector.addTrashCan(x, y);

	}

	public void insertRecharges() {
		Integer x = (int) (Math.random() * 10);
		Integer y = (int) (Math.random() * 10);

		if (hasElement(x, y))
			insertRecharges();

		Recharger recharge = new Recharger("Recarga", x, y);

		agents[x][y] = recharge;

		collector.addRecharger(x, y);

	}

	// Validar posicao dos agentes
	public boolean hasElement(Integer x, Integer y) {
		System.out.println("----------------->>" + x + ", " + y);

		if (agents[x][y] != null) {
			return true;
		}

		/*
		 * if(x > 0 && x < rows){ if(agents[x+1][y] != null) return true; else
		 * if(agents[x-1][y] != null) return true; } if(y > 0 && y < columns){
		 * if(agents[x][y+1] != null) return true; else if(agents[x][y-1] !=
		 * null) return true; } if(x > 0 && x < rows && y > 0 && y < columns){
		 * if(agents[x+1][y+1] != null) return true; else if(agents[x-1][y-1] !=
		 * null) return true; else if(agents[x+1][y-1] != null) return true;
		 * else if(agents[x-1][y+1] != null) return true; }
		 */
		return false;
	}

	public String getMessage() {
		return "Hello World JSF!";
	}

	public Agent[][] getAgents() {
		return agents;
	}

	public void setAgents(Agent[][] agents) {
		this.agents = agents;
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

	public Integer getAmountTrashCans() {
		return amountTrashCans;
	}

	public void setAmountTrashCans(Integer amountTrashCans) {
		this.amountTrashCans = amountTrashCans;
	}

	public Integer getAmountRecharges() {
		return amountRecharges;
	}

	public void setAmountRecharges(Integer amountRecharges) {
		this.amountRecharges = amountRecharges;
	}

	public Collector getCollector() {
		return collector;
	}

	public void setCollector(Collector collector) {
		this.collector = collector;
	}

}
