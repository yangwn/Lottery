package org.china.lottery.bruce.order3.service;

import java.util.ArrayList;
import java.util.List;

import org.china.lottery.bruce.order3.common.ArrayConvertUtils;
import org.china.lottery.bruce.order3.model.GroupTableData;
import org.china.lottery.bruce.order3.model.PageGroupTableData;
import org.china.lottery.bruce.order3.model.ShowPageVO;
import org.china.lottery.bruce.order3.storage.LotteryStroage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LotteryServiceImpl implements LotteryService {

	@Autowired
	private LotteryStroage lotteryStroage;

	private List<List<ShowPageVO>> showPageDataList;

	private List<List<Integer>> winnerCodesList;

	// 一共多少期
	public int getWinnderCycleNum() {
		return lotteryStroage.getWinnerCodesArray().length;
	}

	// 最后一次中奖号码
	public int[] getLatestWinnderCode() {
		return lotteryStroage.getWinnerCodesMatrics()[getWinnderCycleNum() - 1];
	}

	@Override
	public List<List<ShowPageVO>> getShowPageData() {
		return showPageDataList;
	}

	@Override
	public List<List<Integer>> getWinnerCodesList() {
		return winnerCodesList;
	}

	public void createShowPageData() {

		winnerCodesList = ArrayConvertUtils.getListByIntTwoArray(lotteryStroage
				.getShowPageWinnderCodesMatrics());

		PageGroupTableData[][] pageGroupTableDataArray = lotteryStroage
				.getPageGroupTableData();

		GroupTableData[][] allOfGroupTableDataArray = lotteryStroage
				.getAllofGroupTableData();

		showPageDataList = new ArrayList<List<ShowPageVO>>();
		for (int rowIndex = 0; rowIndex < pageGroupTableDataArray.length; rowIndex++) {

			List<ShowPageVO> showPageVOList = new ArrayList<ShowPageVO>();

			for (int columnIndex = 0; columnIndex < pageGroupTableDataArray[rowIndex].length; columnIndex++) {
				PageGroupTableData pageGroupTableData = pageGroupTableDataArray[rowIndex][columnIndex];

				ShowPageVO showPageVO = new ShowPageVO();
				showPageVO.setFactorIndex(pageGroupTableData.getFactorIndex());
				showPageVO.setGroupIndex(pageGroupTableData.getGroupIndex());

				showPageVO
						.setMaxForAllOfGroupHistoryPeriodsRow(allOfGroupTableDataArray[rowIndex][columnIndex]
								.getMaxSerialNumberForGroupStartPosition());
				showPageVO
						.setMaxForAllOfGroupHistorySerialNum(allOfGroupTableDataArray[rowIndex][columnIndex]
								.getMaxSerialNumberForGroup());
				showPageVO
						.setMaxForAllofGroupHistoryWhichColumnsArray(allOfGroupTableDataArray[rowIndex][columnIndex]
								.getMaxSerialNumberColumnsCodeForGroup());

				showPageVO
						.setMaxForEachColumnHistorySerialNumArray(allOfGroupTableDataArray[rowIndex][columnIndex]
								.getMaxSerialNumberForEachColumn());
				showPageVO
						.setMaxForEachcolumnHistorySerialPeriodsRowArray(allOfGroupTableDataArray[rowIndex][columnIndex]
								.getMaxSerialNumberForEachColumnStartedColumnPosition());

				showPageVO
						.setShowPageForMaxSerialNumInWhichColumnsArray(pageGroupTableData
								.getMaxSerialNumberColumnsCodeForGroup());

				showPageVO.setShowPageRowMatriaxTableData(pageGroupTableData
						.getLatestRowGroupTable());

				showPageVO.setRecommendFilterTwoNumber(pageGroupTableData
						.getLatestRowGroupTable(), pageGroupTableData
						.getMaxSerialNumberColumnsCodeForGroup());

				showPageVOList.add(showPageVO);
			}
			showPageDataList.add(showPageVOList);
		}
	}
}
