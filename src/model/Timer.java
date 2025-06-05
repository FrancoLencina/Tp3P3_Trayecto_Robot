package model;

public class Timer {

	public double getBruteForceTime(int [][]matrix) {
		long start = System.currentTimeMillis();
		for (int i = 0; i<500;i++) {
			Solver s = new Solver(matrix);
			s.setBacktrackingEnabled(false);
			s.solve();
		}
		long finish = System.currentTimeMillis();
		return (finish - start) / 1000.0;
	}
	
	public double getPruningTime(int [][]matrix) {
		long start = System.currentTimeMillis();
		for (int i = 0;i<500;i++) {
			Solver s = new Solver(matrix);
			s.setBacktrackingEnabled(true);
			s.solve();
		}
		long finish = System.currentTimeMillis();
		return (finish - start) / 1000.0;
	}
	
}
