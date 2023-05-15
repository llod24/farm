package com.farm.domain;

import java.sql.Date;

public class ConvertedFarmWork {
	
	String cropName;     
	int workload; 
	Date date;
	Long id;
	
	public String getCropName() {
		return cropName;
	}
	
	public int getWorkload() {
		return workload;
	}
	
	public Date getDate() {
		return date;
	}
	
	public Long getId() {
		return id;
	}
	
	public ConvertedFarmWork(String cropName, int workload, Date date, Long id) {
		super();
		this.cropName = cropName;
		this.workload = workload;
		this.date = date;
		this.id = id;
	}
	
	
	
}
