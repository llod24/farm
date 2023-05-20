package com.farm.domain;

public class ChartData {
	
	String date;
	int workload;
	
	public String getDate() {
		return date;
	}
	public int getWorkload() {
		return workload;
	}
	
	public ChartData(String date, int workload) {
		super();
		this.date = date;
		this.workload = workload;
	}
	
	
	
	
}
