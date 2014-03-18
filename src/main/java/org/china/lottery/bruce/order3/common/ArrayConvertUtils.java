package org.china.lottery.bruce.order3.common;

import java.util.ArrayList;
import java.util.List;

public class ArrayConvertUtils {

	public static <T> List<List<T>> getListByTwoArray(T[][] arrays) {
		List<List<T>> multiLists = new ArrayList<List<T>>();
		for (T[] array : arrays) {
			if (array == null) {
				continue;
			}
			List<T> list = new ArrayList<T>();
			for (T value : array) {
				list.add(value);
			}
			multiLists.add(list);
		}
		return multiLists;
	}

	public static List<List<Integer>> getListByIntTwoArray(int[][] arrays) {

		List<List<Integer>> multiLists = new ArrayList<List<Integer>>();
		for (int[] array : arrays) {
			if (array == null) {
				continue;
			}
			List<Integer> list = new ArrayList<Integer>();
			for (int value : array) {
				list.add(value);
			}
			multiLists.add(list);
		}
		return multiLists;
	}
}
