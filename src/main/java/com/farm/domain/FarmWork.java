package com.farm.domain;

public class FarmWork {
	
	String cropName; 
	String workload; 
	String date;
	String username;
	
	public String getCropName() {
		return cropName;
	}
	
	public String getWorkload() {
		return workload;
	}
	
	public String getDate() {
		return date;
	}
	
	public String getUsername() {
		return username;
	}

	public FarmWork(String cropName, String workload, String date, String username) {
		super();
		this.cropName = cropName;
		this.workload = workload;
		this.date = date;
		this.username = username;
	}
	
	
	
}
