package org.china.lottery.bruce.order3.storage.datasource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GroupImpl implements Group {

	@Autowired
	private SourceDataStorage sourceDataStorage;

	@Override
	public int[] getGroup(int columnIndex) {

		int[][] winnerCodes = sourceDataStorage.getAllWinnerCodes();
		int[] groupCodes = new int[winnerCodes.length];

		for (int i = 0; i < winnerCodes.length; i++) {
			groupCodes[i] = winnerCodes[i][columnIndex];
		}
		return groupCodes;
	}
}
