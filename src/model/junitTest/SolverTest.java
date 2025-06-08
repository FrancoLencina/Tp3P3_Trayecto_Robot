package model.junitTest;

import static org.junit.Assert.*;

import org.junit.Test;

import model.Solver;

public class SolverTest {

	@Test
	public void testSolveMatrixWithSingleSolution() {
		int[][] matrix = { { 1, -1, -1 }, { 1, 1, -1 } };

		Solver solver = new Solver(matrix);
		solver.solve();
		assertEquals(1, solver.solutionsSize());
	}

	@Test
	public void testSolveMatrixWithMultipleSolutions() {
		int[][] matrix = { { 1, -1, -1, -1 }, { -1, 1, 1, -1 }, { 1, 1, 1, -1 } };

		Solver solver = new Solver(matrix);
		solver.solve();
		assertEquals(3, solver.solutionsSize());

	}

	@Test
	public void testSolveMatrixWithNoSolution() {
		int[][] matrix = { { 1, 1, 1, 1 }, { 1, 1, 1, 1 }, { 1, -1, 1, -1 } };

		Solver s = new Solver(matrix);
		s.solve();
		assertEquals(s.solutionsSize(), 0);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testSolveEmptyMatrix() {
		int[][] matrix = {};
		new Solver(matrix);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testSolveSquareMatrix() {
		int[][] matrix = { { 1, -1 }, { -1, 1 } };
		new Solver(matrix);
	}

}
