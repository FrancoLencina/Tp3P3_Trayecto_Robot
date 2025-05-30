package model;

import java.util.ArrayList;
import java.util.List;

public class Solver {

	private int _n; //ancho
	private int _m; //alto
	private Solution _actual;
	private List<Solution> _solutions;
	private int[][] _matrix;
	private int _remainingSteps;
	
	public Solver(int [][] matrix) {
		_matrix = matrix;
		_n = matrix[0].length;
		_m = matrix.length;
		_remainingSteps = _n + _m - 2;
	}
	
	public void solve(){
		_solutions = new ArrayList<Solution>();
		_actual = new Solution();
		_actual.addStep(0, 0, _matrix[0][0]);
		generateFrom(0, 0, _actual);
		System.out.print("Soluciones encontradas:" + solutionsSize());
	}
	
	public void generateFrom(int row, int col, Solution sol){
		
		System.out.print("Pasos restantes: " + _remainingSteps);
		System.out.print(" Carga actual " + sol.getCharge());
		
		if(sol.getCharge() == 0 && _remainingSteps == 0) {
			_solutions.add(sol.clone());
			System.out.println("\nSolución encontrada!");
			return;
		}
		
		
		if (Math.abs(sol.getCharge()) <= _remainingSteps ) {
			
			if (row+1 < _m ) {
				
				sol.addStep(row + 1, col, _matrix[row + 1][col]);
				_remainingSteps-=1;
				System.out.print(" v\n");
				generateFrom(row + 1, col, sol);
				sol.removeLastStep(row+1, col, _matrix[row + 1][col]);
				_remainingSteps+=1;
				System.out.print(" ^");
			}
			
			if (col+1 < _n) {
				
				sol.addStep(row, col + 1, _matrix[row][col + 1]);
				_remainingSteps-=1;
				System.out.print(" ->\n");
				generateFrom(row, col + 1, sol);
				sol.removeLastStep(row, col + 1, _matrix[row][col + 1]);
				_remainingSteps+=1;
				System.out.print(" <-");
			}
		}
		
//        if (row + 1 < _n) {
//            sol.addStep(row + 1, col, _matrix[row + 1][col]);
//            generateFrom(row + 1, col, sol);
//            sol.removeLastStep(row + 1, col, _matrix[row + 1][col]);
//        }
//
//        if (col + 1 < _m) {
//            sol.addStep(row, col + 1, _matrix[row][col + 1]);
//            generateFrom(row, col + 1, sol);
//            sol.removeLastStep(row, col + 1, _matrix[row][col + 1]);
//        }
	}
	
	public int solutionsSize(){
		return _solutions.size();
	}
}
