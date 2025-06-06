package model.junitTest;

import static org.junit.Assert.*;

import org.junit.Test;

import model.randomMatrixGeneration.MatrixBuilder;
import model.randomMatrixGeneration.PresetGenerator;
import model.randomMatrixGeneration.RandomGenerator;

public class MatrixBuilderTest {

	@Test
	public void testValidNSize() {

		PresetGenerator generator = new PresetGenerator(4, 3, true);
		MatrixBuilder mg = new MatrixBuilder(generator);
		int expected = mg.generateSize();
		
		assertEquals(expected, 3);
	}
	
	@Test
	public void testValidMSize() {

		PresetGenerator generator = new PresetGenerator(4, 3, false);
		MatrixBuilder mg = new MatrixBuilder(generator);
		int expected = mg.generateSize();
		
		assertEquals(expected, 4);
	}
	
	@Test
	public void testNMCompatible() {

		PresetGenerator generator = new PresetGenerator(4, 3, true);
		MatrixBuilder mg = new MatrixBuilder(generator);
		
		assertTrue(mg.verifyCompatible(4, 3));
	}
	
	@Test
	public void testNMnotCompatible() {

		PresetGenerator generator = new PresetGenerator(4, 2, true);
		MatrixBuilder mg = new MatrixBuilder(generator);
		
		assertFalse(mg.verifyCompatible(4, 2));
	}
	
	@Test
	public void testGeneratePositiveCharge() {

		PresetGenerator generator = new PresetGenerator(true);
		MatrixBuilder mg = new MatrixBuilder(generator);
		int charge = mg.defineCharge();
		assertEquals(charge,1);
	}
	
	@Test
	public void testGenerateNegativeCharge() {

		PresetGenerator generator = new PresetGenerator(false);
		MatrixBuilder mg = new MatrixBuilder(generator);
		int charge = mg.defineCharge();
		assertEquals(charge,-1);
	}
	
	@Test
	public void testGenerateMatrix() {

		RandomGenerator generator = new RandomGenerator();
		MatrixBuilder mg = new MatrixBuilder(generator);
		int[][] matrix = mg.generateMatrix();
		assertNotEquals(matrix,null);
	}
	
	@Test
	public void testGeneratePresetMatrix() {

		PresetGenerator generator = new PresetGenerator(4, 3, true);
		MatrixBuilder mg = new MatrixBuilder(generator);
		int[][] matrix = mg.generateMatrix();
		assertNotEquals(matrix,null);
	}
}

