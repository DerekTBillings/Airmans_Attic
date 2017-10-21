package com.billings.jdbc.sql;

public class GenerateReportsSQL {
	
	public static String getReports = "select report_name as name, report_query as query "+
			"from reports";
	
}
