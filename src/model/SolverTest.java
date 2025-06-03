package model;

import static org.junit.Assert.*;

import org.junit.Test;

public class SolverTest {
	
	@Test
	public void testSolveMatrixWithSingleSolution() {
		int[][] a = {{1,-1,1},{-1,-1,-1}};
		PruningSolver s = new PruningSolver(a);
		
		s.solve();
		
		assertEquals(s.solutionsSize(), 1);
	}
	
	@Test
	public void testSolveMatrixWithMultipleSolutions() {
		int[][] a = {{1,-1,1},{-1,1,-1}};
		PruningSolver s = new PruningSolver(a);
		
		s.solve();
		
		assertNotEquals(s.solutionsSize(), 1);
		assertNotEquals(s.solutionsSize(), 0);
	}
	
	@Test
	public void testSolveMatrixWithNoSolution() {
		int[][] a = {{1,-1},{-1,1}};
		PruningSolver s = new PruningSolver(a);
		
		s.solve();
		
		assertEquals(s.solutionsSize(), 0);
	}
}