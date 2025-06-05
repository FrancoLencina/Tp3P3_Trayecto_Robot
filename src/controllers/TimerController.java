package controllers;

import model.Timer;

public class TimerController {
	
	private Timer timer;
	
	public TimerController() {
        this.timer = new Timer();
    }
	
	public double getBruteForceTime(int [][]matrix) {
		return timer.getBruteForceTime(matrix);
	}
	
	public double getPruningTime(int [][]matrix) {
		return timer.getPruningTime(matrix);
	}
	
}
