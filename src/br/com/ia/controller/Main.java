package br.com.ia.controller; 
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

import br.com.ia.agents.Agent;
import br.com.ia.agents.Collector;

@RequestScoped 
@ManagedBean
public class Main {

	private Agent[][] agents;
	private String [] onearray = new String[10];
	private Integer columns;
	private Integer rows;


	@PostConstruct 
	public void init(){
		System.out.println(" Bean executado! ");
		for (int i = 0; i < onearray.length; i++) {
			onearray[i] = String.valueOf(i);
		}
	}

	public void run(){
		agents = new Agent[rows][columns];
				for (int row = 0; row < agents.length; row++) {
				for (int col = 0; col < agents[row].length; col++) {
					agents[row][col] = new Collector("Agente (" +  Math.round(row) +", "+Math.round(col) + ")", row, col);
				}
			}
			
		
	}


	public String getMessage(){ 
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








}
