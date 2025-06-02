package controllers;

import java.util.List;
import model.Solution;
import model.Solver;

public class BruteForceController {

	private Solver s;
	
	public BruteForceController(int[][] matrix) {
		s = new Solver(matrix);
	}

	public void solve() {
		s.solve();
	}
	
	public List<Solution> getSolutions() {
		return s.get_solutions();
	}
	
	public int getAmountOfSolutions() {
		return s.solutionsSize();
	}
}
