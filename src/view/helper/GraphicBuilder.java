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

public class GraphicBuilder extends SwingWorker<Object, Object>{
	
	private TimerController timerC;
	private int[][] matrix;
	
	public GraphicBuilder(int[][] matrix) {
		this.matrix=matrix;
	}
	
	@Override
	public Object doInBackground() {
		timerC = new TimerController(matrix);
		timerC.run();
		try {
			timerC.join(); // Espera a que termine el hilo
		} catch (InterruptedException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Error al esperar la ejecución del hilo.", "Error",
					JOptionPane.ERROR_MESSAGE);
		}
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
