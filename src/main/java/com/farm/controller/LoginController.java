package com.farm.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.farm.service.CustomAuthenticationProvider;
import com.farm.service.MyUserDetailsService;

@Controller
public class LoginController {
	
	@Autowired
	private MyUserDetailsService myUserDetailsService;
	

    @Autowired
    private AuthenticationManager authenticationManager;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	

    @Autowired
    private CustomAuthenticationProvider customAuthenticationProvider;
    
	@GetMapping(value="/login")
	public String loadLogin() {		
		return "login";
	}
	
	@PostMapping("/login")
	public String processLogin(HttpServletRequest request) {
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		try {
            // Authentication 객체 생성
            Authentication auth = new UsernamePasswordAuthenticationToken(email, password);
            // 커스텀 인증 처리를 위해 AuthenticationManager에 CustomAuthenticationProvider 등록
            // AuthenticationManager를 이용하여 인증 처리
            Authentication result = authenticationManager.authenticate(auth);	
            // 인증 성공시, SecurityContext에 Authentication 정보 저장
            SecurityContextHolder.getContext().setAuthentication(result);
            return "redirect:/main"; // 성공 - 메인으로
        } catch (AuthenticationException e) {
            // 인증 실패시, 에러 메시지를 뷰에 전달하여 로그인 폼 다시 보여줌
            request.setAttribute("error", email+password);
            return "login";
        }
	}
	
}
