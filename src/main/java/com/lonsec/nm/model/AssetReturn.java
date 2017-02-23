package com.lonsec.nm.model;

import java.util.Date;

import com.lonsec.nm.Helper.Helper;
import com.lonsec.nm.common.Constants;

/**
 * This is a pojo class that reflects the state of an return. 
 * @author Neha Mahajan
 *
 */
public class AssetReturn {

	private String code;
	private String date;
	private double returnPercentage;
	
	public AssetReturn getAssetReturn() {
		return this;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public double getReturnPercentage() {
		return returnPercentage;
	}
	public void setReturnPercentage(double returnPercentage) {
		this.returnPercentage = returnPercentage;
	}
	
	/*Convert the common date string to DATE using FUND series date format*/
	public Date getFundDate(){
		String dateStr = this.date;
		return Helper.stringToDate(dateStr, Constants.FUND_SERIES_DATE_FORMAT);
	}
	
	/*Convert the common date string to DATE using BENCHMARK series date format*/
	public Date getBenchmarkDate(){
		String dateStr = this.date;
		return Helper.stringToDate(dateStr, Constants.BENCHMARK_SERIES_DATE_FORMAT);
	}
}
