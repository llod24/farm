package com.farm.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.Collections;
import java.util.List;

import org.apache.tomcat.jdbc.pool.DataSource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
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
		String sql = "SELECT m.id, m.password, m.email, r.role as role " +
                "FROM member m " +
                "JOIN user_roles ur ON m.id = ur.user_id " +
                "JOIN roles r ON ur.role_id = r.id " +
                "WHERE m.email = ?";
		List<MemberDetails> memberDetails = template.query(sql, new Object[]{email}, (rs, rowNum) ->
	       new MemberDetails(
	           rs.getLong("id"),
	           rs.getString("password"),
	           rs.getString("email"),
	           Collections.singletonList(new SimpleGrantedAuthority(rs.getString("role")))
	       )
	   );
	    if (memberDetails.isEmpty()) {
	        return null;
	    } else {
	        return memberDetails.get(0);
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
