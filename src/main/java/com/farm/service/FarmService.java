package com.farm.service;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.farm.domain.ChartData;
import com.farm.domain.ChartDataList;
import com.farm.domain.ConvertedFarmWork;
import com.farm.domain.FarmWork;
import com.farm.repository.FarmRepository;
import com.fasterxml.jackson.databind.ObjectMapper;

public class FarmService {
	
	@Autowired
	private FarmRepository farmRepository;
	
	public int getWorkload(String date) {
		return farmRepository.getWorkload(date);
	}
	
	public void addWorks(List<FarmWork> works) {
		// db에 넣을 수 있는 자료형으로 변환
		List<ConvertedFarmWork> cWorks = new ArrayList<>();
		
		for (FarmWork work : works) {
			ConvertedFarmWork cfw = new ConvertedFarmWork(work.getCropName(), 
							Integer.parseInt(work.getWorkload()),
							Date.valueOf(work.getDate()),
							work.getMemberId());
			cWorks.add(cfw);
		}
		farmRepository.addWorks(cWorks);
		
	}

	public List<FarmWork> getDailyFarmWork(String date) {
		return farmRepository.getDailyFarmWork(date);
	}
	
	public String getNameById(Long id) {
	    String name = farmRepository.getNameById(id);
	    return name;
	}

	public FarmWork getFarmWorkByWorkId(Long workId) {
		return farmRepository.getFarmWorkByWorkId(workId);
	}

	public void updateFarmWork(FarmWork work) {
		// db에 넣을 수 있는 자료형으로 변환
		ConvertedFarmWork cfw = new ConvertedFarmWork(
				Long.parseLong(work.getStringWorkId()),
				work.getCropName(), 
				Integer.parseInt(work.getWorkload()),
				Date.valueOf(work.getDate()));
		farmRepository.updateFarmWork(cfw);
		
	}

	public void deleteFarmWork(String workId) {
		Long id = Long.parseLong(workId);
		farmRepository.deleteFarmWork(id);
	}

	public List<FarmWork> getMonthlyFarmWork(String month) {
		return farmRepository.getMonthlyFarmWork(month);
	}

	public List<String> getWorkloadData(String month, String cropName) {
		List<ChartData> chartData = farmRepository.getWorkloadData(month, cropName);
		List<String> dates = new ArrayList<>();
		List<Integer> workloads = new ArrayList<>();
		for (ChartData data : chartData) {
		    dates.add(data.getDate());
		    workloads.add(data.getWorkload());
		}
		try {
			//ChartData 리스트를 JSON으로 변환
	        ObjectMapper objectMapper = new ObjectMapper();
	        String date = objectMapper.writeValueAsString(dates);
	        String workload = objectMapper.writeValueAsString(workloads);

		    List<String> result = new ArrayList<>();
		    result.add(date);
		    result.add(workload);
		    return result;
	        
	    } catch (Exception e) {
	        e.printStackTrace();
	        return null;
	    }
		
	}
}
