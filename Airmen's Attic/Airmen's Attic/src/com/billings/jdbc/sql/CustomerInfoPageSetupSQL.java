package com.billings.jdbc.sql;

public class CustomerInfoPageSetupSQL {
	
	public static final String getRankList = "SELECT Value "+
		"FROM system_values "+
		"WHERE Category = 'position'";
	
	public static final String getRequiredFieldsList = "SELECT Value "+
		"FROM system_values "+
		"WHERE Category = 'required field'";
	
	public static final String getPersonById = "SELECT p.*, case when admin_level is not null then 'true' else 'false' end as admin "+
		"FROM person p "+
		"	left outer join admin_accounts a on p.person_id = a.person_id "+
		"WHERE p.Person_Id = ?";
	
}
