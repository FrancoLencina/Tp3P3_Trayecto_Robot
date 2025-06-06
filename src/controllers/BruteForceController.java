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
		s.setBacktrackingEnabled(true);
		s.solve();
	}
	
	public List<Solution> getSolutions() {
		return s.get_solutions();
	}
	
	public int getAmountOfSolutions() {
		return s.solutionsSize();
	}
	
	public int[][] getMatrix(){
		return s.get_matrix();
	}
	
	public int get_cant() {
		return s.get_cant();
	}
}
