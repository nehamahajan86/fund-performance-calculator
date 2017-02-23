package com.lonsec.nm.parser;

import java.util.function.Function;

import com.lonsec.nm.common.Constants;
import com.lonsec.nm.model.Benchmark;

/**
 * This interface contains the functionality to parse benchmarks.
 * @author Neha Mahajan
 *
 */
public interface BenchmarkParser {

	public static Function<String, Benchmark> parse = (line) -> {

		String[] b = line.split(Constants.CSV_DELIMITER);
		Benchmark bm = new Benchmark();
		bm.setCode(b[0].trim());
		bm.setName(b[1].trim());

		return bm;
	};
}
