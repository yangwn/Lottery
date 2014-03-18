//package org.china.lottery.bruce.order3.service;
//
//import java.util.Arrays;
//import java.util.HashSet;
//import java.util.Random;
//import java.util.Set;
//import java.util.concurrent.atomic.AtomicInteger;
//
//public class Test2 {
//
//	private static int[] getRandom(int x, int y) {
//		int[] arr = new int[x];
//		int z = 0;
//		for (int i = 0; i < y; i++) {
//			int num = new Random().nextInt(y) + 1;
//			if (z >= x) {
//				break;
//			} else if (!chenkIn(num, arr)) {
//				arr[z++] = num;
//			}
//		}
//		return arr;
//	}
//
//	private static boolean chenkIn(int num, int[] arr) {
//		boolean flag = false;
//		for (int x = 0; x < arr.length; x++) {
//			if (num == arr[x]) {
//				flag = true;
//				break;
//			}
//		}
//		return flag;
//	}
//	//
//	// private static int[][] ARRAY = new int[3][4000];
//	// static {
//	// for (int i = 0; i < 3; i++) {
//	// ARRAY[i] = getRandom(4000, 10);
//	// }
//	// }
//
//	private static int[][] ARRAY = {{7, 7, 7, 7, 6}, {5, 4, 5, 0, 8},
//			{9, 6, 9, 4, 5}};
//
//	public static void openEach(int row, int column, AtomicInteger maxResValue,
//			int rootValue, int[] maxColumnArray) {
//
//		int nextColumn = column + 1;
//		while (nextColumn < ARRAY[0].length) {
//
//			boolean isContinue = true;
//			int loopEndTime = 0;
//
//			for (int nextRow = 0; nextRow < ARRAY.length; nextRow++) {
//
//				int elementValue = ARRAY[nextRow][nextColumn];
//				int subValue = rootValue - elementValue;
//
//				if (subValue != 0) {
//					loopEndTime++;
//					if (loopEndTime > 2) {
//						isContinue = false;
//						break;
//					}
//					continue;
//				}
//
//				maxResValue.incrementAndGet();
//				openEach(nextRow, nextColumn, maxResValue, rootValue,
//						maxColumnArray);
//				return; // 递归出口
//			}
//			nextColumn++;
//			if (!isContinue) {
//				return;
//			}
//		}
//	}
//
//	public static void operateTTEachMain() {
//
//		int totalArrayNum = ARRAY.length; // 3
//		int totalInternalArrayNum = ARRAY[0].length; // 5
//
//		int[] maxColumnArray = new int[10];
//		int[] startedColumnPositionArray = new int[10];
//
//		for (int columnNum = 0; columnNum < totalInternalArrayNum; columnNum++) {
//			for (int rowNum = 0; rowNum < totalArrayNum; rowNum++) {
//
//				int rootValue = ARRAY[rowNum][columnNum];
//
//				AtomicInteger maxTempValue = new AtomicInteger(1);
//				openEach(rowNum, columnNum, maxTempValue, rootValue,
//						maxColumnArray);
//
//				if (maxColumnArray[rootValue] < maxTempValue.get()) {
//					maxColumnArray[rootValue] = maxTempValue.get();
//					startedColumnPositionArray[rootValue] = columnNum;
//				}
//			}
//		}
//
//		System.out.println(Arrays.toString(maxColumnArray) + ","
//				+ Arrays.toString(startedColumnPositionArray));
//	}
//
//	public static void openTT(int row, int column, int rootValue,
//			AtomicInteger maxResValue, Set<Integer> set) {
//
//		int nextColumn = column + 1;
//		while (nextColumn < ARRAY[0].length) {
//
//			boolean isContinue = true;
//			int loopEndTime = 0;
//
//			for (int nextRow = 0; nextRow < ARRAY.length; nextRow++) {
//
//				int elementValue = ARRAY[nextRow][nextColumn];
//				int subValue = rootValue - elementValue;
//
//				if (set.size() == 2 && !set.contains(elementValue)) {
//					loopEndTime++;
//					if (loopEndTime > 2) {
//						isContinue = false;
//						break;
//					}
//					continue;
//				}
//
//				if ((subValue != 0 && Math.abs(subValue) != 1)) {
//					loopEndTime++;
//					if (loopEndTime > 2) {
//						isContinue = false;
//						break;
//					}
//					continue;
//				}
//
//				if (set.size() < 2) {
//					set.add(elementValue);
//				} else {
//					if (!set.contains(elementValue)) {
//						break;
//					}
//				}
//				maxResValue.incrementAndGet();
//				openTT(nextRow, nextColumn, rootValue, maxResValue, set);
//				return; // 递归出口
//			}
//			nextColumn++;
//			if (!isContinue) {
//				return;
//			}
//		}
//	}
//
//	public static void operateTTMain() {
//
//		int totalArrayNum = ARRAY.length; // 3
//		int totalInternalArrayNum = ARRAY[0].length; // 5
//
//		int maxResValue = 0;
//		Set<Integer> maxDoubleColumns = new HashSet<Integer>();
//		int startedColumnPosition = 0;
//
//		for (int columnNum = 0; columnNum < totalInternalArrayNum; columnNum++) {
//			for (int rowNum = 0; rowNum < totalArrayNum; rowNum++) {
//
//				int rootValue = ARRAY[rowNum][columnNum];
//
//				Set<Integer> set = new HashSet<Integer>();
//				set.add(rootValue);
//
//				AtomicInteger maxTempValue = new AtomicInteger(1);
//				openTT(rowNum, columnNum, rootValue, maxTempValue, set);
//
//				if (maxResValue < maxTempValue.get()) {
//					maxResValue = maxTempValue.get();
//					startedColumnPosition = columnNum;
//					maxDoubleColumns = set;
//				}
//			}
//		}
//		System.out.println(maxResValue + ", startedColumnPosition:"
//				+ startedColumnPosition + ", maxDoubleColumns:"
//				+ Arrays.toString(maxDoubleColumns.toArray()));
//	}
//
//	public static void main(String[] args) {
//		operateTTEachMain();
//		operateTTMain();
//	}
//
//}
