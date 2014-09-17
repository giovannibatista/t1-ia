package br.com.ia.utils;

import java.util.ArrayList;

public class Position {
	private Integer x;
	private Integer y;

	public Position(Integer x, Integer y) {
		this.x = x;
		this.y = y;
	}

	public int hashCode() {
		int hashX = (x != null ? x.hashCode() : 0);
		int hashY = (y != null ? y.hashCode() : 0);

		return (hashX + hashY) * hashY + hashX;
	}

	public boolean equals(Object other) {
		if (!(other instanceof Position)) {
			return false;
		}

		Position otherPair = (Position) other;

		return ((this.x == otherPair.x || (this.x != null && otherPair.x != null && this.x.equals(otherPair.x))) 
			 && (this.y == otherPair.y || (this.y != null && otherPair.y != null && this.y.equals(otherPair.y))));
	}

	public static Position getRandomPosition(Integer seed) {
		Position position = null;
		Integer x = (int) (Math.random() * seed);
		Integer y = (int) (Math.random() * seed);
		position = new Position(x, y);
		return position;
	}
	
	public static double getDiference(Position origin, Position destiny) {
		double res = 0;
		
		if (origin == null || destiny == null) {
			return res;
		}
		
		return Math.sqrt(Math.pow((destiny.getX() - origin.getX()), 2) + Math.pow((destiny.getY() - origin.getY()), 2));
	}

	public static Position getPseudoNearest(Position origin, ArrayList<Position> destinies) {
		Position res = null;
		double distance = Double.MAX_VALUE;
		
		for (Position position : destinies) {
			double p = getDiference(origin, position);
			if (p < distance) {
				distance = p;
				res = position;
			}
		}
		
		return res;
	}
	
	public String toString() {
		return "(" + x + ", " + y + ")";
	}

	public Integer getX() {
		return x;
	}

	public Integer getY() {
		return y;
	}

	public void setX(Integer x) {
		this.x = x;
	}

	public void setY(Integer y) {
		this.y = y;
	}

}