package com.farm.config;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import com.farm.controller.FarmController;
import com.farm.repository.FarmRepository;
import com.farm.service.FarmService;

@Configuration
@Import(DbConfig.class)
public class ControllerConfig {
	
	@Autowired
	private FarmRepository farmRepository;
	
	@Bean
	public FarmService farmService() {
		return new FarmService();
	}
	
	@Bean
	public FarmController farmController() {
		return new FarmController();
	}
	
	
}
