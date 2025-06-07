package view;

import java.util.HashMap;
import java.util.Map;
import javax.swing.*;
import controllers.BruteForceController;
import controllers.TimerController;

public class SolutionEventHandler extends SwingWorker<Boolean, Boolean>{
	
	private TimerController timerController;
	private BruteForceController bfc;
	private Visualizer visualizer;
	private JComboBox<String> solutionsOutput;
	private JProgressBar progressBar;
	
	public SolutionEventHandler(BruteForceController controller, Visualizer visuals, JProgressBar bar, JComboBox<String> box,
			TimerController timerController) {
		this.bfc = controller;
		this.progressBar=bar;
		this.solutionsOutput = box;
		this.visualizer = visuals;
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
				if (bfc.getAmountOfSolutions()!=0) {
					String[] solutions = new String[bfc.getAmountOfSolutions()];
					for (int i = 0; i<solutions.length;i++) {
						solutions[i] = "Solución " + (i+1);
						
					}
					solutionsOutput.setModel(new DefaultComboBoxModel<>(solutions));
					solutionsOutput.setEnabled(true);
					visualizer.showSolutionPath(bfc.getSolutions().get(0)); //Mostramos la primera solución.
					setupDataTable();
					progressBar.setIndeterminate(false);
				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	private void setupDataTable() {
		Map<Integer, String> data = new HashMap<Integer, String>();
		int[][] matrix = bfc.getMatrix();
		String matrixAttributes = "Matriz " + matrix[0].length + "x" + matrix.length;
		data.put(1, matrixAttributes);
		String timeWithoutBacktrack = Double.toString(timerController.getBruteForceTime(bfc.getMatrix()));
		data.put(2, timeWithoutBacktrack);
		String timeWithBacktrack = Double.toString(timerController.getPruningTime(bfc.getMatrix()));
		data.put(3, timeWithBacktrack);
		String generatedPrunningPaths = Integer.toString(bfc.getPrunningCant());
		data.put(5, generatedPrunningPaths);
		String generatedBruteaths = Integer.toString(bfc.getBruteCant());
		data.put(4, generatedBruteaths);
		System.out.println("Caminos prunning - " + generatedPrunningPaths);
		System.out.println("Caminos brute - " + generatedBruteaths);
		visualizer.displayDataTable(data);
	}
}