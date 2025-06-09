package view.helper;

import java.util.*;

import javax.swing.SwingWorker;

public class ChartEventHandler extends SwingWorker<Boolean, Void> {

	private List<int[][]> matrixes;
	private Map<String, Double> with = new HashMap();
	private Map<String, Double> without = new HashMap();;

	public ChartEventHandler(List<int[][]> matrixes) {
		this.matrixes = matrixes;
	}

	@Override
	protected Boolean doInBackground() throws Exception {
		List<GraphicBuilder> builders = new ArrayList<>();
		for (int[][] matrix : matrixes) {
			GraphicBuilder gb = new GraphicBuilder(matrix);
			gb.execute();
			System.out.println("ThreadIniciado");
			builders.add(gb);
		}
		for (int i = 0; i < builders.size(); i++) {
			try {
				GraphicBuilder gb = builders.get(i);
				List<Double> results = gb.get();
				int[][] matrix = matrixes.get(i);
				String size = matrix[0].length + "x" + matrix.length;
				without.put(size, results.get(0));
				with.put(size, results.get(1));
			} catch (Exception e) {
				e.printStackTrace();
			}
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
