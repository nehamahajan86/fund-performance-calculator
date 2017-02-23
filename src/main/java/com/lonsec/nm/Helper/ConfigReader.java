package com.lonsec.nm.Helper;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.logging.Logger;

import com.lonsec.nm.common.Constants;

/**
 * This is a singleton class that reads the static configuration from the
 * configuration file. If configuration value is not defined then it initializes
 * the configuration with default values.
 * 
 * @author Neha Mahajan
 *
 */
public class ConfigReader {

	private Logger LOGGER = Logger.getLogger(ConfigReader.class.getName());

	private Properties properties = new Properties();
	private Map<String, String> propMap = new HashMap<String, String>();
	private static ConfigReader instance = new ConfigReader();

	private final String CONFIG_FILE_NAME = "config/fund-performance-calculator.properties";

	private ConfigReader() {
		loadConfig();
	}

	/**
	 * This method returns the instance of ConfigReader class.
	 * 
	 * @return
	 */

	public static ConfigReader getInstance() {
		return instance;
	}

	/**
	 * This method returns the configuration value for a certain property.
	 * 
	 * @param configName
	 *            : Name of the parameter
	 * @return : Value of the parameter.
	 */
	public String getConfigValue(String configName) {
		return propMap.get(configName);
	}

	private void loadConfig() {

		InputStream inputStream = null;

		try {
			/*populate the default configuration.*/
			populateDefaultConfig();
			
			inputStream = new FileInputStream(CONFIG_FILE_NAME);

			/*
			 * If configuration file is present then update the configuration
			 * from the same.
			 */
			if (inputStream != null) {
				properties.load(inputStream);
				populateConfig();
			}

		} catch (IOException ex) {
			LOGGER.severe("An error occurred while reading the configuration");
		} finally {
			if (inputStream != null) {
				try {
					inputStream.close();
				} catch (IOException e) {
					LOGGER.severe("An error occurred while closing the input stream.");
					LOGGER.severe(e.getMessage());
				}
			}
		}
	}

	private void populateDefaultConfig() {
		propMap.put(Constants.PERFORMANCE_OUTPUT_FILE_LOCATION, "fundPerformanceByMonth.csv");
		propMap.put(Constants.EXCESS_LESS_THAN, "-1");
		propMap.put(Constants.EXCESS_GREATER_THAN, "1");
		propMap.put(Constants.EXCESS_UNDER_PERFORM_TEXT, "under performed");
		propMap.put(Constants.EXCESS_OUT_PERFORM_TEXT, "out performed");
	}

	private void populateConfig() {

		String propValue = null;
		propValue = properties.getProperty(Constants.FUND_CSV);
		if (propValue != null) {
			propMap.put(Constants.FUND_CSV, propValue);
		}

		propValue = properties.getProperty(Constants.BENCH_CSV);

		if (propValue != null) {
			propMap.put(Constants.BENCH_CSV, propValue);
		}

		propValue = properties.getProperty(Constants.FUND_RETURN_CSV);

		if (propValue != null) {
			propMap.put(Constants.FUND_RETURN_CSV, propValue);
		}

		propValue = properties.getProperty(Constants.BENCH_RETURN_CSV);

		if (propValue != null) {
			propMap.put(Constants.BENCH_RETURN_CSV, propValue);
		}

		propValue = properties.getProperty(Constants.PERFORMANCE_OUTPUT_FILE_LOCATION);

		if (propValue != null) {
			propMap.put(Constants.PERFORMANCE_OUTPUT_FILE_LOCATION, propValue);
		}

		propValue = properties.getProperty(Constants.EXCESS_LESS_THAN);

		if (propValue != null && Helper.isNumber(propValue)) {
			propMap.put(Constants.EXCESS_LESS_THAN, propValue);
		}

		propValue = properties.getProperty(Constants.EXCESS_GREATER_THAN);

		if (propValue != null && Helper.isNumber(propValue)) {
			propMap.put(Constants.EXCESS_GREATER_THAN, propValue);
		}

		propValue = properties.getProperty(Constants.EXCESS_UNDER_PERFORM_TEXT);

		if (propValue != null) {
			propMap.put(Constants.EXCESS_UNDER_PERFORM_TEXT, propValue);
		}

		propValue = properties.getProperty(Constants.EXCESS_OUT_PERFORM_TEXT);

		if (propValue != null) {
			propMap.put(Constants.EXCESS_OUT_PERFORM_TEXT, propValue);
		}

	}

}
