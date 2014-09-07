package br.com.ia.agents;

public class Agent {
	
	private String name;
	private String icon;
	private Integer axisX;
	private Integer axisY;

	public Agent(String name, String icon, Integer axisX, Integer axisY) {
		super();
		this.name = name;
		this.icon = icon;
		this.axisX = axisX;
		this.axisY = axisY;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getIcon() {
		return icon;
	}
	public void setIcon(String icon) {
		this.icon = icon;
	}

	public Integer getAxisX() {
		return axisX;
	}

	public void setAxisX(Integer axisX) {
		this.axisX = axisX;
	}

	public Integer getAxisY() {
		return axisY;
	}

	public void setAxisY(Integer axisY) {
		this.axisY = axisY;
	}
	
	
	
	

}
