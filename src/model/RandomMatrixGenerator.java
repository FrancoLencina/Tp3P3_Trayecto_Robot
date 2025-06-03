package model;

import java.util.Random;

public class RandomMatrixGenerator {

	private Random random = new Random();
	
	public int[][] generateMatrix() {
		
		int row, col;
		row = random.nextInt(2, 11);
		col = random.nextInt(2, 11);
		
		while((row + col -1)% 2 != 0) {
			row = random.nextInt(2, 11);
			col = random.nextInt(2, 11);
		}
		
		int[][] matrix = new int[row][col];
		
		for(int r = 0; r < matrix.length; r++) {
			for(int c = 0; c < matrix[0].length; c++) {
				boolean positiveCharge = random.nextBoolean();
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
