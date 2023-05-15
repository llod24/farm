package com.farm.domain;

import java.sql.Timestamp;

public class FarmWork {
	
	String cropName; 
	String workload; 
	String date;
	Long id;
	String username;
	Timestamp updated_at;
	
	
	public Timestamp getUpdated_at() {
		return updated_at;
	}

	public String getCropName() {
		return cropName;
	}
	
	public String getWorkload() {
		return workload;
	}
	
	public String getDate() {
		return date;
	}
	
	public Long getId() {
		return id;
	}
	
	public String getUsername() {
		return username;
	}

	public FarmWork(String cropName, String workload, String date, Long id) {
		super();
		this.cropName = cropName;
		this.workload = workload;
		this.date = date;
		this.id = id;
	}

	public FarmWork(String cropName, String workload, String date, String username, Timestamp updated_at) {
		super();
		this.cropName = cropName;
		this.workload = workload;
		this.date = date;
		this.username = username;
		this.updated_at = updated_at;
	}
	

	
	
}
