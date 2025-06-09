package view.helper;

import controllers.TimerController;

public class MyRunnable implements Runnable {
	private TimerController tc;

	public MyRunnable(TimerController tc) {
		this.tc = tc;
	}

	@Override
	public void run() {
		tc.runTimers();
	}

}
