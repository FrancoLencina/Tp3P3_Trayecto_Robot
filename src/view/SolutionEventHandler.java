package view;

import java.awt.Color;
import java.util.List;

import javax.swing.*;

import controllers.BruteForceController;
import model.Solution;

public class SolutionEventHandler extends SwingWorker<Boolean, Boolean>{
	
	private BruteForceController bfc;
	private JProgressBar progressBar;
	private JLabel[][] labels;
	private JComboBox<String> solutionsOutput;
	
	
	public SolutionEventHandler(BruteForceController controller, JProgressBar bar, JComboBox<String> box, JLabel[][] labels) {
		this.bfc = controller;
		this.progressBar = bar;
		this.solutionsOutput = box;
		this.labels = labels;
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
			if (!this.isCancelled())
			{;
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
}

