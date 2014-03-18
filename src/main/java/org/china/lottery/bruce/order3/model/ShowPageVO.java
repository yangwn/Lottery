package org.china.lottery.bruce.order3.model;

import java.util.List;

import org.china.lottery.bruce.order3.common.ArrayConvertUtils;

import com.google.common.primitives.Ints;

public class ShowPageVO {

	private int groupIndex; // 组号

	private int factorIndex; // 1～9的加权值号

	// Group & History
	private int[] recommendFilterTwoNumber; // 推荐排除的两个号码

	private int maxForAllOfGroupHistorySerialNum; // 全表最大的连续列长

	private int maxForAllOfGroupHistoryPeriodsRow; // 全表最大的连续列长的起始行号

	private List<Integer> maxForAllofGroupHistoryWhichColumnsArray; // 全表最大的连续列长的两个列号

	private List<Integer> maxForEachColumnHistorySerialNumArray; // 全表针对每个列的最大连续长度

	private List<Integer> maxForEachcolumnHistorySerialPeriodsRowArray; // 全表针对每个列的最大连续长度的起始行号

	// Page & History
	private List<List<Integer>> showPageRowMatriaxTableData; // 显示到页面上的表信息

	private List<Integer> showPageForMaxSerialNumInWhichColumnsArray; // 显示到页面上的最长的两个连续列号

	// All of Tables
	private List<List<Integer>> allOfGroupTableData; // 全表全信息

	public int getGroupIndex() {
		return groupIndex;
	}

	public void setGroupIndex(int groupIndex) {
		this.groupIndex = groupIndex;
	}

	public int getFactorIndex() {
		return factorIndex;
	}

	public void setFactorIndex(int factorIndex) {
		this.factorIndex = factorIndex;
	}

	public String getRecommendFilterTwoNumber() {
		return Ints.join(",", recommendFilterTwoNumber);
	}

	public void setRecommendFilterTwoNumber(
			int[][] showPageRowMatriaxTableData,
			int[] showPageForMaxSerialNumInWhichColumnsArray) {

		recommendFilterTwoNumber = new int[showPageForMaxSerialNumInWhichColumnsArray.length];
		for (int i = 0; i < showPageForMaxSerialNumInWhichColumnsArray.length; i++) {
			this.recommendFilterTwoNumber[i] = showPageRowMatriaxTableData[showPageRowMatriaxTableData.length - 1][showPageForMaxSerialNumInWhichColumnsArray[i]];
		}
	}

	// 最长连续数
	public int getMaxForAllOfGroupHistorySerialNum() {
		return maxForAllOfGroupHistorySerialNum;
	}

	public void setMaxForAllOfGroupHistorySerialNum(
			int maxForAllOfGroupHistorySerialNum) {
		this.maxForAllOfGroupHistorySerialNum = maxForAllOfGroupHistorySerialNum;
	}

	// 最长的期号
	public int getMaxForAllOfGroupHistoryPeriodsRow() {
		return maxForAllOfGroupHistoryPeriodsRow;
	}

	public void setMaxForAllOfGroupHistoryPeriodsRow(
			int maxForAllOfGroupHistoryPeriodsRow) {
		this.maxForAllOfGroupHistoryPeriodsRow = maxForAllOfGroupHistoryPeriodsRow;
	}

	public List<Integer> getMaxForAllofGroupHistoryWhichColumnsArray() {
		return maxForAllofGroupHistoryWhichColumnsArray;
	}

	public void setMaxForAllofGroupHistoryWhichColumnsArray(
			int[] maxForAllofGroupHistoryWhichColumnsArray) {
		this.maxForAllofGroupHistoryWhichColumnsArray = Ints
				.asList(maxForAllofGroupHistoryWhichColumnsArray);
	}

	public List<Integer> getMaxForEachColumnHistorySerialNumArray() {
		return maxForEachColumnHistorySerialNumArray;
	}

	public void setMaxForEachColumnHistorySerialNumArray(
			int[] maxForEachColumnHistorySerialNumArray) {
		this.maxForEachColumnHistorySerialNumArray = Ints
				.asList(maxForEachColumnHistorySerialNumArray);
	}

	public List<List<Integer>> getShowPageRowMatriaxTableData() {
		return showPageRowMatriaxTableData;
	}

	public void setShowPageRowMatriaxTableData(
			int[][] showPageRowMatriaxTableData) {
		this.showPageRowMatriaxTableData = ArrayConvertUtils
				.getListByIntTwoArray(showPageRowMatriaxTableData);
	}

	public List<Integer> getShowPageForMaxSerialNumInWhichColumnsArray() {
		return showPageForMaxSerialNumInWhichColumnsArray;
	}

	public void setShowPageForMaxSerialNumInWhichColumnsArray(
			int[] showPageForMaxSerialNumInWhichColumnsArray) {
		this.showPageForMaxSerialNumInWhichColumnsArray = Ints
				.asList(showPageForMaxSerialNumInWhichColumnsArray);
	}

	public List<Integer> getMaxForEachcolumnHistorySerialPeriodsRowArray() {
		return maxForEachcolumnHistorySerialPeriodsRowArray;
	}

	public void setMaxForEachcolumnHistorySerialPeriodsRowArray(
			int[] maxForEachcolumnHistorySerialPeriodsRowArray) {
		this.maxForEachcolumnHistorySerialPeriodsRowArray = Ints
				.asList(maxForEachcolumnHistorySerialPeriodsRowArray);
	}

	public List<List<Integer>> getAllOfGroupTableData() {
		return allOfGroupTableData;
	}

	public void setAllOfGroupTableData(int[][] allOfGroupTableData) {
		this.allOfGroupTableData = ArrayConvertUtils
				.getListByIntTwoArray(allOfGroupTableData);
	}

}
