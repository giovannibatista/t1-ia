package br.com.ia.controller;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import org.primefaces.context.RequestContext;

@ViewScoped
@ManagedBean
public class Main {

	private Matrix matrix;
	
	private Integer amountCollectors;
	private Integer amountTrashCans;
	private Integer amountRechargers;
	

	@PostConstruct
	public void init() {
		System.out.println(" Bean executado! ");
		amountCollectors = 1;
		amountTrashCans = 4;
		amountRechargers = 1;
	}

	public void createMatix(ActionEvent event) {
		amountCollectors = 1;
		amountTrashCans = 4;
		amountRechargers = 1;
		matrix = new Matrix(amountCollectors, amountTrashCans, amountRechargers);
		matrix.createMatix();
		
		RequestContext.getCurrentInstance().update("agents");
	}

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
	
	
	
	


}
