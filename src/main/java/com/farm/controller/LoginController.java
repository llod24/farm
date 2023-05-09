package com.farm.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.farm.service.MyUserDetailsService;

@Controller
public class LoginController {
	
	@Autowired
	private MyUserDetailsService myUserDetailsService;
	

    @Autowired
    private AuthenticationManager authenticationManager;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
    
	@GetMapping(value="/login")
	public String loadLogin() {		
		return "login";
	}
	
	@PostMapping("/login")
	public String processLogin(HttpServletRequest request, Model model) {
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		Authentication authentication = new UsernamePasswordAuthenticationToken(email, password);
        try {
            Authentication result = authenticationManager.authenticate(authentication);
            SecurityContextHolder.getContext().setAuthentication(result);
            model.addAttribute("success", "login success");
            return "login";
        } catch (AuthenticationException e) {
            model.addAttribute("error", "Invalid username or password");
            return "login";
        }
	}
	
}
