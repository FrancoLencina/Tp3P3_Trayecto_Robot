package model;

import java.util.ArrayList;
import java.util.List;

public class Solver {
	private int _n; //Ancho
	private int _m; //Alto
	private Solution _current;
	private List<Solution> _solutions;
	private int[][] _matrix;
	
	public Solver(int [][] matrix) {
		_matrix = matrix;
		_n = matrix[0].length;
		_m = matrix.length;
	}
	
	public void solve() {
		_solutions= new ArrayList<Solution>();
		_current= new Solution();
		_current.addStep(0, 0, get_matrix()[0][0]);
		generateFrom(0,0, _current);
		System.out.println("Soluciones encontradas:" + solutionsSize());
	}

	private void generateFrom(int row, int col, Solution sol) {
		System.out.println("Posicion: ("+row+","+col+")");
		System.out.println("Carga actual:" + sol.getCharge());
		if (row == get_m() -1 && col == get_n() -1) {
			if(sol.getCharge()==0) {
				_solutions.add(sol.clone());
				System.out.println("\nSolución encontrada!");
				for (int[] c : _solutions.get(solutionsSize()-1).get_journey()) {
					System.out.print("{" + c[0] +", "+ c[1] + "} ");
				}
			}
			return;
		}
		
		//Hacia abajo si se puede
		if(row +1< get_m()) {
			sol.addStep(row+1, col, get_matrix()[row+1][col]);
			System.out.println("v\n");
			generateFrom(row+1,col,sol);
			sol.removeLastStep(get_matrix()[row+1][col]);
			System.out.println("^");	
		}
		
		//Hacia derecha si se puede
		if(col +1 < get_n()) {
			sol.addStep(row, col+ 1 , get_matrix()[row][col+1]);
			System.out.println("->\n");
			generateFrom(row,col+1,sol);
			sol.removeLastStep(get_matrix()[row][col+1]);
			System.out.println("<-");
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



}
