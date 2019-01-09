package il.co.topq.testng;

import java.util.ArrayList;
import java.util.List;

public class RangeParser {

	private static final String SINGLE_NUMBER_REGEX = "^\\d+$";
	private static final String RANGE_REGEX = "^(\\d+)-(\\d+)$";

	private RangeParser() {
		// Singleton
	}

	/**
	 * Receives range values, for example, 1-4,7 and parses it to list of integers.
	 * 
	 * @param rangeString range numbers represented as string
	 * @return
	 */
	public static List<Integer> parse(String rangeString) {
		List<Integer> numbers = new ArrayList<>();
		for (String numOrRange : rangeString.split(",")) {
			if (numOrRange.matches(SINGLE_NUMBER_REGEX)) {
				numbers.add(Integer.parseInt(numOrRange));
			} else if (numOrRange.matches(RANGE_REGEX)) {
				int first = Integer.parseInt(numOrRange.split("-")[0]);
				int last = Integer.parseInt(numOrRange.split("-")[1]);
				if (last <= first) {
					continue;
				}
				for (int i = first; i < last; i++) {
					numbers.add(i);
				}
			}
		}
		return numbers;
	}

}
