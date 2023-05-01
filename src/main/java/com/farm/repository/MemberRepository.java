package com.farm.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;

import org.apache.tomcat.jdbc.pool.DataSource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.farm.domain.Member;

@Repository
public class MemberRepository {

	private JdbcTemplate template;
	
	public MemberRepository(DataSource dataSource) {
		this.template = new JdbcTemplate(dataSource);
	}
	
	public void addMember(Member member) {
		template.update((Connection con) -> {
			PreparedStatement pstmt = con.prepareStatement(
					"INSERT INTO member (username, password, email) VALUES (?,?,?)");
			pstmt.setString(1, member.getUsername());
            pstmt.setString(2, member.getPassword());
            pstmt.setString(3, member.getEmail());
			return pstmt;
		});
		
	}
}
