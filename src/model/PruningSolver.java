package model;

public class PruningSolver extends Solver{

	private int _remainingSteps;
	
	public PruningSolver(int[][] matrix) {
		super(matrix);
		set_matrix(matrix);
	}
	

	public void generateFrom(int row, int col, Solution sol){
		if(sol.getCharge() == 0 && _remainingSteps == 0) {
			get_solutions().add(sol.clone());
			return;
		}
		
		
		if (Math.abs(sol.getCharge()) <= _remainingSteps ) {
			
			if (row+1 < get_m() ) {
				
				sol.addStep(col, row + 1, get_matrix()[row + 1][col]);
				_remainingSteps-=1;
				generateFrom(row + 1, col, sol);
				sol.removeLastStep(get_matrix()[row + 1][col]);
				_remainingSteps+=1;
			}
			
			if (col+1 < get_n()) {
				
				sol.addStep(col + 1, row, get_matrix()[row][col + 1]);
				_remainingSteps-=1;
				generateFrom(row, col + 1, sol);
				sol.removeLastStep(get_matrix()[row][col + 1]);
				_remainingSteps+=1;
			}
		}
	}
}
