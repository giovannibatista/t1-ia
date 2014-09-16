package br.com.ia.controller;

import java.util.ArrayList;
import br.com.ia.agents.Block;
import br.com.ia.agents.Collector;
import br.com.ia.utils.Position;

public class Program {

	public static void main(String[] args) {
		//int size = 8;

		Matrix m = new Matrix();
		
		m.createMatrix();
		
		Collector c = new Collector("c1", new Position(2, 2));
		//c.run();
		
		ArrayList<Block> n = m.getNeighbors(c.getPosition());
		
		for (Block block : n) {
			System.out.println(block.getPosition());
		}
	}
}