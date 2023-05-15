package com.farm.service;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.farm.domain.ConvertedFarmWork;
import com.farm.domain.FarmWork;
import com.farm.repository.FarmRepository;

public class FarmService {
	
	@Autowired
	private FarmRepository farmRepository;
	
	public int getWorkload(String date) {
		return farmRepository.getWorkload(date);
	}
	
	
	// 수량 형변환, date 포맷 처리
	public void addWorks(List<FarmWork> works) {
		List<ConvertedFarmWork> cWorks = new ArrayList<>();
		
		for (FarmWork work : works) {
			ConvertedFarmWork cfw = new ConvertedFarmWork(work.getCropName(), 
							Integer.parseInt(work.getWorkload()),
							Date.valueOf(work.getDate()),
							work.getId());
			cWorks.add(cfw);
		}
		farmRepository.addWorks(cWorks);
		
	}


	public List<FarmWork> getDailyFarmWork(String date) {
		return farmRepository.getDailyFarmWork(date);
	}
	
	public String getNameById(Long id) {
	    // id 값을 이용하여 name 값을 조회하는 로직
	    String name = farmRepository.getNameById(id);
	    return name;
	}
}
