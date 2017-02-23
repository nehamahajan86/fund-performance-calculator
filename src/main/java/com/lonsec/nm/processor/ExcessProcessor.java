package com.lonsec.nm.processor;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.lonsec.nm.Helper.ConfigReader;
import com.lonsec.nm.Helper.Helper;
import com.lonsec.nm.common.Constants;
import com.lonsec.nm.model.AssetReturn;
import com.lonsec.nm.model.Fund;
import com.lonsec.nm.model.FundPerformance;

/**
 * This class contains the logic to calculate excess.
 * @author Neha Mahajan
 *
 */
public class ExcessProcessor {

	private List<AssetReturn> returnOnFundList;
	private Map<String, Fund> fundMap;
	private Map<Date, List<AssetReturn>> returnOnBenchmarkMap;

	public ExcessProcessor(Map<Date, List<AssetReturn>> returnOnBenchmarkMap, List<AssetReturn> returnOnFundList,
			Map<String, Fund> fundMap) {
		super();
		this.returnOnBenchmarkMap = returnOnBenchmarkMap;
		this.returnOnFundList = returnOnFundList;
		this.fundMap = fundMap;
	}

	/**
	 * This method calculates the fund performance for every element in fund return series.
	 * @return
	 */
	public List<FundPerformance> process() {
		List<FundPerformance> fundPerformanceList = new ArrayList<>();
		FundPerformance fundPerformance;
		int rank = 0;
		Date prevBenchDate = null;

		for (AssetReturn returnOnFund : returnOnFundList) {
			Date date = returnOnFund.getFundDate();
			Fund fund = fundMap.get(returnOnFund.getCode());

			if (fund != null) {
				AssetReturn returnOnBenchmark = getBenchmarkReturn(date, fund.getBenchmarkCode());

				if (returnOnBenchmark != null) {

					if (null == prevBenchDate || !returnOnBenchmark.getBenchmarkDate().equals(prevBenchDate)) {
						rank = 1;
						prevBenchDate = returnOnBenchmark.getBenchmarkDate();
					}

					double excess = Helper
							.roundDouble(returnOnFund.getReturnPercentage() - returnOnBenchmark.getReturnPercentage());

					fundPerformance = new FundPerformance();
					fundPerformance.setFundName(fund.getName());
					fundPerformance.setDate(date);
					fundPerformance.setExcess(excess);
					fundPerformance.setReturnPercentage(Helper.roundDouble(returnOnFund.getReturnPercentage()));
					fundPerformance.setOutPerformance(calculateOutPerformance(fundPerformance.getExcess()));
					fundPerformance.setRank(rank);
					rank++;

					fundPerformanceList.add(fundPerformance);
				}
			}
		}

		return fundPerformanceList;
	}

	private AssetReturn getBenchmarkReturn(Date date, String benchmarkCode) {
		AssetReturn benchmarkReturn = null;
		List<AssetReturn> benchReturnList = returnOnBenchmarkMap.get(date);

		if (benchReturnList != null) {
			for (AssetReturn assetReturn : benchReturnList) {
				if (assetReturn.getCode().equalsIgnoreCase(benchmarkCode)) {
					benchmarkReturn = assetReturn;
					break;
				}
			}
		}
		return benchmarkReturn;
	}

	private String calculateOutPerformance(double excess) {
		String performance;

		if (excess < Double.parseDouble(ConfigReader.getInstance().getConfigValue(Constants.EXCESS_LESS_THAN))) {
			performance = ConfigReader.getInstance().getConfigValue(Constants.EXCESS_UNDER_PERFORM_TEXT);

		} else if (excess > Double
				.parseDouble(ConfigReader.getInstance().getConfigValue(Constants.EXCESS_GREATER_THAN))) {
			performance = ConfigReader.getInstance().getConfigValue(Constants.EXCESS_OUT_PERFORM_TEXT);

		} else {
			performance = "";
		}

		return performance;
	}

}