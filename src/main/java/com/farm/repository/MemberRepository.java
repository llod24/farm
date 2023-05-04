package com.farm.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;

import org.apache.tomcat.jdbc.pool.DataSource;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.farm.domain.Member;
import com.farm.domain.MemberDetails;

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
	
	public MemberDetails findByEmail(String email) {
		String sql = "select * from member where email = ?";
        RowMapper<Member> rowMapper = new BeanPropertyRowMapper<>(Member.class);
		try {
			Member newMember = template.queryForObject(sql, new Object[]{email}, rowMapper);
	        return new MemberDetails(newMember);
		}catch(Exception e) {
			//TODO 에러처리하기
			return  new MemberDetails(new Member("error", "error"));
			
		}
	}
	
	public int getWorkload(String date) {
		String sql = "select sum(workload) from work where workDate = ?";
		try {
	        int workload = template.queryForObject(sql, Integer.class, date);
	        return workload;
	    } catch (Exception e) {
	        return 0;
	    }
	}
}
