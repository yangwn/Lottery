package org.china.lottery.bruce.order3.storage;

import org.china.lottery.bruce.order3.model.GroupTableData;
import org.china.lottery.bruce.order3.model.PageGroupTableData;

public interface LotteryStroage {
	
	public int[][] getShowPageWinnderCodesMatrics();

	public int[] getShowPageWinnderCodesArray();

	public int[] getWinnerCodesArray();

	public int[][] getWinnerCodesMatrics();

	public GroupTableData[][] getAllofGroupTableData();

	public GroupTableData[] getGroupTableByGroupNum(int groupIndex);

	public GroupTableData getGroupTableByGroupIndexAndFactorIndex(
			int groupIndex, int fatorIndex);

	public PageGroupTableData[][] getPageGroupTableData();

	public PageGroupTableData[] getPageGroupTableData(int groupIndex);

	public PageGroupTableData getPageGroupTableData(int groupIndex,
			int fatorIndex);

}
