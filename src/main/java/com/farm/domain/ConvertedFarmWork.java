package com.farm.domain;

import java.sql.Date;

public class ConvertedFarmWork {
	
	Long workId;
	String cropName;     
	int workload; 
	Date date;
	Long memberId;
	
	
	
	public Long getWorkId() {
		return workId;
	}

	public String getCropName() {
		return cropName;
	}

	public int getWorkload() {
		return workload;
	}

	public Date getDate() {
		return date;
	}

	public Long getMemberId() {
		return memberId;
	}

	public ConvertedFarmWork(String cropName, int workload, Date date, Long memberId) {
		super();
		this.cropName = cropName;
		this.workload = workload;
		this.date = date;
		this.memberId = memberId;
	}

	public ConvertedFarmWork(Long workId, String cropName, int workload, Date date) {
		super();
		this.workId = workId;
		this.cropName = cropName;
		this.workload = workload;
		this.date = date;
	}
	
	
	
	
	
}
