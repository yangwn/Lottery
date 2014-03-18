//package org.china.lottery.bruce.order3.service;
//
//import java.util.HashSet;
//import java.util.Set;
//
//public class Test {
//
//	public static void main(String[] args) {
//		int maxSerialNumberForGroup = 0;
//
//		int[] CONTAINARRAY = {-1, 0};
//
//		int[][] serialPositionResultArray = {{7, 3, 2, 7, 6}, {5, 4, 5, 0, 8},
//				{9, 6, 9, 4, 5}};
//
//		for (int i = 0; i < serialPositionResultArray.length; i++) {
//			for (int j = 0; j < serialPositionResultArray[i].length; j++) {
//				int currentValue = serialPositionResultArray[j][i]; // 7
//
//				int maxSerialNumberForGroupTemp = 1;
//				int m = i;
//				int n = (j == 0) ? 1 : j;
//
//				int distinct = 0;
//				int startStatus = 0;
//				boolean isContinue = true;
//
//				while (isContinue) {
//					int nextValue = serialPositionResultArray[m][n];
//					int resValue = nextValue - currentValue;
//
//					if (resValue != 0 && Math.abs(resValue) != 1) {
//						m++;
//						continue;
//					}
//
//					if (startStatus == 0 || distinct == 0) {
//						distinct = resValue;
//					}
//					startStatus++;
//
//					if (resValue == distinct || resValue == 0) {
//						maxSerialNumberForGroupTemp++;
//						if (maxSerialNumberForGroupTemp > maxSerialNumberForGroup) {
//							maxSerialNumberForGroup = maxSerialNumberForGroupTemp;
//						}
//					}
//					m++;
//				}
//			}
//		}
//
//	}
//	public static void operateForGroupTotalNum(int[] serialPositionResultArray) {
//
//		int maxSerialNumberForGroup = 0;
//		// For Group
//		for (int i = 0; i < serialPositionResultArray.length; i++) {
//
//			Set<Integer> set = new HashSet<Integer>();
//			int startedStatus = 0;
//
//			int currentValue = serialPositionResultArray[i];
//			int maxSerialNumberForGroupTemp = 1;
//
//			for (int j = i + 1; j < serialPositionResultArray.length; j++) {
//
//				int nextValue = serialPositionResultArray[j];
//				int resValue = nextValue - currentValue;
//
//				if (resValue != 0 && Math.abs(resValue) != 1) {
//					if (maxSerialNumberForGroupTemp > maxSerialNumberForGroup) {
//						maxSerialNumberForGroup = maxSerialNumberForGroupTemp;
//					}
//					set.clear();
//					i = j - 1;
//					break;
//				}
//
//				if (startedStatus == 0 || set.size() == 1) {
//					set.add(currentValue);
//					set.add(nextValue);
//				}
//				startedStatus++;
//
//				if (ifCloseColumn(currentValue, nextValue, set)) {
//					maxSerialNumberForGroupTemp++;
//					if (maxSerialNumberForGroupTemp > maxSerialNumberForGroup) {
//						maxSerialNumberForGroup = maxSerialNumberForGroupTemp;
//					}
//				} else {
//					i = j - 1;
//					break;
//				}
//			}
//		}
//		System.out
//				.println("maxSerialNumberForGroup:" + maxSerialNumberForGroup);
//	}
//
//	public static void operateEachColumn(int[] serialPositionResultArray) {
//
//		int[] serialNumberForEachColumn = new int[10];
//		// For Each Column
//		for (int i = 0; i < serialPositionResultArray.length; i++) {
//
//			int currentValue = serialPositionResultArray[i];
//			int maxSerialForEachColumnNumTemp = 1;
//
//			for (int j = i + 1; j < serialPositionResultArray.length; j++) {
//
//				int nextValue = serialPositionResultArray[j];
//				int resValue = nextValue - currentValue;
//
//				if (resValue != 0) {
//					if (serialNumberForEachColumn[currentValue] < maxSerialForEachColumnNumTemp) {
//						serialNumberForEachColumn[currentValue] = maxSerialForEachColumnNumTemp;
//					}
//					i = j - 1;
//					break;
//				}
//
//				if (ifSameColumn(currentValue, nextValue)) {
//					maxSerialForEachColumnNumTemp++;
//					if (serialNumberForEachColumn[currentValue] < maxSerialForEachColumnNumTemp) {
//						serialNumberForEachColumn[currentValue] = maxSerialForEachColumnNumTemp;
//					}
//				} else {
//					i = j - 1;
//					break;
//				}
//			}
//		}
//	}
//
//	// /**
//	// * @param args
//	// */
//	// public static void main(String[] args) {
//	//
//	// int[][] serialPositionResultArray = {{5, 7, 9}, {3, 4, 6}, {2, 5, 9},
//	// {0, 4, 7}, {5, 6, 8}};
//	//
//	// int[] serialNumberForEachColumn = new int[10];
//	// int maxSerialNumberForGroup = 0;
//	//
//	// // For Group
//	// for (int i = 0; i < serialPositionResultArray.length; i++) {
//	//
//	// Set<Integer> set = new HashSet<Integer>();
//	// int startedStatus = 0;
//	//
//	// int currentValue = serialPositionResultArray[i];
//	// int maxSerialNumberForGroupTemp = 1;
//	//
//	// for (int j = i + 1; j < serialPositionResultArray.length; j++) {
//	//
//	// int nextValue = serialPositionResultArray[j];
//	// int resValue = nextValue - currentValue;
//	//
//	// if (resValue != 0 && Math.abs(resValue) != 1) {
//	// if (maxSerialNumberForGroupTemp > maxSerialNumberForGroup) {
//	// maxSerialNumberForGroup = maxSerialNumberForGroupTemp;
//	// }
//	// set.clear();
//	// i = j - 1;
//	// break;
//	// }
//	//
//	// if (startedStatus == 0 || set.size() == 1) {
//	// set.add(currentValue);
//	// set.add(nextValue);
//	// }
//	// startedStatus++;
//	//
//	// if (ifCloseColumn(currentValue, nextValue, set)) {
//	// maxSerialNumberForGroupTemp++;
//	// if (maxSerialNumberForGroupTemp > maxSerialNumberForGroup) {
//	// maxSerialNumberForGroup = maxSerialNumberForGroupTemp;
//	// }
//	// } else {
//	// i = j - 1;
//	// break;
//	// }
//	//
//	// }
//	// }
//	//
//	// // For Each Column
//	// for (int i = 0; i < serialPositionResultArray.length; i++) {
//	//
//	// int currentValue = serialPositionResultArray[i];
//	// int maxSerialForEachColumnNumTemp = 1;
//	//
//	// for (int j = i + 1; j < serialPositionResultArray.length; j++) {
//	//
//	// int nextValue = serialPositionResultArray[j];
//	// int resValue = nextValue - currentValue;
//	//
//	// if (resValue != 0) {
//	// if (serialNumberForEachColumn[currentValue] <
//	// maxSerialForEachColumnNumTemp) {
//	// serialNumberForEachColumn[currentValue] = maxSerialForEachColumnNumTemp;
//	// }
//	// i = j - 1;
//	// break;
//	// }
//	//
//	// if (ifSameColumn(currentValue, nextValue)) {
//	// maxSerialForEachColumnNumTemp++;
//	// if (serialNumberForEachColumn[currentValue] <
//	// maxSerialForEachColumnNumTemp) {
//	// serialNumberForEachColumn[currentValue] = maxSerialForEachColumnNumTemp;
//	// }
//	// } else {
//	// i = j - 1;
//	// break;
//	// }
//	// }
//	// }
//	//
//	// System.out.println("serialNumberForEachColumn:"
//	// + Arrays.toString(serialNumberForEachColumn));
//	// System.out
//	// .println("maxSerialNumberForGroup:" + maxSerialNumberForGroup);
//	//
//	// }
//	private static boolean ifSameColumn(int currentPosition, int nextPosition) {
//		return (nextPosition - currentPosition) == 0;
//	}
//
//	private static boolean ifCloseColumn(int currentPosition, int nextPosition,
//			Set<Integer> serailColumnNumSet) {
//
//		return serailColumnNumSet.contains(currentPosition)
//				&& serailColumnNumSet.contains(nextPosition);
//	}
//
//}
