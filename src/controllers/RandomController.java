package controllers;

import model.randomMatrixGeneration.Generator;
import model.randomMatrixGeneration.MatrixBuilder;
import model.randomMatrixGeneration.RandomGenerator;

public class RandomController {
	private MatrixBuilder builder;
	private Generator rng;

	public RandomController() {
		rng = new RandomGenerator();
		builder = new MatrixBuilder(rng);
	}

	public int[][] getMatrix() {
		return builder.generateMatrix();
	}
}
