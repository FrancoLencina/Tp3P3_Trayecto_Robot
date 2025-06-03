package model;

import static org.junit.Assert.*;

import org.junit.Test;

public class BruteForceSolverTest {

	@Test
	public void testSolveWithExampleMatrix() {
		int[][] matrix = {
	            { 1, -1, -1, -1 },
	            { -1, 1, 1, -1 },
	            { 1, 1, 1, -1 }
	        };
		
		BruteForceSolver solver = new BruteForceSolver(matrix);
        solver.solve();
        
        assertEquals(3,solver.solutionsSize());
        
        for(Solution sol: solver.get_solutions()) {
        	assertEquals(0, sol.getCharge());
        }
	}

}
