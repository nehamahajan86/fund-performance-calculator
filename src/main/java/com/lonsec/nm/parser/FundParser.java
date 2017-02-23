package com.lonsec.nm.parser;

import java.util.function.Function;

import com.lonsec.nm.common.Constants;
import com.lonsec.nm.model.Fund;

/**
 * This interface contains the method to parse funds.
 * @author Neha Mahajan
 *
 */
public interface FundParser {

	public static Function<String, Fund> parse = (line) -> {

		String[] p = line.split(Constants.CSV_DELIMITER);
		Fund fund = new Fund();
		fund.setCode(p[0].trim());
		fund.setName(p[1].trim());
		fund.setBenchmarkCode(p[2].trim());
		return fund;
	};
}
