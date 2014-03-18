package org.china.lottery.bruce.order3.common;

import java.util.List;

public class ShowColorUtils {

	public static String getColor(List<List<Integer>> winnerCodes,
			int rowIndex, int groupIndex, int showNum) {

		if (winnerCodes.size() <= rowIndex) {
			return "";
		}
		int res = winnerCodes.get(rowIndex).get(groupIndex);
		if (res == showNum) {
			return "d";
		} else if (winnerCodes.get(rowIndex).contains(showNum)) {
			return "w";
		} else {
			return "";
		}

	}
}
