package controllers;

import java.util.List;
import model.Solution;
import model.PruningSolver;

public class BruteForceController {

	private PruningSolver s;
	
	public BruteForceController(int[][] matrix) {
		s = new PruningSolver(matrix);
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
