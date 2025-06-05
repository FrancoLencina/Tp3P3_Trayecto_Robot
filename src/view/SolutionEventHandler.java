package view;

import java.awt.Color;
import javax.swing.*;
import controllers.BruteForceController;
import controllers.TimerController;

public class SolutionEventHandler extends SwingWorker<Boolean, Boolean>{
	
	private TimerController timerController;
	private JTextField txtWithPruning;
	private JTextField txtWithoutPruning;
	private BruteForceController bfc;
	private JProgressBar progressBar;
	private JLabel[][] labels;
	private JComboBox<String> solutionsOutput;
	
	
	public SolutionEventHandler(BruteForceController controller, JProgressBar bar, JComboBox<String> box, JLabel[][] labels,
			TimerController timerController, JTextField txtWithPruning, JTextField txtWithoutPruning) {
		this.bfc = controller;
		this.progressBar = bar;
		this.solutionsOutput = box;
		this.labels = labels;
		this.timerController = timerController;
		this.txtWithPruning = txtWithPruning;
		this.txtWithoutPruning = txtWithoutPruning;
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
					showFirstSolutionOnView();
				}
				progressBar.setIndeterminate(false);
				txtWithoutPruning.setVisible(true);
			    txtWithPruning.setVisible(true);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	private void showFirstSolutionOnView() {
		for (int[] c : bfc.getSolutions().get(0).get_journey()) {
		    int row = c[0];
		    int col = c[1];
		    if (row < labels.length && col < labels[0].length) {
		        labels[row][col].setOpaque(true);
		        labels[row][col].setBackground(Color.GREEN);
		    } else {
		        System.out.println("Índice fuera de rango: (" + row + "," + col + ")");
		    }
		}
	}
	
	private void timerOnScreen() {
		double timeWithout= timerController.getBruteForceTime(bfc.getMatrix());
		double timeWith= timerController.getPruningTime(bfc.getMatrix());
		
		txtWithoutPruning.setText(" "+timeWithout + " segs.");
		txtWithPruning.setText(" "+timeWith + " segs.");
		System.out.println("Tiempo Total sin poda: " + timeWithout + " segs.");
		System.out.println("Tiempo Total con poda: " + timeWith + " segs.");
	}
}

