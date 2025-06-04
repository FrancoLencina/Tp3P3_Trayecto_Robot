package view;

import java.util.List;

import javax.swing.*;

import controllers.BruteForceController;

public class SolutionEventHandler extends SwingWorker<Object, Boolean>{
	
	private BruteForceController bfc;
	private JProgressBar progressBar;
	private JComboBox<String> solutionsOutput;
	
	
	public SolutionEventHandler(BruteForceController bfc, JProgressBar progressBar, JComboBox<String> box) {
		this.bfc = bfc;
		this.progressBar = progressBar;
		this.solutionsOutput = box;
	}
	
	
	@Override
	protected Object doInBackground() throws Exception{
		progressBar.setIndeterminate(true);
		bfc.solve();
		return bfc.getSolutions();
	}
	
	@Override
	public void done() {
		try {
			if (!this.isCancelled())
			{
				List<Object> get= (List<Object>) this.get();
				if ( get.size()!=0) {
					String[] solutions = new String[bfc.getAmountOfSolutions()];
					for (int i = 0; i<solutions.length;i++) {
						solutions[i] = "Solución " + (i+1);
						
					}
					solutionsOutput.setModel(new DefaultComboBoxModel<>(solutions));
					solutionsOutput.setEnabled(true);
				}
				progressBar.setIndeterminate(false);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

}
