package model;

public class RandomMatrixGenerator {

	private Generator _random;
	private int _origin = 2;
	private int _bound = 11;
	
	public RandomMatrixGenerator(Generator generator) {
		_random = generator;
	}
	
	public int[][] generateMatrix() {
		
		int row, col;
		row = generateSize(_origin, _bound);
		col = generateSize(_origin, _bound);
		
		while (!verifyCompatible(_origin, _bound)) {
			row = generateSize(_origin, _bound);
			col = generateSize(_origin, _bound);
		}
		
		int[][] matrix = generateCharge(row, col);
		
		return matrix;
	}

	private int generateSize(int origin, int bound) {
		return _random.nextInt(origin, bound);
	}

	private boolean verifyCompatible(int row, int col) {
		
		if((row + col) % 2 == 1) {
			return true;
		}
		return false;
	}

	private int[][] generateCharge(int row, int col) {
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
