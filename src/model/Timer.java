package model;

public class Timer {

	public double getBruteForceTime(int [][]matrix) {
		long start = System.currentTimeMillis();
			Solver s = new Solver(matrix);
			s.solve();
		long finish = System.currentTimeMillis();
		return (finish - start) / 1000.0;
	}
	
	public double getPruningTime(int [][]matrix) {
		long start = System.currentTimeMillis();
			PruningSolver s = new PruningSolver(matrix);
			s.solve();
		long finish = System.currentTimeMillis();
		return (finish - start) / 1000.0;
	}
	
}
