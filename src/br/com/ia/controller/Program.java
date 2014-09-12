package br.com.ia.controller;

import br.com.ia.agents.Collector;
import br.com.ia.utils.Position;

public class Program {

	public static void main(String[] args) {
		int size = 8;

		Matrix m = new Matrix();
		
		m.createMatix();
		
		Collector c = new Collector("c1", new Position(0, 0));
	}
}