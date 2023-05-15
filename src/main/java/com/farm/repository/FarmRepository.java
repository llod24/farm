package com.farm.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.List;

import org.apache.tomcat.jdbc.pool.DataSource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.farm.domain.ConvertedFarmWork;
import com.farm.domain.FarmWork;

@Repository
public class FarmRepository {
	
	private JdbcTemplate template;
	
	public FarmRepository(DataSource dataSource) {
		this.template = new JdbcTemplate(dataSource);
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
	
	public void addWorks(List<ConvertedFarmWork> works) {
		
		for (ConvertedFarmWork work : works) {
			template.update((Connection con) -> {
				PreparedStatement pstmt = con.prepareStatement(
						"INSERT INTO work (workDate, cropName, workload, id) VALUES (?, ?, ?, ?)");
				pstmt.setDate(1, work.getDate());
	            pstmt.setString(2, work.getCropName());
	            pstmt.setInt(3, work.getWorkload());
	            pstmt.setLong(4, work.getId());
				return pstmt;
			});
		}
		
	}

	public List<FarmWork> getDailyFarmWork(String date) {
		String sql = "SELECT work.workDate, work.cropName, work.workload, member.username, work.updated_at " + 
				"FROM work " + 
				"JOIN member ON work.id = member.id where workDate = ?";
		List<FarmWork> dailyFarmWork = template.query(sql, new Object[]{date}, (rs, rowNum) ->
	       new FarmWork(
		       rs.getString("cropName"),
		       rs.getString("workload"),
	           rs.getString("workDate"),
	           rs.getString("username"),
	           rs.getTimestamp("updated_at")
	       ));
		return dailyFarmWork;	
	}

	public String getNameById(Long id) {
		String sql = "SELECT username from member WHERE id = ?";
		String username =  template.queryForObject(sql, String.class, id);
		return username;
	}
	
}
