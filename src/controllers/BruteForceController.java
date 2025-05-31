package controllers;

import java.util.List;

import fileReader.JsonReader;
import model.Solution;
import model.Solver;

public class BruteForceController {

	
	private JsonReader jr = new JsonReader();
	private Solver s;
	
	public void readFile(String route) {
		jr.readFile(route);
		s = new Solver(jr.getMatrix());
	}
	
	public void solve() {
		s.solve();
	}
	
	public int[][] getMatrix() {
		return jr.getMatrix();
	}
	
	public int[] getMatrixAttributes() {
		return jr.getMatrixAttributes();
	}
	
	public List<Solution> getSolutions() {
		return s.get_solutions();
	}
	
	public int getAmountOfSolutions() {
		return s.solutionsSize();
	}
}
