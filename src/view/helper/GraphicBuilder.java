package view.helper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.swing.JOptionPane;
import javax.swing.SwingWorker;

import controllers.TimerController;

public class GraphicBuilder extends SwingWorker<List<Double>, Void>{
	
	private TimerController timerC;
	private int[][] matrix;
	
	public GraphicBuilder(int[][] matrix) {
		this.matrix=matrix;
	}
	
	@Override
	public List<Double> doInBackground() {
		timerC = new TimerController(matrix);
		timerC.runTimers();
		List<Double> results = new ArrayList<Double>();
		results.add(getResultsWithoutPruning());
		results.add(getResultsWithPruning());
		return results;
	}
	
	public double getResultsWithPruning() {
		return timerC.getPruningTime();
	}
	
	public double getResultsWithoutPruning() {
		return timerC.getBruteForceTime();
	}

}
