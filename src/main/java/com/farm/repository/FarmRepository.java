package com.farm.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.List;

import org.apache.tomcat.jdbc.pool.DataSource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.farm.domain.ConvertedFarmWork;
import com.farm.domain.FarmWork;
import com.farm.domain.Member;

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
						"INSERT INTO work (workerID, workDate, cropName, workload) VALUES (?,?, ?, ?)");
				pstmt.setString(1, "testID");
				pstmt.setDate(2, work.getDate());
	            pstmt.setString(3, work.getCropName());
	            pstmt.setInt(4, work.getWorkload());
				return pstmt;
			});
		}
		
	}

	public List<FarmWork> getDailyFarmWork(String date) {
		String sql = "select workDate, cropName, workload from work where workDate = ?";
		List<FarmWork> dailyFarmWork = template.query(sql, new Object[]{date}, (rs, rowNum) ->
	       new FarmWork(
		           rs.getString("cropName"),
		           rs.getString("workload"),
	           rs.getString("workDate")
	       ));
		return dailyFarmWork;	
	}
	
}
