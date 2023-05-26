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
		Long id = memberRepository.addMember(member);
		//임시권한 부여
		memberRepository.addRole(id, "temp");
	}
	
	public List<Member> getAllMembers(){
		return memberRepository.getAllMembers(); 
	}
	
	public Member getMemberById(Long memberId) {
		return memberRepository.getMemberById(memberId);
	}
	
	public void addRole(Long memberId, String role) {
		memberRepository.addRole(memberId, role);
	}
	
	public void updateRole(Long memberId, String role) {
		memberRepository.updateRole(memberId, role);
	}
	
	public void deleteRole(Long memberId, String role) {
		memberRepository.deleteRole(memberId, role);
	}

	public Long getIdByEmail(String email) {
		return memberRepository.getIdByEmail(email);
	}

	public String getNameByEmail(String email) {
		return memberRepository.getNameByEmail(email);
	}
}
