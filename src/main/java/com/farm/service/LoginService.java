package com.farm.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.farm.domain.Member;
import com.farm.repository.MemberRepository;

public class LoginService {

	@Autowired
	private MemberRepository memberRepository;
	
	public boolean processLogin(Member member) {
		Member newMember = memberRepository.IsRight(member);
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		if(encoder.matches(newMember.getPassword(), member.getPassword())) {
			return true;
		}
		return false;
	}
}
