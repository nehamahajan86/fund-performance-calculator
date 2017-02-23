package com.lonsec.nm.io;

import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.logging.Logger;

import com.lonsec.nm.model.FundPerformance;

/**
 * This class writes the output to a specific file.
 * @author Neha Mahajan
 *
 */
public class OutputWriter {

	Logger logger = Logger.getLogger(this.getClass().getName());

	/**
	 * This class writes the output to file.
	 * @param fileName
	 * @param fundPerformanceList
	 */
	public void writeOutputToFile(String fileName, List<FundPerformance> fundPerformanceList) {

		try (BufferedWriter writer = Files.newBufferedWriter(Paths.get(fileName))) {
			File outputFile = new File(fileName);
			outputFile.createNewFile();

			writer.write("FundName, Date, Excess, OutPerformance, Return, Rank");
			writer.newLine();

			fundPerformanceList.stream().map(FundPerformance::toString).forEach(fundPerformance -> {
				try {
					writer.write(fundPerformance);
					writer.newLine();
				} catch (IOException ioe) {
					logger.severe("Error writing fund" + fundPerformance);
					logger.severe(ioe.getMessage());
				}
			});

		} catch (IOException ioe) {
			logger.severe("Error writing output file" + fileName);
			logger.severe(ioe.getMessage());
		}

	}
}
