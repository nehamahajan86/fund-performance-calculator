package com.lonsec.nm.app;

import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import com.lonsec.nm.Helper.ConfigReader;
import com.lonsec.nm.Helper.Helper;
import com.lonsec.nm.common.Constants;
import com.lonsec.nm.io.InputReader;
import com.lonsec.nm.io.OutputWriter;
import com.lonsec.nm.model.AssetReturn;
import com.lonsec.nm.model.Benchmark;
import com.lonsec.nm.model.Fund;
import com.lonsec.nm.model.FundPerformance;
import com.lonsec.nm.parser.AssetReturnParser;
import com.lonsec.nm.parser.BenchmarkParser;
import com.lonsec.nm.parser.FundParser;
import com.lonsec.nm.processor.ExcessProcessor;

/**
 * This class is an entry point to application.
 * @author Neha Mahajan
 *
 */
public class ExcessCalculatorApp {

	public static void main(String[] args) {

		ConfigReader configReader = ConfigReader.getInstance();
		Logger logger = Logger.getLogger(ExcessCalculatorApp.class.getName());

		InputReader reader = new InputReader();

		String fundCSVFileLocation = configReader.getConfigValue(Constants.FUND_CSV);
		String benchmarkCSVFileLocation = configReader.getConfigValue(Constants.BENCH_CSV);
		String fundReturnSeriesCSVLocation = configReader.getConfigValue(Constants.FUND_RETURN_CSV);
		String benchReturnSeriesCSVLocation = configReader.getConfigValue(Constants.BENCH_RETURN_CSV);
		
		if(fundCSVFileLocation == null || fundCSVFileLocation.equalsIgnoreCase("")){
			logger.severe("Please provide the location of Fund CSV.");
			System.exit(0);
		}
		
		if(benchmarkCSVFileLocation == null || benchmarkCSVFileLocation.equalsIgnoreCase("")){
			logger.severe("Please provide the location of Benchmark CSV.");
			System.exit(0);
		}
		
		if(fundReturnSeriesCSVLocation == null || fundReturnSeriesCSVLocation.equalsIgnoreCase("")){
			logger.severe("Please provide the location of Fund Return Series CSV.");
			System.exit(0);
		}
		
		if(benchReturnSeriesCSVLocation == null || benchReturnSeriesCSVLocation.equalsIgnoreCase("")){
			logger.severe("Please provide the location of Benchmark Return Series CSV.");
			System.exit(0);
		}
		
		/* Read all input files using common InputReader */
		List<String> benchList = reader.getAllInputLines(benchmarkCSVFileLocation);
		List<String> fundList = reader.getAllInputLines(fundCSVFileLocation);
		List<String> benchReturnStringList = reader.getAllInputLines(benchReturnSeriesCSVLocation);
		List<String> fundReturnStringList = reader.getAllInputLines(fundReturnSeriesCSVLocation);

		/* Benchmarks are not used in the business logic. */
		@SuppressWarnings("unused")
		Map<String, Benchmark> benchmarkMap = benchList.stream()
				.filter(bench -> bench.split(Constants.CSV_DELIMITER).length == 2).map(BenchmarkParser.parse)
				.collect(Collectors.toMap(Benchmark::getCode, Benchmark::getBenchmark));

		Map<String, Fund> fundMap = fundList.stream().filter(fund -> fund.split(Constants.CSV_DELIMITER).length == 3)
				.map(FundParser.parse).collect(Collectors.toMap(Fund::getCode, Fund::getFund));

		List<AssetReturn> benchReturnList = benchReturnStringList.stream()
				.filter(benchReturn -> benchReturn.split(Constants.CSV_DELIMITER).length == 3)
				.filter(benchReturn -> Helper.isNumber(benchReturn.split(Constants.CSV_DELIMITER)[2]))
				.map(AssetReturnParser.parse).collect(Collectors.toList());

		/*
		 * Sort the FundReturnSeries on Date (desc) and returnPercentage (desc).
		 * Helps to calculate rank in ExcessProcess.process
		 */
		List<AssetReturn> fundReturnList = fundReturnStringList.stream()
				.filter(fundReturn -> fundReturn.split(Constants.CSV_DELIMITER).length == 3)
				.filter(fundReturn -> Helper.isNumber(fundReturn.split(Constants.CSV_DELIMITER)[2]))
				.map(AssetReturnParser.parse)
				.sorted(Comparator.comparing(AssetReturn::getFundDate, Comparator.reverseOrder()).thenComparing(
						Comparator.comparing(AssetReturn::getReturnPercentage, Comparator.reverseOrder())))
				.collect(Collectors.toList());

		/*
		 * Convert benchReturnList to map with Key=Date and Value=List of
		 * benchReturn for this date
		 */
		Map<Date, List<AssetReturn>> benchReturnMap = Helper.convertToMap(benchReturnList);

		/* Process various input files to calculate Performance. */
		ExcessProcessor excessProcessor = new ExcessProcessor(benchReturnMap, fundReturnList, fundMap);
		List<FundPerformance> fundPerformanceList = excessProcessor.process();

		/*Write output to CSV file*/
		new OutputWriter().writeOutputToFile(configReader.getConfigValue(Constants.PERFORMANCE_OUTPUT_FILE_LOCATION),
				fundPerformanceList);

	}

}
