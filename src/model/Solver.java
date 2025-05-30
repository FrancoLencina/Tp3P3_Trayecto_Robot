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
		System.out.println("Soluciones encontradas:" + solutionsSize());
	}
	
	public void generateFrom(int row, int col, Solution sol){
		
		System.out.print("Pasos restantes: " + _remainingSteps);
		System.out.print(" Carga actual " + sol.getCharge());
		
		if(sol.getCharge() == 0 && _remainingSteps == 0) {
			_solutions.add(sol.clone());
			System.out.println("\nSolución encontrada!");
			for (int[] c : _solutions.get(solutionsSize()-1).get_journey()) {
				System.out.print("{" + c[0] +", "+ c[1] + "} ");
			}
			return;
		}
		
		
		if (Math.abs(sol.getCharge()) <= _remainingSteps ) {
			
			if (row+1 < _m ) {
				
				sol.addStep(col, row + 1, _matrix[row + 1][col]);
				_remainingSteps-=1;
				System.out.print(" v\n");
				generateFrom(row + 1, col, sol);
				sol.removeLastStep(col, row+1, _matrix[row + 1][col]);
				_remainingSteps+=1;
				System.out.print(" ^");
			}
			
			if (col+1 < _n) {
				
				sol.addStep(col + 1, row, _matrix[row][col + 1]);
				_remainingSteps-=1;
				System.out.print(" ->\n");
				generateFrom(row, col + 1, sol);
				sol.removeLastStep(col + 1, row, _matrix[row][col + 1]);
				_remainingSteps+=1;
				System.out.print(" <-");
			}
		}
	}
	
	public int solutionsSize(){
		return _solutions.size();
	}
}
