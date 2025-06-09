package view.helper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.SwingWorker;

public class ChartEventHandler extends SwingWorker<Boolean, Object>{

	private List<int[][]> matrixes;
	private Map<String, Double> with = new HashMap();
	private Map<String, Double> without = new HashMap();;
	
	public ChartEventHandler(List<int[][]> matrixes) {
		this.matrixes = matrixes;
	}
	
	@Override
	protected Boolean doInBackground() throws Exception {
		for(int[][] matrix : matrixes) {
			try {
				GraphicBuilder gb = new GraphicBuilder(matrix);
				gb.execute();
				String size = matrix[0].length + "x" + matrix.length;
				List<Double> results = (List<Double>) gb.get();
				without.put(size, results.get(0));
				with.put(size, results.get(1));
				} catch (Exception ex) {
					ex.printStackTrace();
				}
		}
		return true;
	}

	public Map<String, Double> getTimesWithPruning(){ 
		return this.with;
	}
	
	public Map<String, Double> getTimesWithoutPruning(){ 
		return this.without;
	}
	
}
