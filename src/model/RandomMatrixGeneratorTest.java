package model;

import static org.junit.Assert.*;

import org.junit.Test;

public class RandomMatrixGeneratorTest {

	@Test
	public void testValidSize() {

		PresetGenerator generator = new PresetGenerator(4, 3, true);
		RandomMatrixGenerator rmg = new RandomMatrixGenerator(generator);
		int[][] matrix = rmg.generateMatrix();
		
		assertEquals(matrix.length, 4);
	}
	
	//@Test
	public void testValidValues() {
		boolean allResultsAreValid = true;
		
		for (int i = 0; i < 100; i++) {
			
			RandomGenerator generator = new RandomGenerator();
			RandomMatrixGenerator rmg = new RandomMatrixGenerator(generator);
			boolean valid = false;
			int[][] matrix = rmg.generateMatrix();
			
			for (int row = 0; row<matrix.length; row++) {
				for (int col = 0; col<matrix[0].length; col++) {
					
					if (matrix[row][col] == 1 || matrix[row][col] == -1) {
						valid = true;
					}
				}
			}
			allResultsAreValid = allResultsAreValid && valid;
		
		}
		
		assertTrue(allResultsAreValid);
	}
	
	//@Test
	public void testCompatibledimensions() {
		boolean allResultsAreValid = true;
		
		for (int i = 0; i < 100; i++) {
			
			RandomGenerator generator = new RandomGenerator();
			RandomMatrixGenerator rmg = new RandomMatrixGenerator(generator);
			boolean valid = false;
			int[][] matrix = rmg.generateMatrix();
			int row = matrix.length;
			int col = matrix[0].length;
			
			if ((row + col) % 2 == 1) {
				valid = true;
			}
			
			allResultsAreValid = allResultsAreValid && valid;
		
		}
		assertTrue(allResultsAreValid);
	}


	
//	@Test
//	public void testValidSize() {
//		boolean allResultsAreValid = true;
//		
//		for (int i = 0; i < 100; i++) { //probamos si funciona para 100 matrices aleatorias diferentes
//			
//			RandomGenerator generator = new RandomGenerator();
//			RandomMatrixGenerator rmg = new RandomMatrixGenerator(generator);
//			boolean valid = false;
//			int[][] matrix = rmg.generateMatrix();
//			
//			if (matrix.length > 1 && matrix.length < 11 && 
//				matrix[0].length > 1 && matrix[0].length < 11) {
//				
//				valid = true;
//			}
//			
//			allResultsAreValid = allResultsAreValid && valid;
//		}
//		
//		assertTrue(allResultsAreValid);
//	}
//	
//	@Test
//	public void testValidValues() {
//		boolean allResultsAreValid = true;
//		
//		for (int i = 0; i < 100; i++) {
//			
//			RandomGenerator generator = new RandomGenerator();
//			RandomMatrixGenerator rmg = new RandomMatrixGenerator(generator);
//			boolean valid = false;
//			int[][] matrix = rmg.generateMatrix();
//			
//			for (int row = 0; row<matrix.length; row++) {
//				for (int col = 0; col<matrix[0].length; col++) {
//					
//					if (matrix[row][col] == 1 || matrix[row][col] == -1) {
//						valid = true;
//					}
//				}
//			}
//			allResultsAreValid = allResultsAreValid && valid;
//		
//		}
//		
//		assertTrue(allResultsAreValid);
//	}
//	
//	@Test
//	public void testCompatibledimensions() {
//		boolean allResultsAreValid = true;
//		
//		for (int i = 0; i < 100; i++) {
//			
//			RandomGenerator generator = new RandomGenerator();
//			RandomMatrixGenerator rmg = new RandomMatrixGenerator(generator);
//			boolean valid = false;
//			int[][] matrix = rmg.generateMatrix();
//			int row = matrix.length;
//			int col = matrix[0].length;
//			
//			if ((row + col) % 2 == 1) {
//				valid = true;
//			}
//			
//			allResultsAreValid = allResultsAreValid && valid;
//		
//		}
//		assertTrue(allResultsAreValid);
//	}
}

