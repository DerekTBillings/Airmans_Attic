package com.billings.jdbc.dao;

import java.util.List;
import java.util.Map;

import com.billings.jdbc.dto.Report;

public interface GenerateReportsDAO {
	
	public List<Report> getReports();
	
	public List<Map<String, Object>> generateReport(String query);
	
}
