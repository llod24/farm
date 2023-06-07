package com.farm.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.farm.service.MyUserDetailsService;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter  {
	
	 @Override
	    protected void configure(HttpSecurity http) throws Exception {
	        http
	        	.csrf()
	        		.disable()
	            .authorizeRequests()
	            	.antMatchers("/", "/login", "/register", "/main").permitAll()
	            	.antMatchers("/add").hasAnyRole("ADMIN", "WORKER")
	                .antMatchers("/manage").hasRole("ADMIN")
	                .anyRequest().authenticated()
	                .and()
	            .logout()
	                .logoutUrl("/logout")
	                .logoutSuccessUrl("/")
	        		.and()
	        	.exceptionHandling()
	            	.accessDeniedPage("/accessDenied");
	    }
	        
	@Bean
	public MyUserDetailsService myUserDetailsService() {
		return new MyUserDetailsService();
	}
    	
	@Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(myUserDetailsService());
    }
	
    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }   
    
   
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}