package com.billings.jdbc.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.billings.jdbc.dto.Report;
import com.billings.jdbc.sql.GenerateReportsSQL;
import com.billings.utils.SQLStatementUtils;

@SuppressWarnings("unchecked")
public class GenerateReportsImpl implements GenerateReportsDAO {

	@Override
	public List<Report> getReports() {
		String query = GenerateReportsSQL.getReports;
		
		return (List<Report>)SQLStatementUtils.executeQuery(
				query, Report.class);
	}

	@Override
	public List<Map<String, Object>> generateReport(String query) {
		return (List<Map<String, Object>>)SQLStatementUtils.executeQuery(query, HashMap.class);
	}
	
}
