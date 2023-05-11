package com.farm.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.farm.domain.Member;
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
	
	public List<Member> getAllMembers(){
		return memberRepository.getAllMembers(); 
	}
	
	public Member getMemberById(Long memberId) {
		return memberRepository.getMemberById(memberId);
	}
}
