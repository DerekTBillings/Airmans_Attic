package com.billings.jdbc.sql;

public class CustomerInfoPageSetupSQL {
	
	public static final String getRankList = "SELECT Value "+
		"FROM system_values "+
		"WHERE Category = 'position'";
	
	public static final String getRequiredFieldsList = "SELECT Value "+
		"FROM system_values "+
		"WHERE Category = 'required field'";
	
}
