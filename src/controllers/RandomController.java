package controllers;

import model.randomMatrixGeneration.*;

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
