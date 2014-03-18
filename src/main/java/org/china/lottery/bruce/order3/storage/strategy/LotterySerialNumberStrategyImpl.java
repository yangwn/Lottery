package org.china.lottery.bruce.order3.storage.strategy;

import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;

import com.google.common.base.Preconditions;

public class LotterySerialNumberStrategyImpl {

	private int[][] serialPositionResultArray;

	public LotterySerialNumberStrategyImpl setSerialPositionResultArray(
			int[][] serialPositionResultArray) {
		this.serialPositionResultArray = serialPositionResultArray;
		return this;
	}

	public void recursiveForMaxSerialEachColumns(int row, int column,
			AtomicInteger maxResValue, int rootValue, int[] maxColumnArray) {

		Preconditions
				.checkNotNull(
						serialPositionResultArray,
						"serialPositionResultArray is NULL, Call setSerialPositionResultArray function !!!");

		int nextColumn = column + 1;
		while (nextColumn < serialPositionResultArray[0].length) {

			boolean isContinue = true;
			int loopEndTime = 0;

			for (int nextRow = 0; nextRow < serialPositionResultArray.length; nextRow++) {

				int elementValue = serialPositionResultArray[nextRow][nextColumn];
				int subValue = rootValue - elementValue;
				if (subValue != 0) {
					loopEndTime++;
					if (loopEndTime > 2) {
						isContinue = false;
						break;
					}
					continue;
				}
				maxResValue.incrementAndGet();
				recursiveForMaxSerialEachColumns(nextRow, nextColumn,
						maxResValue, rootValue, maxColumnArray);
				return; // 递归出口
			}
			nextColumn++;
			if (!isContinue) {
				return;
			}
		}
	}

	public void recursiveForMaxSerialAllOfGroupNum(int row, int column,
			int rootValue, AtomicInteger maxResValue, Set<Integer> set) {
		int nextColumn = column + 1;
		while (nextColumn < serialPositionResultArray[0].length) {

			boolean isContinue = true;
			int loopEndTime = 0;

			for (int nextRow = 0; nextRow < serialPositionResultArray.length; nextRow++) {

				int elementValue = serialPositionResultArray[nextRow][nextColumn];
				int subValue = rootValue - elementValue;

				if (set.size() == 2 && !set.contains(elementValue)) {
					loopEndTime++;
					if (loopEndTime > 2) {
						isContinue = false;
						break;
					}
					continue;
				}
				if ((subValue != 0 && Math.abs(subValue) != 1)) {
					loopEndTime++;
					if (loopEndTime > 2) {
						isContinue = false;
						break;
					}
					continue;
				}
				if (set.size() < 2) {
					set.add(elementValue);
				} else {
					if (!set.contains(elementValue)) {
						break;
					}
				}
				maxResValue.incrementAndGet();
				recursiveForMaxSerialAllOfGroupNum(nextRow, nextColumn,
						rootValue, maxResValue, set);
				return; // 递归出口
			}
			nextColumn++;
			if (!isContinue) {
				return;
			}
		}
	}
}
