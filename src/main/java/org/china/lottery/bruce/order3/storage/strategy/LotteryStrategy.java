package org.china.lottery.bruce.order3.storage.strategy;

public interface LotteryStrategy {

	public int[] getSequenceArray(int lastValue, int factor);

	public int getPosition(int[] sortedArray, int value);

	public int getNextValue(int currentVale);

	public int getLastValue(int currentVale);
	
}
