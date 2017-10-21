package com.billings.jdbc.dao;

import com.billings.jdbc.sql.PasswordInterfacePageSQL;
import com.billings.utils.SQLStatementUtils;

public class PasswordInterfaceKeyHolderImpl implements PasswordInterfaceDAO {

	@Override
	public boolean validateUsernameAndPassword(String username, String password) {
		String query = PasswordInterfacePageSQL.validateKeyHolderUsernameAndPassword;
		
		int matchesFound = (Integer)SQLStatementUtils.executeQueryForSingleCell(
				query, Integer.class, username, password);
		
		return matchesFound > 0;
	}

	@Override
	public void updateSignInHistory(String username) {
		String query = PasswordInterfacePageSQL.updateSignInHistoryForKeyHolder;
		
		SQLStatementUtils.executeInsert(query, username);
	}

}
