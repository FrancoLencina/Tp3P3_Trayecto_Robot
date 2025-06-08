package model;

public class Timer {

	private double bruteForceTime;
	private double pruningTime;

	public void doBruteForceTime(int[][] matrix) {
		long start = System.currentTimeMillis();
		for (int i = 0; i < 500; i++) {
			Solver s = new Solver(matrix);
			s.setBacktrackingEnabled(false);
			s.solve();
		}
		long finish = System.currentTimeMillis();
		bruteForceTime = (finish - start) / 1000.0;
	}

	public void doPruningTime(int[][] matrix) {
		long start = System.currentTimeMillis();
		for (int i = 0; i < 500; i++) {
			Solver s = new Solver(matrix);
			s.setBacktrackingEnabled(true);
			s.solve();
		}
		long finish = System.currentTimeMillis();
		pruningTime = (finish - start) / 1000.0;
	}

	public double getBruteForceTime() {
		return bruteForceTime;
	}

	public double getPruningTime() {
		return pruningTime;
	}

}
