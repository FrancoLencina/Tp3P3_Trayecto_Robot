package model.junitTest;

import static org.junit.Assert.*;

import org.junit.Test;

import model.Solution;

public class SolutionTest {

	@Test
	public void testAddSingleStep() {
		Solution s = new Solution();
		s.addStep(0, 0, 3);

		assertEquals(s.get_journey().size(), 1);
		assertEquals(s.getCharge(), 3);
	}

	@Test
	public void testAddMultipleSteps() {
		Solution s = new Solution();
		s.addStep(0, 0, 2);
		s.addStep(0, 2, 1);

		assertEquals(s.get_journey().size(), 2);
		assertEquals(s.getCharge(), 3);
	}

	@Test
	public void testRemoveLastStep() {
		Solution s = new Solution();
		s.addStep(1, 2, 5);
		s.addStep(3, 4, -2);

		s.removeLastStep(-2);
		assertEquals(s.get_journey().size(), 1);
		assertEquals(s.getCharge(), 5);
		s.removeLastStep(5);
		assertTrue(s.get_journey().isEmpty());
		assertEquals(0, s.getCharge());
	}

	@Test
	public void testRemoveStepOnEmptyJourney() {
		Solution s = new Solution();
		s.removeLastStep(3);
		assertTrue(s.get_journey().isEmpty());
		assertEquals(0, s.getCharge());
	}

	@Test
	public void testClone() {
		Solution s = new Solution();
		s.addStep(0, 0, 3);
		s.addStep(1, 1, -1);

		Solution clone = s.clone();

		assertEquals(s.getCharge(), clone.getCharge());
		assertEquals(s.get_journey().size(), clone.get_journey().size());

		for (int i = 0; i < s.get_journey().size(); i++) {
			assertArrayEquals(s.get_journey().get(i), clone.get_journey().get(i));
		}
	}

	@Test
	public void testCloneIsDifferent() {
		Solution s = new Solution();
		s.addStep(0, 0, 3);
		s.addStep(1, 1, -1);

		Solution clone = s.clone();
		clone.addStep(2, 2, 4);

		assertEquals(2, s.get_journey().size());
		assertEquals(3, clone.get_journey().size());
	}

}