package model;

public class testing {

	public static void main(String[] args) {
//		
//		int[][] matrix = {{1, -1, -1, -1}, {-1, 1, 1, -1}, {1, 1, 1, -1}};
//		int[][] matrix2 = {{1, -1, 1, 1}, {-1, -1, -1, -1}, {1, 1, -1, -1}, {-1, 1, 1, -1}, {-1, -1, -1, 1}};
//		
//		Solver solver = new Solver(matrix);
//		Solver solver2 = new Solver(matrix2);
//	
//		solver2.solve();
		
		RandomMatrixGenerator rmg = new RandomMatrixGenerator();
		int [][] matrix = rmg.generateMatrix();
		rmg.printMatrix(matrix);
	}
}
