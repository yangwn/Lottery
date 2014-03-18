package org.china.lottery.bruce.order3.service;

import java.util.List;

import org.china.lottery.bruce.order3.model.ShowPageVO;

public interface LotteryService {

	public List<List<ShowPageVO>> getShowPageData();

	public List<List<Integer>> getWinnerCodesList();

	public int getWinnderCycleNum();

	public int[] getLatestWinnderCode();

}
