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
    private boolean _backtracking;

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
        if(sol.getCharge() == 0 && _remainingSteps == 0) {
            _solutions.add(sol.clone());
            return;
        }


        if (Math.abs(sol.getCharge()) <= _remainingSteps || !_backtracking) {

            if (row+1 < _m ) {

                sol.addStep(col, row + 1, _matrix[row + 1][col]);
                _remainingSteps-=1;
                generateFrom(row + 1, col, sol);
                sol.removeLastStep(_matrix[row + 1][col]);
                _remainingSteps+=1;
            }

            if (col+1 < _n) {

                sol.addStep(col + 1, row, _matrix[row][col + 1]);
                _remainingSteps-=1;
                generateFrom(row, col + 1, sol);
                sol.removeLastStep(_matrix[row][col + 1]);
                _remainingSteps+=1;
            }
        }
    }

    public int solutionsSize(){
        return _solutions.size();
    }

    public List<Solution> get_solutions() {
        return _solutions;
    }
    
	public int[][] get_matrix() {
		return _matrix;
	}


	public int get_n() {
		return _n;
	}


	public int get_m() {
		return _m;
	}

	public void set_matrix(int[][] _matrix) {
		this._matrix = _matrix;
	}
	
	public void setBacktrackingEnabled(boolean state) {
		_backtracking = state;
	}



}
