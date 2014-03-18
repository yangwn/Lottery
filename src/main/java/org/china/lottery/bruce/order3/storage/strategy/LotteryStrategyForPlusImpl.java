package org.china.lottery.bruce.order3.storage.strategy;

import org.springframework.stereotype.Service;

import com.google.common.primitives.Ints;

/**
 * 
 * @author yangwunan
 * 
 */

@Service("lotteryPlusStrategy")
public class LotteryStrategyForPlusImpl implements LotteryStrategy {

	@Override
	public int[] getSequenceArray(int lastValue, int factor) {

		int startVale = lastValue + factor;
		if (startVale >= 10) {
			startVale = startVale - 10;
		}
		int[] sortedArray = new int[10];
		int loopIndex = 0;
		while (loopIndex < 10) {
			sortedArray[loopIndex] = startVale;
			startVale = (startVale == 9) ? 0 : (++startVale);
			loopIndex++;
		}
		return sortedArray;
	}

	@Override
	public int getPosition(int[] sortedArray, int value) {
		return Ints.indexOf(sortedArray, value);
	}

	@Override
	public int getNextValue(int currentVale) {
		if (currentVale >= 9) {
			currentVale = 0;
		} else {
			currentVale++;
		}
		return currentVale;
	}

	@Override
	public int getLastValue(int currentVale) {
		if (currentVale == 0) {
			currentVale = 9;
		} else {
			currentVale--;
		}
		return currentVale;
	}

}
