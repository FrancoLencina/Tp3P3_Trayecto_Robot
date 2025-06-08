package view.helper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JOptionPane;

import controllers.TimerController;

public class GraphicBuilder {
	
	private TimerController timerC;
	
	public GraphicBuilder(int[][] matrix) {
		startProcess(matrix);
	}

	public Map<String, Double> getResultsWithPruning(int[][] matrix) {
		Map<String, Double> resultsWith = new HashMap<>();
		double timeWith = timerC.getPruningTime();
		String size = matrix[0].length + "x" + matrix.length;
		resultsWith.put(size, timeWith);
		System.out.println("Tamano" + size);
		System.out.println("segudno con " + timeWith);
		
		return resultsWith ;
	}

	private void startProcess(int[][] matrix) {
		timerC = new TimerController(matrix);
		timerC.start();
		try {
			timerC.join(); // Espera a que termine el hilo
		} catch (InterruptedException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Error al esperar la ejecución del hilo.", "Error",
					JOptionPane.ERROR_MESSAGE);
		}
	}
	
	public Map<String, Double> getResultsWithoutPruning(int[][] matrix) {
		Map<String, Double> resultsWithout = new HashMap<>();
		double timeWithout = timerC.getBruteForceTime();
		String size = matrix[0].length + "x" + matrix.length;
		resultsWithout.put(size, timeWithout);
		System.out.println("Tamano" + size);
		System.out.println("segudno con " + timeWithout);
		
		return resultsWithout ;
	}

}
