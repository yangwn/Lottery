package org.china.lottery.bruce.order3.storage.datasource;

import org.china.lottery.bruce.order3.model.GroupTableData;
import org.china.lottery.bruce.order3.model.PageGroupTableData;
import org.china.lottery.bruce.order3.storage.strategy.LotteryStrategy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

@Repository
public class SequnceDataTableImpl implements SequnceDataTable {

	private final Logger logger = LoggerFactory.getLogger(getClass());

	private final static int groupTotalNum = 3;

	private final static int factorTotalNum = 9;

	@Value("${winner.show.number}")
	private int showWinnerRowNumber = 20;

	@Autowired
	private SourceDataStorage sourceDataStorage;

	@Autowired
	private LotteryStrategy lotteryPlusStrategy;

	@Autowired
	private Group groupImpl;

	private GroupTableData[][] allOfGroupTable;

	// latest show rows
	private PageGroupTableData[][] allofPageGroupTableData;

	public void createAllofGroupTable() {

		int[][] winnerCodes = sourceDataStorage.getAllWinnerCodes();
		int[][] showPageWinnerCodes = sourceDataStorage.getWinnerCodesToShow();

		allOfGroupTable = new GroupTableData[groupTotalNum][];
		allofPageGroupTableData = new PageGroupTableData[groupTotalNum][];

		try {
			for (int groupIndex = 0; groupIndex < groupTotalNum; groupIndex++) {

				allOfGroupTable[groupIndex] = new GroupTableData[factorTotalNum];
				allofPageGroupTableData[groupIndex] = new PageGroupTableData[factorTotalNum];

				for (int factorIndex = 0; factorIndex < factorTotalNum; factorIndex++) {

					int[][] groupTable = new int[winnerCodes.length + 1][];
					for (int rowIndex = 0; rowIndex < winnerCodes.length; rowIndex++) {
						int[] sequenceArray = lotteryPlusStrategy
								.getSequenceArray(
										winnerCodes[rowIndex][groupIndex],
										factorIndex + 1); // getSequenceArray生成矩阵方法从1开始
						groupTable[rowIndex + 1] = sequenceArray;
					}

					GroupTableData groupTableData = new GroupTableData();
					groupTableData.setGroupTableData(groupTable);
					groupTableData.setFactorIndex(factorIndex);
					groupTableData.setGroupIndex(groupIndex);
					groupTableData
							.createdPositionArrayForWinnerCode(winnerCodes)
							.computeMaxGroupAllofNumber()
							.computeMaxSerialSinglColumn();

					allOfGroupTable[groupIndex][factorIndex] = groupTableData;

					// process the latest row to show
					PageGroupTableData pageGroupTableData = new PageGroupTableData();
					pageGroupTableData.setGroupTableData(groupTable);
					pageGroupTableData
							.setLatestWinnderCodes(showPageWinnerCodes);
					pageGroupTableData.setShowNumber(showWinnerRowNumber);
					pageGroupTableData.setGroupIndex(groupIndex);
					pageGroupTableData.setFactorIndex(factorIndex);

					pageGroupTableData.createdLatest20GroupTable()
							.createdPositionArrayForWinnerCode()
							.computeMaxGroupAllofNumber()
							.computeMaxSerialSinglColumn();

					allofPageGroupTableData[groupIndex][factorIndex] = pageGroupTableData;
				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		logger.info("............StartUp -- The BigMaxtrics Table have created finish, GoGoGo !!!........");
	}

	@Override
	public GroupTableData[][] getAllofGroupTableData() {
		return allOfGroupTable;
	}

	@Override
	public PageGroupTableData[][] getAllofPageGroupTableData() {
		return allofPageGroupTableData;
	}
}
