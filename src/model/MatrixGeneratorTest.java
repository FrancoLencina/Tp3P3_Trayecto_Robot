package model;

import static org.junit.Assert.*;

import org.junit.Test;

public class MatrixGeneratorTest {

	@Test
	public void testValidNSize() {

		PresetGenerator generator = new PresetGenerator(4, 3, true);
		MatrixGenerator mg = new MatrixGenerator(generator);
		int expected = mg.generateSize();
		
		assertEquals(expected, 4);
	}
	
	@Test
	public void testValidMSize() {

		PresetGenerator generator = new PresetGenerator(4, 3, false);
		MatrixGenerator mg = new MatrixGenerator(generator);
		int expected = mg.generateSize();
		
		assertEquals(expected, 3);
	}
	
	@Test
	public void testNMCompatible() {

		PresetGenerator generator = new PresetGenerator(4, 3, true);
		MatrixGenerator mg = new MatrixGenerator(generator);
		
		assertTrue(mg.verifyCompatible(4, 3));
	}
	
	@Test
	public void testNMnotCompatible() {

		PresetGenerator generator = new PresetGenerator(4, 2, true);
		MatrixGenerator mg = new MatrixGenerator(generator);
		
		assertFalse(mg.verifyCompatible(4, 2));
	}
	
	@Test
	public void testGeneratePositiveCharge() {

		PresetGenerator generator = new PresetGenerator(true);
		MatrixGenerator mg = new MatrixGenerator(generator);
		int charge = mg.defineCharge();
		assertEquals(charge,1);
	}
	
	@Test
	public void testGenerateNegativeCharge() {

		PresetGenerator generator = new PresetGenerator(false);
		MatrixGenerator mg = new MatrixGenerator(generator);
		int charge = mg.defineCharge();
		assertEquals(charge,-1);
	}
	
	@Test
	public void testGenerateMatrix() {

		RandomGenerator generator = new RandomGenerator();
		MatrixGenerator mg = new MatrixGenerator(generator);
		int[][] matrix = mg.generateMatrix();
		assertNotEquals(matrix,null);
	}
}

