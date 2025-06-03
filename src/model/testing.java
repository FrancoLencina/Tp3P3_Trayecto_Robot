package model;

public class testing {

	public static void main(String[] args) {
//		
//		int[][] matrix = {{1, -1, -1, -1}, {-1, 1, 1, -1}, {1, 1, 1, -1}};
		int[][] matrix = {{1, -1, 1, 1}, {-1, -1, -1, -1}, {1, 1, -1, -1}, {-1, 1, 1, -1}, {-1, -1, -1, 1}};
//		
//		BruteForceSolver solver = new BruteForceSolver(matrix);
//		Solver solver2 = new Solver(matrix2);
//	
//		solver.solve();
//		
//		Generator ranGen = new RandomGenerator();
//		MatrixGenerator rmg = new MatrixGenerator(ranGen);
//		int [][] matrix = rmg.generateMatrix();
//		PruningSolver solver = new PruningSolver(matrix);
//		solver.solve();
//		rmg.printMatrix(matrix);
//		
//		Generator preGen = new PresetGenerator(2,4,true);
//		MatrixGenerator pmg = new MatrixGenerator(preGen);
//		int [][] matrix2 = pmg.generateMatrix();
//		pmg.printMatrix(matrix2);
		
		PruningSolver ps = new PruningSolver(matrix);
		long start1 = System.currentTimeMillis();
		ps.solve();
		long finish1 = System.currentTimeMillis();
		Solver s = new Solver(matrix);
		long start2 = System.currentTimeMillis();
		s.solve();
		long finish2 = System.currentTimeMillis();
		System.out.println("Tiempo de ejecución con Poda: " + (finish1 - start1));
		System.out.println("Tiempo de ejecución sin Poda: " + (finish2 - start2));
	}
}