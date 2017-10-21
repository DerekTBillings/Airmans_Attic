package com.billings.jdbc.sql;

public class AdminManagementSQL {
	
	public static final String makeAdmin = "insert into admin_accounts values(?, '1', ?, ?)";
	
	public static final String removeAdmin = "delete from admin_accounts where person_id = ?";

	public static final String isUsernameAvailable = "select case when count(*) = 0 then 'true' else 'false' end as isAvailable "+
			"from admin_accounts "+
			"where username = ?";

}
