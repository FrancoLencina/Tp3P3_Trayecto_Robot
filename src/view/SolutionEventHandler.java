package view;

import javax.swing.*;
import controllers.BruteForceController;
import controllers.TimerController;

public class SolutionEventHandler extends SwingWorker<Boolean, Boolean>{
	
	private TimerController timerController;
	private BruteForceController bfc;
	private Visualizer visualizer;
	private JComboBox<String> solutionsOutput;
	
	
	public SolutionEventHandler(BruteForceController controller, Visualizer visuals,JComboBox<String> box,
			TimerController timerController) {
		this.bfc = controller;
		this.solutionsOutput = box;
		this.visualizer = visuals;
		this.timerController = timerController;
	}
	
	
	@Override
	protected Boolean doInBackground() {
		bfc.solve();
		System.out.println(bfc.getAmountOfSolutions());
		return true;
	}
	
	


	@Override
	public void done() {
		try {
			if (!this.isCancelled()) {
//				timerOnScreen();
				if (bfc.getAmountOfSolutions()!=0) {
					String[] solutions = new String[bfc.getAmountOfSolutions()];
					for (int i = 0; i<solutions.length;i++) {
						solutions[i] = "Solución " + (i+1);
						
					}
					solutionsOutput.setModel(new DefaultComboBoxModel<>(solutions));
					solutionsOutput.setEnabled(true);
					visualizer.showSolutionPath(bfc.getSolutions().get(0)); //Mostramos la primera solución.
				}
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
		visualizer.showTime(timeWithout, timeWith);
	}
}

