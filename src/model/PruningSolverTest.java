package model;

import static org.junit.Assert.*;

import org.junit.Test;

public class PruningSolverTest {
	
	@Test
	public void testSolveMatrixWithSingleSolution() {
		int[][] a = {{1,-1,1},{-1,-1,-1}};
		PruningSolver s = new PruningSolver(a);
		
		s.solve();
		
		assertEquals(1,s.solutionsSize());
	}
	
	@Test
	public void testSolveMatrixWithMultipleSolutions() {
		int[][] a = {{1,-1,1},{-1,1,-1}};
		PruningSolver s = new PruningSolver(a);
		
		s.solve();
		
		assertNotEquals(1,s.solutionsSize());
		assertNotEquals(0,s.solutionsSize());
	}
	
	@Test
	public void testSolveMatrixWithNoSolution() {
		int[][] a = {{-1,-1},{-1,1},{-1,-1}};
		PruningSolver s = new PruningSolver(a);
		
		s.solve();
		
		assertEquals(0,s.solutionsSize());
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testSolveEmptyMatrix() {
		int[][] matrix= {};
		new PruningSolver(matrix);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testSolveSquareMatrix() {
		int[][] matrix= {{1,-1},{-1,1}};
		new Solver(matrix);
	}
	
	
}