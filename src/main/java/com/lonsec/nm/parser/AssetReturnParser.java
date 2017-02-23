package com.lonsec.nm.parser;

import java.util.function.Function;

import com.lonsec.nm.common.Constants;
import com.lonsec.nm.model.AssetReturn;

/**
 * This interface contains the parse function to parse return series.
 * @author Neha Mahajan
 *
 */
public interface AssetReturnParser {

	public static Function<String, AssetReturn> parse = (line) -> {
		
		String[] r = line.split(Constants.CSV_DELIMITER);
		AssetReturn ar = new AssetReturn();
		ar.setCode(r[0].trim());
		ar.setDate(r[1].trim());
		ar.setReturnPercentage(Double.parseDouble(r[2]));

		return ar;
	};

}
