package org.china.lottery.bruce.order3.model;

public class PageGroupTableData extends GroupTableData {

	private int[][] latestRowGroupTable;

	private int[][] latestWinnderCodes;

	private int showRowNumber = 20;

	public void setShowNumber(int showRowNumber) {
		this.showRowNumber = showRowNumber;
	}

	public void setLatestWinnderCodes(int[][] latestWinnderCodes) {
		this.latestWinnderCodes = latestWinnderCodes;
	}

	public PageGroupTableData createdLatest20GroupTable() {

		if (groupTableData.length < showRowNumber) {
			showRowNumber = groupTableData.length; // 获取矩阵多一行数据
		}
		latestRowGroupTable = new int[showRowNumber][];
		int k = 0;
		for (int i = groupTableData.length - showRowNumber; i < groupTableData.length; i++) {
			latestRowGroupTable[k] = groupTableData[i];
			k++;
		}
		return this;
	}

	/**
	 * 前N个winnerCodes
	 */
	public GroupTableData createdPositionArrayForWinnerCode() {

		// 奖号第一行值不需要, 数组少一位
		serialPositionResultArray = new int[latestWinnderCodes[0].length][latestWinnderCodes.length - 1];
		// 从1开始获取矩阵和奖号

		for (int rowIndex = 1; rowIndex < latestWinnderCodes.length; rowIndex++) {
			for (int columnIndex = 0; columnIndex < latestWinnderCodes[0].length; columnIndex++) {
				int[] SerialNumTempArray = latestRowGroupTable[rowIndex - 1];
				int winerCode = latestWinnderCodes[rowIndex][columnIndex];

				serialPositionResultArray[columnIndex][rowIndex - 1] = lotteryStrategy
						.getPosition(SerialNumTempArray, winerCode);
			}
		}
		return this;
	}

	public int[][] getLatestRowGroupTable() {
		return this.latestRowGroupTable;
	}

	public int[][] getLatestWinnderCodes() {
		return this.latestWinnderCodes;
	}
}
