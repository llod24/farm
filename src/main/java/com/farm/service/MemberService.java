package com.farm.service;

import org.springframework.beans.factory.annotation.Autowired;

import com.farm.domain.Member;
import com.farm.repository.MemberRepository;

public class MemberService {
	
	@Autowired
	private MemberRepository memberRepository;
	
	public void addMember(Member member) {
		memberRepository.addMember(member);
		
	}
}
