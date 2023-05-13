package com.farm.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.farm.domain.FarmWork;
import com.farm.service.FarmService;

@Controller
public class FarmController {
	
	@Autowired
	private FarmService farmService;

	@GetMapping(value="/")
	public String loadMain() {		
		return "main";
	}
	
	@GetMapping(value="/work")
	public String showFarmWorkloadByDate(HttpServletRequest request, Model model) {
		if(request.getParameter("queryDate") != null) {
			String date = request.getParameter("queryDate");
			int work = farmService.getWorkload(date);
			List<FarmWork> dailyFarmWork = farmService.getDailyFarmWork(date);
			if (!dailyFarmWork.isEmpty()) {
	            model.addAttribute("totalFarmWork", work);
	            model.addAttribute("dailyFarmWorks", dailyFarmWork);
	        }
		}
		return "farmWorkload";
	}
	
	@GetMapping(value="/add")
	public String addFarmWorkRecord() {
		return "addFarmWorkRecord";
	}
	
	@PostMapping("/add")
	public String handleAddWork(HttpServletRequest request) {
	    List<FarmWork> works = new ArrayList<>();
	    // 입력 개수 받아오기
	    int inputCount = Integer.parseInt(request.getParameter("inputCount"));
	    
	    // 데이터 받아오기
	    for (int i = 1; i <= inputCount; i++) {
	        String crop = request.getParameter("crop-" + i);
	        String amount = request.getParameter("amount-" + i);
	        String date = request.getParameter("date-" + i);
	        
	        // 생성자 사용, 객체 생성
	        FarmWork work = new FarmWork(crop, amount, date);
	        works.add(work);
	    }
	    
	    farmService.addWorks(works);
	    return "main";
	}	
}
