package com.lonsec.nm.model;

/**
 * This is a pojo class that reflects the state of a fund.
 * @author Neha Mahajan
 *
 */
public class Fund {

	private String code;
	private String name;
	private String benchmarkCode;
	
	public Fund getFund() {
		return this;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getBenchmarkCode() {
		return benchmarkCode;
	}
	public void setBenchmarkCode(String benchmarkCode) {
		this.benchmarkCode = benchmarkCode;
	}
}
