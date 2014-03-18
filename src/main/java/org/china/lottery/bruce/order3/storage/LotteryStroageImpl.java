package org.china.lottery.bruce.order3.storage;

import org.china.lottery.bruce.order3.model.GroupTableData;
import org.china.lottery.bruce.order3.model.PageGroupTableData;
import org.china.lottery.bruce.order3.storage.datasource.SequnceDataTable;
import org.china.lottery.bruce.order3.storage.datasource.SourceDataStorage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class LotteryStroageImpl implements LotteryStroage {

	@Autowired
	private SequnceDataTable sequnceDataTable;

	@Autowired
	private SourceDataStorage sourceDataStorage;

	@Override
	public int[][] getWinnerCodesMatrics() {
		return sourceDataStorage.getAllWinnerCodes();
	}

	@Override
	public int[] getWinnerCodesArray() {
		return sourceDataStorage.getAllWinnerArray();
	}

	@Override
	public GroupTableData[][] getAllofGroupTableData() {
		return sequnceDataTable.getAllofGroupTableData();
	}

	@Override
	public GroupTableData[] getGroupTableByGroupNum(int groupIndex) {
		return getAllofGroupTableData()[groupIndex];
	}

	@Override
	public GroupTableData getGroupTableByGroupIndexAndFactorIndex(
			int groupIndex, int fatorIndex) {
		return getAllofGroupTableData()[groupIndex][fatorIndex];
	}

	@Override
	public PageGroupTableData[][] getPageGroupTableData() {
		return sequnceDataTable.getAllofPageGroupTableData();
	}

	@Override
	public PageGroupTableData[] getPageGroupTableData(int groupIndex) {
		return getPageGroupTableData()[groupIndex];
	}

	@Override
	public PageGroupTableData getPageGroupTableData(int groupIndex,
			int fatorIndex) {
		return getPageGroupTableData()[groupIndex][fatorIndex];
	}

	@Override
	public int[][] getShowPageWinnderCodesMatrics() {
		return sourceDataStorage.getWinnerCodesToShow();
	}

	@Override
	public int[] getShowPageWinnderCodesArray() {
		return sourceDataStorage.getWinnerCodesArrayToShow();
	}

}
