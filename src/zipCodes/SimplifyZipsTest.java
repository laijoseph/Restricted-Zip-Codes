package zipCodes;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.Test;

public class SimplifyZipsTest {

	@Test
	public void noOverlap() {// no overlap
		int[][] testValues = { { 90730, 92834 }, { 93207, 94683 }, { 95729, 99321 } };
		int[][] expected = { { 90730, 92834 }, { 93207, 94683 }, { 95729, 99321 } };
		assertTrue(compareResult(expected, SimplifyZips.simplyZipCodes(testValues)));
	}

	@Test
	public void allOverlap() {// all overlap
		int[][] testValues = { { 90730, 93207 }, { 92834, 95729 }, { 94683, 99321 } };
		int[][] expected = { { 90730, 99321 } };
		assertTrue(compareResult(expected, SimplifyZips.simplyZipCodes(testValues)));
	}

	@Test
	public void someOverlap() {// some overlap
		int[][] testValues = { { 90730, 93207 }, { 92834, 94683 }, { 95729, 99321 } };
		int[][] expected = { { 90730, 94683 }, { 95729, 99321 } };
		assertTrue(compareResult(expected, SimplifyZips.simplyZipCodes(testValues)));
	}

	@Test
	public void continuousRanges() {// no overlap, but continuous ranges
		int[][] testValues = { { 90730, 93207 }, { 93208, 94682 }, { 94683, 99321 } };
		int[][] expected = { { 90730, 99321 } };
		assertTrue(compareResult(expected, SimplifyZips.simplyZipCodes(testValues)));
	}

	@Test
	public void compareResultTest() {// to show that compareResult() doesn't
										// give false
		// positives
		int[][] testValues = { { 90730, 93207 }, { 93208, 94682 }, { 94683, 99321 } };
		int[][] expected = { { 90739, 99321 } };
		assertFalse(compareResult(expected, SimplifyZips.simplyZipCodes(testValues)));
	}

	@Test
	public void maxMinPairs() {// range pairs are swapped
		int[][] testValues = { { 93207, 90730 }, { 94682, 93208 }, { 99321, 94683 } };
		int[][] expected = { { 90730, 99321 } };
		assertTrue(compareResult(expected, SimplifyZips.simplyZipCodes(testValues)));
	}

	@Test
	public void oneIncludesAll() {// first set of range includes the rest of the
									// ranges
		int[][] testValues = { { 90730, 99321 }, { 92834, 95729 }, { 94683, 94682 } };
		int[][] expected = { { 90730, 99321 } };
		assertTrue(compareResult(expected, SimplifyZips.simplyZipCodes(testValues)));
	}

	@Test
	public void noLimits() {// Don't throw NPE if parameter array is empty
							// (maybe we don't have shipping limitations
							// at this time)
		int[][] testNull = { {} };
		int[][] testNull2 = {};
		int[][] testNull3 = null;
		assertNull(SimplifyZips.simplyZipCodes(testNull));
		assertNull(SimplifyZips.simplyZipCodes(testNull2));
		assertNull(SimplifyZips.simplyZipCodes(testNull3));
	}

	// Below are the examples given by the problem statement

	/*
	 * If the input = [94133,94133] [94200,94299] [94600,94699] Then the output
	 * should be = [94133,94133] [94200,94299] [94600,94699]
	 */

	@Test
	public void exampleFromProblemStatement() {
		int[][] testValues = { { 94133, 94133 }, { 94200, 94299 }, { 94600, 94699 } };
		int[][] expected = { { 94133, 94133 }, { 94200, 94299 }, { 94600, 94699 } };
		assertTrue(compareResult(expected, SimplifyZips.simplyZipCodes(testValues)));
	}

	/*
	 * If the input = [94133,94133] [94200,94299] [94226,94399] Then the output
	 * should be = [94133,94133] [94200,94399]
	 */
	@Test
	public void exampleFromProblemStatement2() {// to show that compareResult()
												// works correctly
		int[][] testValues = { { 94133, 94133 }, { 94200, 94299 }, { 94226, 94399 } };
		int[][] expected = { { 94133, 94133 }, { 94200, 94399 } };
		assertTrue(compareResult(expected, SimplifyZips.simplyZipCodes(testValues)));
	}

	private boolean compareResult(int[][] expected, ArrayList<int[]> actual) {
		for (int i = 0; i < expected.length; i++) {
			if (!Arrays.equals(expected[i], actual.get(i)))
				return false;
		}
		return true;
	}
}
