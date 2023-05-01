package com.farm.domain;

import java.sql.Date;

public class ConvertedFarmWork {
	
	String cropName;     
	int workload; 
	Date date;
	
	public String getCropName() {
		return cropName;
	}
	
	public int getWorkload() {
		return workload;
	}
	
	public Date getDate() {
		return date;
	}
	
	public ConvertedFarmWork(String cropName, int workload, Date date) {
		super();
		this.cropName = cropName;
		this.workload = workload;
		this.date = date;
	}
	
}
