package org.china.lottery.bruce.order3.model;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;

import org.china.lottery.bruce.order3.storage.strategy.LotterySerialNumberStrategyImpl;
import org.china.lottery.bruce.order3.storage.strategy.LotteryStrategy;
import org.china.lottery.bruce.order3.storage.strategy.LotteryStrategyForPlusImpl;

import com.google.common.primitives.Ints;

public class GroupTableData {

	protected int factorIndex = 0;

	protected int groupIndex = 0;

	protected int[][] groupTableData;

	protected int[][] serialPositionResultArray;

	// For all of Group
	protected int maxSerialNumberForGroup = 0;

	protected int maxSerialNumberForGroupStartPosition = 0;

	protected Set<Integer> maxSerialNumberColumnsCodeForGroup;

	// For each Singal Column
	protected int[] maxSerialNumberForEachColumn;

	protected int[] maxSerialNumberForEachColumnStartedColumnPosition;

	protected LotteryStrategy lotteryStrategy = new LotteryStrategyForPlusImpl();

	protected LotterySerialNumberStrategyImpl serialNumberStrategy = new LotterySerialNumberStrategyImpl();

	public void setGroupTableData(int[][] groupTableData) {
		this.groupTableData = groupTableData;
	}

	public GroupTableData createdPositionArrayForWinnerCode(
			int[][] winnerGroupCodes) {

		// 奖号第一行值不需要, 数组少一位
		serialPositionResultArray = new int[winnerGroupCodes[0].length][winnerGroupCodes.length - 1];
		// 从1开始获取矩阵和奖号

		for (int rowIndex = 1; rowIndex < winnerGroupCodes.length; rowIndex++) {
			for (int columnIndex = 0; columnIndex < winnerGroupCodes[0].length; columnIndex++) {
				int[] SerialNumTempArray = groupTableData[rowIndex];
				int winerCode = winnerGroupCodes[rowIndex][columnIndex];

				serialPositionResultArray[columnIndex][rowIndex - 1] = lotteryStrategy
						.getPosition(SerialNumTempArray, winerCode);
			}
		}
		return this;
	}

	/**
	 * For all of GroupTable, Get the Total of group max serial Double-columns.
	 * 
	 * @return
	 */
	public GroupTableData computeMaxGroupAllofNumber() {

		int totalArrayNum = serialPositionResultArray.length;
		int totalInternalArrayNum = serialPositionResultArray[0].length;

		int maxResValue = 0;
		Set<Integer> maxDoubleColumns = new HashSet<Integer>();
		int startedColumnPosition = 0;

		for (int columnNum = 0; columnNum < totalInternalArrayNum; columnNum++) {
			for (int rowNum = 0; rowNum < totalArrayNum; rowNum++) {

				int rootValue = serialPositionResultArray[rowNum][columnNum];

				Set<Integer> set = new HashSet<Integer>();
				set.add(rootValue);

				AtomicInteger maxTempValue = new AtomicInteger(1);
				serialNumberStrategy.setSerialPositionResultArray(
						serialPositionResultArray)
						.recursiveForMaxSerialAllOfGroupNum(rowNum, columnNum,
								rootValue, maxTempValue, set);

				if (maxResValue < maxTempValue.get()) {
					maxResValue = maxTempValue.get();
					startedColumnPosition = columnNum;
					maxDoubleColumns = set;
				}
			}
		}
		this.maxSerialNumberForGroup = maxResValue;
		this.maxSerialNumberColumnsCodeForGroup = maxDoubleColumns;
		this.maxSerialNumberForGroupStartPosition = startedColumnPosition;
		return this;
	}

	/**
	 * For each columns.
	 * 
	 * @return
	 */
	public GroupTableData computeMaxSerialSinglColumn() {

		int totalArrayNum = serialPositionResultArray.length;
		int totalInternalArrayNum = serialPositionResultArray[0].length;

		int[] maxSerialColumnsArray = new int[10];
		int[] startedColumnPositionArray = new int[10];

		for (int columnNum = 0; columnNum < totalInternalArrayNum; columnNum++) {
			for (int rowNum = 0; rowNum < totalArrayNum; rowNum++) {

				int rootValue = serialPositionResultArray[rowNum][columnNum];

				AtomicInteger maxTempValue = new AtomicInteger(1);
				serialNumberStrategy.setSerialPositionResultArray(
						serialPositionResultArray)
						.recursiveForMaxSerialEachColumns(rowNum, columnNum,
								maxTempValue, rootValue, maxSerialColumnsArray);

				if (maxSerialColumnsArray[rootValue] < maxTempValue.get()) {
					maxSerialColumnsArray[rootValue] = maxTempValue.get();
					startedColumnPositionArray[rootValue] = columnNum;
				}
			}
		}
		this.maxSerialNumberForEachColumn = maxSerialColumnsArray;
		this.maxSerialNumberForEachColumnStartedColumnPosition = startedColumnPositionArray;
		return this;
	}

	public void setFactorIndex(int factorIndex) {
		this.factorIndex = factorIndex;
	}

	public void setGroupIndex(int groupIndex) {
		this.groupIndex = groupIndex;
	}

	public int getFactorIndex() {
		return factorIndex;
	}

	public int getGroupIndex() {
		return groupIndex;
	}

	public int[][] getPositionArrayForWinnerCode() {
		return serialPositionResultArray;
	}

	public int[][] getGroupTableData() {
		return groupTableData;
	}

	public int getLength() {
		return groupTableData.length;
	}

	public int[] getMaxSerialNumberForEachColumn() {
		return maxSerialNumberForEachColumn;
	}

	public int[] getMaxSerialNumberForEachColumnStartedColumnPosition() {
		return maxSerialNumberForEachColumnStartedColumnPosition;
	}

	public int getMaxSerialNumberForGroup() {
		return maxSerialNumberForGroup;
	}

	public int getMaxSerialNumberForGroupStartPosition() {
		return maxSerialNumberForGroupStartPosition;
	}

	/**
	 * 获取最长连续的两个列的列号,Set
	 * 
	 * @return
	 */
	public int[] getMaxSerialNumberColumnsCodeForGroup() {
		return Ints.toArray(maxSerialNumberColumnsCodeForGroup);
	}
}
