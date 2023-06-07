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
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        
        Member member = new Member(username, email, password);
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
	public String processLogin(@RequestParam("email") String email, @RequestParam("password") String password, 
			HttpServletRequest request, Model model) {
		System.out.println(email);
		Long id = memberService.getIdByEmail(email);
		String name = memberService.getNameByEmail(email);
		Authentication authentication = new UsernamePasswordAuthenticationToken(email, password);
        try {
            Authentication result = authenticationManager.authenticate(authentication);
            SecurityContextHolder.getContext().setAuthentication(result);
            HttpSession session = request.getSession();
            MemberDetails memberDetails = (MemberDetails) result.getPrincipal();
            session.setAttribute("user", result.getName());         
            session.setAttribute("authorities", memberDetails.getAuthorities());
            session.setAttribute("id", id);
            session.setAttribute("name", name);
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
//        new SecurityContextLogoutHandler().logout(request, response, authentication);
//        sessionStatus.setComplete();
        return "main";
    }
	
	@GetMapping(value="/manage")
	public String getMembers(Model model) {
	    List<Member> memberList = memberService.getAllMembers();
	    String optionList = memberService.getAllRoles();
	    model.addAttribute("members", memberList);
	    model.addAttribute("options", optionList);
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
	
	@GetMapping(value="/accessDenied")
	public String getMembers() {
	    return "accessDenied";
	}
	
}
