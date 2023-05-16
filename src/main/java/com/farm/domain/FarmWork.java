package com.farm.domain;

import java.sql.Timestamp;

public class FarmWork {
	
	String cropName; 
	String workload; 
	String date;
	String stringWorkId;

	Long workId; // 데이터 삽입시 auto inc 되는 id 저장용
	Long memberId; // member의 id 저장용
	String username;
	Timestamp updated_at;

	public String getCropName() {
		return cropName;
	}

	public String getWorkload() {
		return workload;
	}

	public String getDate() {
		return date;
	}

	public String getStringWorkId() {
		return stringWorkId;
	}
	
	public Long getWorkId() {
		return workId;
	}

	public Long getMemberId() {
		return memberId;
	}

	public String getUsername() {
		return username;
	}

	public Timestamp getUpdated_at() {
		return updated_at;
	}

	//데이터 삽입시 사용되는 생성자
	public FarmWork(String cropName, String workload, String date, Long memberId) {
		super();
		this.cropName = cropName;
		this.workload = workload;
		this.date = date;
		this.memberId = memberId;
	}

	//데이터 조회시 사용되는 생성자
	public FarmWork(Long workId, String cropName, String workload, String date, String username, Timestamp updated_at) {
		super();
		this.workId = workId;
		this.cropName = cropName;
		this.workload = workload;
		this.date = date;
		this.username = username;
		this.updated_at = updated_at;
	}
	
	//데이터 업데이트시 사용되는 생성자
	public FarmWork(String stringWorkId, String cropName, String workload, String date) {
		super();
		this.stringWorkId = stringWorkId;
		this.cropName = cropName;
		this.workload = workload;
		this.date = date;
	}
	
	
}
