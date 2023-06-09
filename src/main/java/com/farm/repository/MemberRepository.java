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
	
	public Long addMember(Member member) {
		template.update((Connection con) -> {
			PreparedStatement pstmt = con.prepareStatement(
					"INSERT INTO member (username, password, email) VALUES (?,?,?)");
			pstmt.setString(1, member.getUsername());
            pstmt.setString(2, member.getPassword());
            pstmt.setString(3, member.getEmail());
			return pstmt;
		});
		String sql = "SELECT id from member WHERE email = ?";
		Long id =  template.queryForObject(sql, Long.class, member.getEmail());
		return id;
	}
	
	public Long getIdByEmail(String email) {
		String sql = "SELECT id from member WHERE email = ?";
		Long id =  template.queryForObject(sql, Long.class, email);
		return id;
	}
	
	public String getNameByEmail(String email) {
		String sql = "SELECT username from member WHERE email = ?";
		String name =  template.queryForObject(sql, String.class, email);
		return name;
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
	
	public List <Member> getAllMembers(){
		String sql = "SELECT m.id, m.username, m.email, r.role as role FROM member m " + 
				"JOIN user_roles ur ON m.id = ur.user_id " + 
				"JOIN roles r ON ur.role_id = r.id " + 
				"ORDER BY m.id ASC;";
		List<Member> member = template.query(sql, (rs, rowNum) ->
	       new Member(
	           rs.getLong("id"),
	           rs.getString("username"),
	           rs.getString("email"),
	           rs.getString("role")
	       ));
		return member;		
	}
	
	public Member getMemberById(Long memberId) {
		String sql = "SELECT m.id, m.username, m.email, COALESCE(r.role, '권한없음') as role " + 
				"FROM member m " + 
				"LEFT JOIN user_roles ur ON m.id = ur.user_id " + 
				"LEFT JOIN roles r ON ur.role_id = r.id " +
				"WHERE m.id = ?";
		List<Member> member = 
				template.query(sql, new Object[]{memberId},(rs, rowNum) ->
	       new Member(
	           rs.getLong("id"),
	           rs.getString("username"),
	           rs.getString("email"),
	           rs.getString("role")
	       ));
	    if (member.isEmpty()) {
	        return null;
	    } else {
	        return member.get(0);
	    }
	}
	
	public void addRole(Long memberId, String role) {
		String getRoleId = "select id from roles where role = ? "; 
		Long roleId =  template.queryForObject(getRoleId, Long.class, role);
		template.update((Connection con) -> {
			PreparedStatement pstmt = con.prepareStatement(
					"INSERT INTO user_roles VALUES (?, ?)");
			pstmt.setLong(1, memberId);
            pstmt.setLong(2, roleId);
			return pstmt;
		});
		
	}
	
	public void updateRole(Long memberId, String role) {
		String getRoleId = "select id from roles where role = ? "; 
		Long roleId =  template.queryForObject(getRoleId, Long.class, role);
		String sql = "UPDATE user_roles SET role_id = ? WHERE user_id = ?";
		template.update(sql, roleId, memberId);
	}
	
	public void deleteRole(Long memberId, String role) {
		String sql = "delete from user_roles where user_id = ?";
		template.update(sql, memberId);	
	}
	
	public List<String> getAllRoles(){
		String query = "SELECT role FROM roles";
        return template.queryForList(query, String.class);
	}
}
