package com.farm.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.support.SessionStatus;

import com.farm.domain.Member;
import com.farm.domain.MemberDetails;
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
	
	@Autowired
	private AuthenticationManager authenticationManager;
		
	    
	@GetMapping(value="/login")
	public String loadLogin() {		
		return "login";
	}
	
	@PostMapping("/login")
	public String processLogin(HttpServletRequest request, Model model) {
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		Long id = memberService.getIdByEmail(email);
		Authentication authentication = new UsernamePasswordAuthenticationToken(email, password);
        try {
            Authentication result = authenticationManager.authenticate(authentication);
            SecurityContextHolder.getContext().setAuthentication(result);
            HttpSession session = request.getSession();
            MemberDetails memberDetails = (MemberDetails) result.getPrincipal();
            session.setAttribute("user", result.getName());         
            session.setAttribute("authorities", memberDetails.getAuthorities());
            session.setAttribute("id", id);       
            model.addAttribute("success", "login success");
            return "main";
        } catch (AuthenticationException e) {
            model.addAttribute("error", e.toString());
            return "login";
        }
	}
	
	@PostMapping("/logout")
	public String logout(HttpServletRequest request, HttpServletResponse response, 
			Authentication authentication, SessionStatus sessionStatus) {
        new SecurityContextLogoutHandler().logout(request, response, authentication);
        sessionStatus.setComplete();
        return "main";
    }
	
	@GetMapping(value="/manage")
	public String getMembers(Model model) {
	    List<Member> memberList = memberService.getAllMembers();
	    model.addAttribute("members", memberList);
	    return "manage";
	}
	
	@GetMapping("/manage/{memberId}")
	@ResponseBody
	public Member getMemberById(@PathVariable Long memberId) {
	    Member member = memberService.getMemberById(memberId);
	    return member;
	}
	
	@PostMapping("/manage")
	public String editRole(HttpServletRequest request) {
		String role = request.getParameter("role");
		//String originalRole = request.getParameter("originalRole");
		String stringId = request.getParameter("memberId");
		Long memberId = Long.parseLong(stringId);
		memberService.updateRole(memberId, role);
        return "redirect:/manage";
    }
	
	
	
}
