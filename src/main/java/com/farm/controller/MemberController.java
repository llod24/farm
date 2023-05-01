package com.farm.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.farm.domain.Member;
import com.farm.service.MemberService;

@Controller
public class MemberController {
	
	@Autowired
	private MemberService memberService;
	
	@GetMapping(value="/register")
	public String loadRegister() {
		return "register";
	}
	
	@PostMapping("/register")
	public String handleAddMember(HttpServletRequest request) {
		
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String email = request.getParameter("email");
        
        Member member = new Member(username, password, email);
        
	    memberService.addMember(member);
	    return "main";
	}
}
