package com.farm.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.transaction.annotation.Transactional;

import com.farm.domain.MemberDetails;
import com.farm.repository.MemberRepository;

public class MyUserDetailsService implements UserDetailsService {

	@Autowired
	private MemberRepository memberRepository;
	
	@Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        MemberDetails memberDetails = memberRepository.findByEmail(email);
        if (memberDetails == null) {
            throw new UsernameNotFoundException("User not found");
        }
        return memberDetails;
    }
	
	
	
	
}
