package com.lonsec.nm.Helper;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import com.lonsec.nm.model.AssetReturn;

/**
 * This is a helper class that contains the utility functions.
 * @author Neha Mahajan
 *
 */
public class Helper {

	private static Logger logger = Logger.getLogger(Helper.class.getName());

	/**
	 * This method converts the asset return list to map.
	 * @param benchReturnList
	 * @return
	 */
	public static Map<Date, List<AssetReturn>> convertToMap(List<AssetReturn> benchReturnList) {

		Map<Date, List<AssetReturn>> benchReturnMap = new HashMap<>();

		for (AssetReturn assetReturn : benchReturnList) {

			Date date = assetReturn.getBenchmarkDate();

			List<AssetReturn> assetReturnList = benchReturnMap.get(date);

			if (assetReturnList == null) {
				assetReturnList = new ArrayList<>();
			}
			assetReturnList.add(assetReturn);
			benchReturnMap.put(date, assetReturnList);
		}
		return benchReturnMap;
	}

	/**
	 * This method rounds off the double values to 2 decimal places.
	 * @param value
	 * @return
	 */
	public static Double roundDouble(Double value) {
		BigDecimal bd = new BigDecimal(value);
		return bd.setScale(2, BigDecimal.ROUND_HALF_DOWN).doubleValue();
	}

	/**
	 * This method converts the date to a string in the particular date format.
	 * @param date
	 * @param format
	 * @return
	 */
	public static String dateToString(Date date, String format) {
		DateFormat dateFormat = new SimpleDateFormat(format);
		return dateFormat.format(date);
	}

	/**
	 * This method converts the date to a string in a particular format.
	 * @param strDate
	 * @param format
	 * @return
	 */
	public static Date stringToDate(String strDate, String format) 
	{
		Date date = null;
		try {
			date = new SimpleDateFormat(format).parse(strDate);
		} catch (ParseException e) {
			logger.severe("Error formatting date " + strDate + " in " + format + " format.");
		}
		return date;
	}
	
	
	/**
	 * Checks whether the particular string is a double number.
	 * @param str
	 * @return
	 */
	public static boolean isNumber(String str) {
		try {
			Double.parseDouble(str);
		} catch (NumberFormatException nfe) {
			return false;
		}
		return true;
	}
}