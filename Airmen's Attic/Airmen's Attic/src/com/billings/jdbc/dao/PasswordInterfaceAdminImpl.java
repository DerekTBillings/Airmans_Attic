package com.billings.jdbc.dao;

import com.billings.jdbc.sql.PasswordInterfacePageSQL;
import com.billings.utils.SQLStatementUtils;

public class PasswordInterfaceAdminImpl implements PasswordInterfaceDAO {

	@Override
	public boolean validateUsernameAndPassword(String username, String password) {
		String query = PasswordInterfacePageSQL.validateAdminUsernameAndPassword;
		
		int matchesFound = (Integer)SQLStatementUtils.executeQueryForSingleCell(
				query, Integer.class, username, password);
		
		return matchesFound > 0;
	}

	@Override
	public void updateSignInHistory(String username) {
		String query = PasswordInterfacePageSQL.updateSignInHistoryForAdmin;
		
		SQLStatementUtils.executeUpdate(query, username);
	}

}
