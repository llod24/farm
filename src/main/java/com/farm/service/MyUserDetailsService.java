package com.farm.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.farm.domain.MemberDetails;
import com.farm.repository.MemberRepository;

@Component
public class MyUserDetailsService implements UserDetailsService {

	@Autowired
	private MemberRepository memberRepository;
	
	@Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        System.out.println(email);
		MemberDetails memberDetails = memberRepository.findByEmail(email);
        if (memberDetails == null) {
            throw new UsernameNotFoundException("User not found - null");
        }
        return memberDetails;
    }
}
