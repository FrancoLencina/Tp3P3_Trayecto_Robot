package controllers;

import java.util.List;
import model.*;

public class BruteForceController {

	private Solver pruningSolver;
	private Solver bruteSolver;

	public BruteForceController(int[][] matrix) {
		pruningSolver = new Solver(matrix);
		pruningSolver.setBacktrackingEnabled(true);
		bruteSolver = new Solver(matrix);
		bruteSolver.setBacktrackingEnabled(false);
	}

	public void solve() {
		pruningSolver.solve();
		bruteSolver.solve();
	}

	public List<Solution> getSolutions() {
		return pruningSolver.get_solutions();
	}

	public int getAmountOfSolutions() {
		return pruningSolver.solutionsSize();
	}

	public int[][] getMatrix() {
		return pruningSolver.get_matrix();
	}

	public int getPrunningCant() {
		return pruningSolver.get_cant();
	}

	public int getBruteCant() {
		return bruteSolver.get_cant();
	}
}
