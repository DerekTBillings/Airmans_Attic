package com.billings.resources;

import java.util.List;
import java.util.Map;

public class ReportResultsResources {
	
	private static List<Map<String, Object>> report;

	public static List<Map<String, Object>> getReport() {
		return report;
	}

	public static void setReport(List<Map<String, Object>> results) {
		report = results;
	}
	
}
