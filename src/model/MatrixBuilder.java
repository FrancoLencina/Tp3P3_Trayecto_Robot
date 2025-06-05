package model;

public class MatrixBuilder {

	private Generator _random;
	private int _origin = 2;
	private int _bound = 11;
	
	public MatrixBuilder(Generator generator) {
		_random = generator;
	}
	
	public int[][] generateMatrix() {
		
		int row, col;
		row = generateSize();
		col = generateSize();
		
		while (!verifyCompatible(row, col)) {
			row = generateSize();
			col = generateSize();
		}
		
		int[][] matrix = generateCharge(row, col);
		
		return matrix;
	}

	int generateSize() {
		return _random.nextInt(_origin, _bound);
	}

	boolean verifyCompatible(int row, int col) {
		
		if((row + col) % 2 == 1) {
			return true;
		}
		return false;
	}

	private int[][] generateCharge(int row, int col) {
		int[][] matrix = new int[row][col];
		
		for(int r = 0; r < matrix.length; r++) {
			for(int c = 0; c < matrix[0].length; c++) {
				matrix[r][c] = defineCharge();
			}
		}
		return matrix;
	}
	
	int defineCharge() {
		boolean positiveCharge = _random.nextBoolean();
		if(positiveCharge) {
			return 1;
		}
		
		return -1;
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
