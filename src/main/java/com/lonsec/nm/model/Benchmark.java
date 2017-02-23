package com.lonsec.nm.model;

/**
 * This is a pojo class that reflects the state of a benchmark.
 * @author Neha Mahajan
 *
 */
public class Benchmark {

	private String code;
	private String name;

	public Benchmark getBenchmark() {
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
}
