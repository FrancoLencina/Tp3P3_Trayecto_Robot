package model;

import java.util.List;

public class Solver {

	private int n; //ancho
	private int m; //alto
	private Solution actual;
	private List<Solution> solutions;
	private Cell[][] cellMatrix;
	
	public Solver(int n, int m) {
		this.n = n;
		this.m = m;
	}
	
	public void resolver(Cell cell){
		
	}
	
	public void generateFrom(Cell cell){
		
	}
	
	public int solutionsSize(){
		return solutions.size();
	}
}
