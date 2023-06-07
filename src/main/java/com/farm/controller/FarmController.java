package com.farm.controller;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.farm.domain.FarmWork;
import com.farm.service.FarmService;

@Controller
public class FarmController {
	
	@Autowired
	private FarmService farmService;

	@GetMapping(value="/")
	public String loadMain() {		
		return "login";
	}
	@GetMapping(value="/add")
	public String addFarmWorkRecord() {
		return "addFarmWorkRecord";
	}
	
	@PostMapping("/add")
	public String handleAddWork(HttpServletRequest request, Model model) {
	    HttpSession session = request.getSession();
	    
	    List<FarmWork> works = new ArrayList<>();
	    int inputCount = Integer.parseInt(request.getParameter("inputCount"));
	    
	    for (int i = 1; i <= inputCount; i++) {
	        String cropName = request.getParameter("cropName-" + i);
	        String workload = request.getParameter("workload-" + i);
	        String date = request.getParameter("date-" + i);
	        
	        if (cropName.isEmpty() || workload.isEmpty() || date.isEmpty()) {
	            model.addAttribute("error", "모든 필수 항목을 입력해주세요.");
	            return "addFarmWorkRecord";
	        }
	        
	        FarmWork work = new FarmWork(cropName, workload, date, (Long) session.getAttribute("id"));
	        works.add(work);
	    }
	    
	    farmService.addWorks(works);
	    return "farmWorkByDate";
	}

	@GetMapping(value="/work")
	public String showFarmWorkloadByDate(
			@RequestParam(value = "queryDate", required = false, defaultValue = "") String date,
            @RequestParam(value = "successMessage", required = false) String successMessage, Model model) {
		if (successMessage != null) {
			model.addAttribute("successMessage", successMessage);
		}
		if (date.isEmpty()) {
			LocalDate today = LocalDate.now();
			date = today.toString();
		}
		int work = farmService.getWorkload(date);
		List<FarmWork> dailyFarmWork = farmService.getDailyFarmWork(date);
		
		if (!dailyFarmWork.isEmpty()) {
			model.addAttribute("totalFarmWork", work);
            model.addAttribute("dailyFarmWorks", dailyFarmWork);
            model.addAttribute("queryDate", date);
		}
		return "farmWorkByDate";
	}
	
	@GetMapping("/work/{workId}")
	@ResponseBody
	public FarmWork getFarmWorkById(@PathVariable Long workId) {
	    FarmWork farmWork = farmService.getFarmWorkByWorkId(workId);
	    return farmWork;
	}
	
	@PostMapping("/work")
	public String updateFarmWork(
			@RequestParam("workId") String workId,
            @RequestParam("cropName") String cropName,
            @RequestParam("workload") String workload,
            @RequestParam("date") String date,
            RedirectAttributes redirect) {
        // 생성자 사용, 객체 생성
        FarmWork work = new FarmWork(workId, cropName, workload, date);
		farmService.updateFarmWork(work);
		redirect.addFlashAttribute("successMessage", "업데이트 완료");
	    
        return "redirect:/work";
    }
	
	@PostMapping("/work/delete")
	public String deleteFarmWork(
			@RequestParam("workId") String workId,
            RedirectAttributes redirect) {
		farmService.deleteFarmWork(workId);
		redirect.addFlashAttribute("successMessage", "삭제 완료");
	    
        return "redirect:/work";
    }
	
	@GetMapping(value="/work/month")
	public String showFarmWorkloadByMonth(
			@RequestParam(value = "queryMonth", required = false, defaultValue = "") String month,
            @RequestParam(value = "successMessage", required = false) String successMessage, Model model) {
		if (successMessage != null) {
			model.addAttribute("successMessage", successMessage);
		}
//		if (date.isEmpty()) {
//			LocalDate today = LocalDate.now();
//			date = today.toString();
//		}
		List<FarmWork> monthlyFarmWork = farmService.getMonthlyFarmWork(month);
		
		if (!monthlyFarmWork.isEmpty()) {
			//model.addAttribute("totalFarmWork", work);
            model.addAttribute("monthlyFarmWorks", monthlyFarmWork);
            model.addAttribute("queryMonth", month);
		}
		return "farmWorkByMonth";
	}
	
	@GetMapping(value = "/work/chart")
	public String showWorkloadChart(
			@RequestParam(value = "queryMonth", required = false, defaultValue = "") String month,
			@RequestParam(value = "cropName", required = false) String cropName,
			Model model)	{
	    List<String> result = farmService.getWorkloadData(month, cropName);
	    if (result != null) {		    
		    model.addAttribute("labels", result.get(0));
		    model.addAttribute("workloadData", result.get(1));		    
	    }
		return "workloadChart";
	}
	
	@GetMapping(value = "/main")
	public String showMain() {
		return "main";
	}
	
}
