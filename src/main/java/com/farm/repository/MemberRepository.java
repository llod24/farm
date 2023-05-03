package com.farm.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

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
	
	public Member IsRight(Member member) {
		String sql = "select email, password from member where email = ?";
		try {
		return template.queryForObject(
				sql,
				(ResultSet rs, int rowNum) -> {
						Member newMember = new Member(
							rs.getString("email"),
							rs.getString("password"));
						return newMember;
					}, member.getEmail());
		}catch(Exception e) {
			//TODO 에러처리하기
			Member Emember = new Member("error", "error");
			return Emember;
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
