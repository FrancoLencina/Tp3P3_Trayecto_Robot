package view.helper;

import java.util.*;
import javax.swing.SwingWorker;
import controllers.TimerController;

public class ChartEventHandler extends SwingWorker<Boolean, Void> {

	private List<int[][]> matrixes;
	private Map<String, Double> with = new HashMap<>();
	private Map<String, Double> without = new HashMap<>();;

	public ChartEventHandler(List<int[][]> matrixes) {
		this.matrixes = matrixes;
	}

	@Override
	protected Boolean doInBackground() throws Exception {

		    List<Thread> threads = new ArrayList<>();
		    List<TimerController> timers = new ArrayList<>();

		    for (int[][] matrix : matrixes) {
		        TimerController tc = new TimerController(matrix);
		        timers.add(tc);

		        Thread t = new Thread(new Runnable() {
		        	
					@Override
					public void run() {
						tc.runTimers();
						
					}});
		        
		        t.start();                
		        threads.add(t);

		    }

		    for (Thread t : threads) {
		        try {
		            t.join();                    
		        } catch (InterruptedException e) {
		            Thread.currentThread().interrupt();
		            return false;                
		        }
		    }

		    for (int i = 0; i < timers.size(); i++) {
		        TimerController tc = timers.get(i);
		        int[][] matrix = matrixes.get(i);
		        String size = matrix[0].length + "x" + matrix.length;

		        without.put(size, tc.getBruteForceTime());
		        with.put(size, tc.getPruningTime());
		    }
		    return true;
		}

	public Map<String, Double> getTimesWithPruning() {
		return this.with;
	}

	public Map<String, Double> getTimesWithoutPruning() {
		return this.without;
	}

}
