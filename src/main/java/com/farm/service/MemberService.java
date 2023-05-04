package com.farm.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;

import com.farm.domain.Member;
import com.farm.domain.MemberDetails;
import com.farm.repository.MemberRepository;

public class MemberService{

	@Autowired
	private MemberRepository memberRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;
	
	public void addMember(Member member) {
		member.setPassword(passwordEncoder.encode(member.getPassword()));
		memberRepository.addMember(member);
	}
	
	
}
