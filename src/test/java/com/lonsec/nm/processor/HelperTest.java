package com.lonsec.nm.processor;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.Assert;
import org.junit.Test;

import com.lonsec.nm.Helper.Helper;

public class HelperTest {

	@Test
	public void testRoundDouble() {
		Assert.assertEquals(4.77, Helper.roundDouble(4.76767), 0);
		Assert.assertEquals(4.76, Helper.roundDouble(4.763232), 0);
	}
	
	@Test
	public void testStringToDate() throws ParseException
	{
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		Date date = dateFormat.parse("30/06/2016");
		
		Assert.assertNotNull(Helper.stringToDate("30/06/2016", "dd/MM/yyyy"));
		Assert.assertEquals(date, Helper.stringToDate("30/06/2016", "dd/MM/yyyy"));
	}

	@Test
	public void testDateToString() throws ParseException
	{
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		Date date = dateFormat.parse("30/06/2016");
		
		Assert.assertEquals("30/06/2016", Helper.dateToString(date, "dd/MM/yyyy"));
	}
}
