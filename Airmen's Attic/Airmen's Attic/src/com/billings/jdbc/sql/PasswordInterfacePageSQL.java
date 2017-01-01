package com.billings.jdbc.sql;

public class PasswordInterfacePageSQL {

	private static final String validateUsernameAndPassword = "SELECT COUNT(*) "+
			"FROM admin_accounts a "+
			"INNER JOIN admin_levels l on a.Admin_Level = l.Admin_Level "+
			"WHERE l.Admin_Level_Name in (%s) "+
			"	AND a.Username = ? "+
			"    AND a.Password = ?";
	
	public static final String validateAdminUsernameAndPassword = String.format(
			validateUsernameAndPassword, "'Admin'");
	
	public static final String validateKeyHolderUsernameAndPassword = String.format(
			validateUsernameAndPassword, "'Admin', 'Key Holder'");
	
	private static final String updateSignInHistory = "INSERT INTO sign_in_history (Person_Id, Time_In, Time_Out, Role) "+
			"VALUES( "+
			"	(SELECT Person_Id "+
			"    FROM admin_accounts "+
			"    WHERE Username = ?), "+
			"    NOW(), Null, '%s')";
	
	public static final String updateSignInHistoryForAdmin = String.format(
			updateSignInHistory, "Admin");
	
	public static final String updateSignInHistoryForKeyHolder = String.format(
			updateSignInHistory, "Key Holder");
	
}
