package model;

public class RandomMatrixGenerator {

	private Generator _random;
	
	public RandomMatrixGenerator(Generator generator) {
		_random = generator;
	}
	
	public int[][] generateMatrix() {
		
		int row, col;
		row = _random.nextInt(2, 11);
		col = _random.nextInt(2, 11);
		
		while((row + col -1)% 2 != 0) {
			row = _random.nextInt(2, 11);
			col = _random.nextInt(2, 11);
		}
		
		int[][] matrix = new int[row][col];
		
		for(int r = 0; r < matrix.length; r++) {
			for(int c = 0; c < matrix[0].length; c++) {
				boolean positiveCharge = _random.nextBoolean();
				if(positiveCharge) {
					matrix[r][c] = 1;
				}
				else {
					matrix[r][c] = -1;
				}
			}
		}
		
		return matrix;
	}
	public void printMatrix(int[][] matrix) {
        for (int[] row : matrix) {
            for (int val : row) {
                System.out.print((val == 1 ? " 1 " : "-1 ") + " ");
            }
            System.out.println();
        }
    }
}
