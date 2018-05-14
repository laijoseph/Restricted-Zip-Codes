package zipCodes;

import java.util.ArrayList;
import java.util.Collections;
import java.util.ListIterator;

public class SimplifyZips {
	static ArrayList<int[]> simplyZipCodes(int[][] zipRanges) {
		// handle null here to avoid NPE
		if ((zipRanges == null) || (zipRanges.length < 1) || (zipRanges[0].length < 1))
			return null;
		ArrayList<Integer> lowerBounds = new ArrayList<Integer>();
		ArrayList<Integer> upperBounds = new ArrayList<Integer>();

		// problem doesn't specify that we'll always be given ranges in
		// [lower,upper] format, could be [upper,lower]
		for (int[] i : zipRanges) {
			if (i[0] < i[1]) {
				lowerBounds.add(i[0]);
				upperBounds.add(i[1]);
			} else {
				lowerBounds.add(i[1]);
				upperBounds.add(i[0]);
			}
		}

		// now we have lowerBounds and upperBounds populated
		Collections.sort(lowerBounds);
		Collections.sort(upperBounds);

		ListIterator<Integer> lowerItr = lowerBounds.listIterator();
		ListIterator<Integer> upperItr = upperBounds.listIterator();
		lowerItr.next();

		// we check for overlap and adjacent zip codes
		while (lowerItr.hasNext()) {
			int upper = upperItr.next();
			int lower = lowerItr.next();
			if ((upper + 1) >= (lower)) {
				upperItr.remove();
				lowerItr.remove();
			}
		}
		// merge the lists of lower and upper bounds for return
		ArrayList<int[]> result = new ArrayList<int[]>();

		for (int i = 0; i < lowerBounds.size(); i++) {
			int[] range = { lowerBounds.get(i), upperBounds.get(i) };
			result.add(range);
		}
		return result;
	}
}
