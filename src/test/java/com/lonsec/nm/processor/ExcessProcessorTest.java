package com.lonsec.nm.processor;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.lonsec.nm.Helper.Helper;
import com.lonsec.nm.model.AssetReturn;
import com.lonsec.nm.model.Fund;
import com.lonsec.nm.model.FundPerformance;
import com.lonsec.nm.processor.ExcessProcessor;

public class ExcessProcessorTest {

	private List<AssetReturn> returnOnFundList;
	private Map<String, Fund> fundMap;
	private Map<Date, List<AssetReturn>> returnOnBenchmarkMap;

	@Before
	public void setUp() throws Exception {
		returnOnFundList = new ArrayList<>();
		fundMap = new HashMap<>();
		returnOnBenchmarkMap = new HashMap<>();
	}

	@After
	public void tearDown() throws Exception {
		returnOnFundList = null;
		fundMap = null;
		returnOnBenchmarkMap = null;
	}

	@Test
	public void testProcess() 
	{
		AssetReturn returnOnFund = new AssetReturn();
		returnOnFund.setCode("Fund 1");
		returnOnFund.setDate("30/09/2016");
		returnOnFund.setReturnPercentage(2.0);
		returnOnFundList.add(returnOnFund);
		
		AssetReturn returnOnBenchmark = new AssetReturn();
		returnOnBenchmark.setCode("Bm 1");
		returnOnBenchmark.setDate("30-09-2016");
		returnOnBenchmark.setReturnPercentage(1.0);
		
		List<AssetReturn> returnOnBenchmarkList = new ArrayList<>();
		returnOnBenchmarkList.add(returnOnBenchmark);
		returnOnBenchmarkMap.put(Helper.stringToDate("30/09/2016", "dd/MM/yyyy"), returnOnBenchmarkList);
		
		Fund fund = new Fund();
		fund.setBenchmarkCode("Bm 1");
		fund.setCode("Fund 1");
		fund.setName("Test Fund 1");
		fundMap.put(fund.getCode(), fund);
		
		ExcessProcessor excessProcessor = new ExcessProcessor(returnOnBenchmarkMap, returnOnFundList, fundMap);
		List<FundPerformance> fundPerformanceList = excessProcessor.process();
		
		FundPerformance fundPerformance = fundPerformanceList.get(0);
		
		Assert.assertEquals(1, fundPerformanceList.size());
		Assert.assertEquals("Test Fund 1", fundPerformance.getFundName());
		Assert.assertEquals(2.0, fundPerformance.getReturnPercentage(), 0);
		Assert.assertEquals(1.0, fundPerformance.getExcess(), 0);
	}
	
	@Test
	public void shouldNotProcessWhenNoFundInfoIsPresent()
	{
		AssetReturn returnOnFund = new AssetReturn();
		returnOnFund.setCode("Fund 1");
		returnOnFund.setDate("30/09/2016");
		returnOnFund.setReturnPercentage(2.0);
		returnOnFundList.add(returnOnFund);
		
		AssetReturn returnOnBenchmark = new AssetReturn();
		returnOnBenchmark.setCode("Bm 1");
		returnOnBenchmark.setDate("30-09-2016");
		returnOnBenchmark.setReturnPercentage(1.0);
		
		List<AssetReturn> returnOnBenchmarkList = new ArrayList<>();
		returnOnBenchmarkList.add(returnOnBenchmark);
		returnOnBenchmarkMap.put(Helper.stringToDate("30/09/2016", "dd/MM/yyyy"), returnOnBenchmarkList);
		
		ExcessProcessor excessProcessor = new ExcessProcessor(returnOnBenchmarkMap, returnOnFundList, fundMap);
		List<FundPerformance> fundPerformanceList = excessProcessor.process();
		
		Assert.assertEquals(0, fundPerformanceList.size());
	}
	
	@Test
	public void shouldNotProcessWhenNoBenchmarkMappingIsFound()
	{
		AssetReturn returnOnFund = new AssetReturn();
		returnOnFund.setCode("Fund 1");
		returnOnFund.setDate("30/09/2016");
		returnOnFund.setReturnPercentage(2.0);
		returnOnFundList.add(returnOnFund);
		
		Fund fund = new Fund();
		fund.setBenchmarkCode("Bm 1");
		fund.setCode("Fund 1");
		fund.setName("Test Fund 1");
		fundMap.put(fund.getCode(), fund);
		
		ExcessProcessor excessProcessor = new ExcessProcessor(returnOnBenchmarkMap, returnOnFundList, fundMap);
		List<FundPerformance> fundPerformanceList = excessProcessor.process();
		
		Assert.assertEquals(0, fundPerformanceList.size());
	}
}
