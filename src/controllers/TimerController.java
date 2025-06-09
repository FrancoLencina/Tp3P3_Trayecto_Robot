package controllers;

import model.Timer;

public class TimerController{

	private int[][] matrix;
	private Timer timer;

	public TimerController(int[][] matrix) {
		this.matrix = matrix;
		timer = new Timer();
	}

	public void runTimers() {
		timer.doBruteForceTime(matrix);
		timer.doPruningTime(matrix);
	}

	public double getBruteForceTime() {
		return timer.getBruteForceTime();
	}

	public double getPruningTime() {
		return timer.getPruningTime();
	}
}
