package org.china.lottery.bruce.order3.storage.datasource;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import com.google.common.io.Files;

@Repository
public class SourceDataStorageImpl implements SourceDataStorage {

	private final static String CRLF = System.getProperty("line.separator");

	private File winnerSourceDatafile;
	private int[][] winnerCodes;
	private int[] winnerCodeArray;

	private int[][] latestWinnderCodes;

	@Value("${winner.file.url}")
	private String url;

	@Value("${winner.show.number}")
	private int showWinnerRowNumber = 20;

	public void init() {
		loadWinnerFile();
		createLatestWinnderCodesToShow();
	}

	@Override
	public void loadWinnerFile() {
		winnerSourceDatafile = new File(url);
		if (Files.isFile().apply(winnerSourceDatafile)) {
			new FileNotFoundException("文件: " + url + " 不存在!!!");
		}
		try {
			List<String> winnerCodeStrList = Files.readLines(
					winnerSourceDatafile, Charset.defaultCharset());

			List<Integer> winnerCodesList = new ArrayList<Integer>();
			for (String winnerCodeStr : winnerCodeStrList) {
				if (StringUtils.isEmpty(winnerCodeStr)) {
					continue;
				}
				winnerCodesList.add(Integer.parseInt(StringUtils
						.trim(winnerCodeStr)));
			}

			int lotteryFileLength = winnerCodesList.size();
			winnerCodes = new int[lotteryFileLength][];
			winnerCodeArray = new int[lotteryFileLength];

			for (int i = 0; i < lotteryFileLength; i++) {

				int winnerCode = winnerCodesList.get(i);
				winnerCodeArray[i] = winnerCode;

				winnerCodes[i] = new int[3];
				winnerCodes[i][2] = winnerCode % 10; // firstPositionValue
				winnerCodes[i][1] = (winnerCode / 10) % 10; // secondPositionValue
				winnerCodes[i][0] = (winnerCode / 100) % 10; // thirdPositionValue
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void createLatestWinnderCodesToShow() {

		if (winnerCodes.length < showWinnerRowNumber) {
			showWinnerRowNumber = winnerCodes.length;
		}
		latestWinnderCodes = new int[showWinnerRowNumber][];

		int k = 0;
		for (int i = winnerCodes.length - showWinnerRowNumber; i < winnerCodes.length; i++) {
			latestWinnderCodes[k] = winnerCodes[i];
			k++;
		}
	}

	@Override
	public void refresh() {
		winnerCodes = null;
		winnerSourceDatafile = null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public File getSourceFile() {
		return winnerSourceDatafile;
	}

	@Override
	public int[][] getAllWinnerCodes() {
		return winnerCodes;
	}

	@Override
	public int[] getAllWinnerArray() {
		return winnerCodeArray;
	}

	@Override
	public int[][] getWinnerCodesToShow() {
		return latestWinnderCodes;
	}

	@Override
	public int[] getWinnerCodesArrayToShow() {
		return ArrayUtils.subarray(winnerCodeArray, winnerCodeArray.length
				- showWinnerRowNumber, winnerCodeArray.length - 1);
	}

	@Deprecated
	public void appendToWinnerSourceFile(int value) {
		System.out.println(CRLF);
		return;
	}

}
