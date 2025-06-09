package view.helper;

import javax.swing.*;
import controllers.*;

public class SolutionEventHandler extends SwingWorker<Boolean, Object> {

	private TimerController timerController;
	private BruteForceController bfc;
	

	public SolutionEventHandler(BruteForceController controller,TimerController timerController) {
		this.bfc = controller;
		this.timerController = timerController;
	}

	@Override
	protected Boolean doInBackground() {
		timerController.runTimers();
		bfc.solve();
		System.out.println(bfc.getAmountOfSolutions());
		return true;
	}

}
