package org.china.lottery.bruce.order3.controller;

import java.util.List;

import org.china.lottery.bruce.order3.model.ShowPageVO;
import org.china.lottery.bruce.order3.service.LotteryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.common.primitives.Ints;

@Controller
public class LotteryController {

	private final Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	private LotteryService lotteryService;

	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public String order3Index(ModelMap model) {

		logger.info("visit the main url...");

		List<List<ShowPageVO>> showPageDataTable = lotteryService
				.getShowPageData();
		List<List<Integer>> showPageWinnerCodes = lotteryService
				.getWinnerCodesList();

		// 排除推荐号

		int latestCycleNum = lotteryService.getWinnderCycleNum();
		String latestWinnerCode = Ints.join(",",
				lotteryService.getLatestWinnderCode());

		model.addAttribute("showPageWinnerTable", showPageWinnerCodes);
		model.addAttribute("latestCycleNum", latestCycleNum);
		model.addAttribute("latestWinnerCode", latestWinnerCode);

		model.addAttribute("showPageDataTable", showPageDataTable);
		model.addAttribute("groupIndexLength", showPageDataTable.size() - 1);
		model.addAttribute("factorIndexLength", showPageDataTable.get(0).size());

		return "lottery";
	}

	@RequestMapping(value = "/greetmsg/{name}", method = RequestMethod.GET)
	public @ResponseBody
	String greetPath(@PathVariable String name) {
		logger.debug("Method greetPath");
		return name;
	}

	@RequestMapping(value = "/greet", method = RequestMethod.GET)
	public String greetRequest(
			@RequestParam(required = false, defaultValue = "John Doe") String name,
			ModelMap model) {
		logger.debug("Method greetRequest");
		model.addAttribute("name", name);
		model.addAttribute("contentName", "title");
		return "forms";
	}
}
