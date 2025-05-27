package model;

import java.util.ArrayList;
import java.util.List;

public class Solver {

	private int _n; //ancho
	private int _m; //alto
	private Solution _actual;
	private List<Solution> _solutions;
	private int[][] _matrix;
	private int _steps;
	
	public Solver(int [][] matrix) {
		_matrix = matrix;
		_n = matrix.length;
		_m = matrix[0].length;
		_steps = 0;
	}
	
	public void solve(){
		_solutions = new ArrayList<Solution>();
		_actual = new Solution();
		_actual.addStep(0, 0, _matrix[0][0]);
		generateFrom(0, 0, _actual);
	}
	
	public void generateFrom(int row, int col, Solution sol){
		if(remainingSteps() == 0) {
			_solutions.add(sol.clone());
			return;
		}
		
		_steps++;
		
        if (row + 1 < _n) {
            sol.addStep(row + 1, col, _matrix[row + 1][col]);
            generateFrom(row + 1, col, sol);
            sol.removeLastStep(row + 1, col, _matrix[row + 1][col]);
        }

        if (col + 1 < _m) {
            sol.addStep(row, col + 1, _matrix[row][col + 1]);
            generateFrom(row, col + 1, sol);
            sol.removeLastStep(row, col + 1, _matrix[row][col + 1]);
        }
	}
	
	private int remainingSteps() {
		return _steps - (_n + (_m - 1));
	}

	public int solutionsSize(){
		return _solutions.size();
	}
}
