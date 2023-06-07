package com.farm.config;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import com.farm.controller.FarmController;
import com.farm.controller.MemberController;
import com.farm.repository.FarmRepository;
import com.farm.repository.MemberRepository;
import com.farm.service.FarmService;
import com.farm.service.MemberService;
import com.farm.service.MyUserDetailsService;

@Configuration
@Import(DbConfig.class)
public class ControllerConfig {
	
	@Autowired
	private FarmRepository farmRepository;

	@Autowired
	private MemberRepository memberRepository;
	
	@Bean
	public FarmService farmService() {
		return new FarmService();
	}
	
	@Bean
	public FarmController farmController() {
		return new FarmController();
	}
	
	@Bean
	public MemberService memberService() {
		return new MemberService();
	}
	
	@Bean
	public MemberController memberController() {
		return new MemberController();
	}
	
	
	
	
	
	
}
