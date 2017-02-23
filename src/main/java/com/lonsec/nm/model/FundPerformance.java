package com.lonsec.nm.model;

import java.util.Date;

import com.lonsec.nm.Helper.Helper;
import com.lonsec.nm.common.Constants;

/**
 * This is a pojo class that reflects the state of a fund performance.
 * @author Neha Mahajan
 *
 */
public class FundPerformance {

	private Date date;
	private double excess;
	private double returnPercentage;
	private String fundName;
	private String outPerformance;
	private int rank;

	public String getFundName() {
		return fundName;
	}

	public void setFundName(String fundName) {
		this.fundName = fundName;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public double getExcess() {
		return excess;
	}

	public void setExcess(double d) {
		this.excess = d;
	}

	public String getOutPerformance() {
		return outPerformance;
	}

	public void setOutPerformance(String outPerformance) {
		this.outPerformance = outPerformance;
	}

	public double getReturnPercentage() {
		return returnPercentage;
	}

	public void setReturnPercentage(double d) {
		this.returnPercentage = d;
	}

	public int getRank() {
		return rank;
	}

	public void setRank(int rank) {
		this.rank = rank;
	}

	@Override
	public String toString() {
		return fundName + ", " + Helper.dateToString(date, Constants.FUND_SERIES_DATE_FORMAT) + ", " + excess + ", "
				+ outPerformance + ", " + returnPercentage + ", " + rank;
	}

}
