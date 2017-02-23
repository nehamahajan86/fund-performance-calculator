package com.lonsec.nm.io;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

/**
 * This class reads the file and returns the input as a List.
 * 
 * @author Neha Mahajan
 *
 */
public class InputReader {

	private Logger logger = Logger.getLogger(this.getClass().getName());

	public List<String> getAllInputLines(String inputFilePath) {
		List<String> inputList = new ArrayList<>();

		try (BufferedReader reader = new BufferedReader(new FileReader(inputFilePath))) {
			inputList = reader.lines().skip(1).collect(Collectors.toList());
		} catch (IOException ioException) {
			logger.severe("Error parsing input file" + inputFilePath);
		}
		return inputList;
	}
}
