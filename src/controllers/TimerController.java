package controllers;

import model.Timer;

public class TimerController extends Thread {

	private int[][] matrix;
	private Timer timer;

	public TimerController(int[][] matrix) {
		this.matrix = matrix;
		timer = new Timer();
	}

	@Override
	public void run() {
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
