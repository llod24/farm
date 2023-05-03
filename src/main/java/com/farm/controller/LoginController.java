package com.farm.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.farm.domain.Member;
import com.farm.service.LoginService;

@Controller
public class LoginController {
	
	@Autowired
	private LoginService loginService;
	
	
	@GetMapping(value="/login")
	public String loadLogin() {		
		return "login";
	}
	
	@PostMapping("/login")
	public String processLogin(HttpServletRequest request) {
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		Member member = new Member(email, password);
		if(loginService.processLogin(member))
			return "success";
		else
			return "login";
	}
	
}
