package com.lonsec.nm.common;

import org.junit.Assert;
import org.junit.Test;

public class ConstantsTest {

	@Test
	public void test() {
		Assert.assertEquals(",", Constants.CSV_DELIMITER);
		Assert.assertEquals("excessGreaterThan", Constants.EXCESS_GREATER_THAN);
		Assert.assertEquals("excessLessThan", Constants.EXCESS_LESS_THAN);
	}
}
