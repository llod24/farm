package com.farm.domain;

public class FarmWork {
	
	String cropName; 
	String workload; 
	String date;
	
	public String getCropName() {
		return cropName;
	}
	
	public String getWorkload() {
		return workload;
	}
	
	public String getDate() {
		return date;
	}
	
	public FarmWork(String cropName, String workload, String date) {
		super();
		this.cropName = cropName;
		this.workload = workload;
		this.date = date;
	}    
}
