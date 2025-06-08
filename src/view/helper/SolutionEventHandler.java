package view.helper;

import java.util.*;
import javax.swing.*;
import controllers.*;

public class SolutionEventHandler extends SwingWorker<Boolean, Boolean> {

	private boolean running = false;
	private TimerController timerController;
	private BruteForceController bfc;
	private SolutionVisualizer visualizer;
	private JComboBox<String> solutionsOutput;

	public SolutionEventHandler(BruteForceController controller, SolutionVisualizer visuals, JComboBox<String> box) {
		this.bfc = controller;
		this.solutionsOutput = box;
		this.visualizer = visuals;
		this.timerController = new TimerController(bfc.getMatrix());
	}

	@Override
	protected Boolean doInBackground() {
		running = true;
		visualizer.setProgressBarIndeterminate();
		timerController.run();
		bfc.solve();
		System.out.println(bfc.getAmountOfSolutions());
		return true;
	}

	@Override
	public void done() {
		try {
			running = false;
			if (!this.isCancelled()) {
				if (bfc.getAmountOfSolutions() != 0) {
					String[] solutions = new String[bfc.getAmountOfSolutions()];
					for (int i = 0; i < solutions.length; i++) {
						solutions[i] = "Solución " + (i + 1);

					}
					solutionsOutput.setModel(new DefaultComboBoxModel<>(solutions));
					solutionsOutput.setEnabled(true);
					visualizer.showSolutionPath(bfc.getSolutions().get(0)); // Mostramos la primera solución.
				}
				setupDataTable();
				visualizer.stopProgressBar();
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
		String timeWithoutBacktrack = Double.toString(timerController.getBruteForceTime());
		data.put(2, timeWithoutBacktrack);
		String timeWithBacktrack = Double.toString(timerController.getPruningTime());
		data.put(3, timeWithBacktrack);
		String generatedBruteaths = Integer.toString(bfc.getBruteCant());
		data.put(4, generatedBruteaths);
		String generatedPrunningPaths = Integer.toString(bfc.getPrunningCant());
		data.put(5, generatedPrunningPaths);
		visualizer.displayDataTable(data);
	}

	public boolean isRunning() {
		return running;
	}
}
