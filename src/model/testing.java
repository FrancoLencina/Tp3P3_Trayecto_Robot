package model;

public class testing {

	public static void main(String[] args) {
//		
		int[][] matrix = {{1, -1, -1, -1}, {-1, 1, 1, -1}, {1, 1, 1, -1}};
		int[][] matrix2 = {{1, -1, 1, 1}, {-1, -1, -1, -1}, {1, 1, -1, -1}, {-1, 1, 1, -1}, {-1, -1, -1, 1}};
		
		BruteForceSolver solver = new BruteForceSolver(matrix);
		Solver solver2 = new Solver(matrix2);
	
		solver.solve();
//		Generator ranGen = new RandomGenerator();
//		RandomMatrixGenerator rmg = new RandomMatrixGenerator(ranGen);
//		int [][] matrix = rmg.generateMatrix();
//		rmg.printMatrix(matrix);
	}
}
