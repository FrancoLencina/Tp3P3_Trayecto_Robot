package view;

import javax.swing.*;
import controllers.BruteForceController;
import controllers.TimerController;

public class SolutionEventHandler extends SwingWorker<Boolean, Boolean>{
	
	private TimerController timerController;
	private BruteForceController bfc;
	private JProgressBar progressBar;
	private Visualizer drawer;
	private JComboBox<String> solutionsOutput;
	
	
	public SolutionEventHandler(BruteForceController controller, JProgressBar bar, JComboBox<String> box, Visualizer drawer,
			TimerController timerController) {
		this.bfc = controller;
		this.progressBar = bar;
		this.solutionsOutput = box;
		this.drawer = drawer;
		this.timerController = timerController;
	}
	
	
	@Override
	protected Boolean doInBackground() {
		progressBar.setIndeterminate(true);
		bfc.solve();
		System.out.println(bfc.getAmountOfSolutions());
		return true;
	}
	
	


	@Override
	public void done() {
		try {
			if (!this.isCancelled()) {
				timerOnScreen();
				if (bfc.getAmountOfSolutions()!=0) {
					String[] solutions = new String[bfc.getAmountOfSolutions()];
					for (int i = 0; i<solutions.length;i++) {
						solutions[i] = "Solución " + (i+1);
						
					}
					solutionsOutput.setModel(new DefaultComboBoxModel<>(solutions));
					solutionsOutput.setEnabled(true);
					drawer.showSolutionPath(bfc.getSolutions().get(0)); //Mostramos la primera solución.
				}
				progressBar.setIndeterminate(false);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	
	private void timerOnScreen() {
		double timeWithout= timerController.getBruteForceTime(bfc.getMatrix());
		double timeWith= timerController.getPruningTime(bfc.getMatrix());
		System.out.println("Tiempo sin poda: " + timeWithout);
		System.out.println("Tiempo con poda: " + timeWith);
		//ACA IRIA ALGO DE TABLA
		drawer.showTime(timeWithout, timeWith);
	}
}

