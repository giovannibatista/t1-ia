package br.com.ia.controller;

import br.com.ia.agents.Block;
import br.com.ia.agents.Collector;

public class Program {

	public static void main(String[] args) {
		int size = 8;
		
		Block[][] matrix = new Block[size][size];
		
		Collector c = new Collector("CLC1", 0, 0);
		matrix[0][0] = c;
		
		for (int i = 0; i < matrix.length; i++) {
			for (int j = 0; j < matrix[i].length; j++) {
				if (matrix[i][j] == null) {
					matrix[i][j] = new Block(i, j);
				}
			}
		}
		
		for (int i = 0; i < matrix.length; i++) {
			for (int j = 0; j < matrix[i].length; j++) {
				if (j > 0) {
					System.out.print(" ");
				}
				
				System.out.print(matrix[i][j]);
			}
			
			System.out.println();
		}
		
		/*
		int i = 0;
		while (i < 10) {
			System.out.println("Iteração " + ++i);
		}
		*/
	}
}