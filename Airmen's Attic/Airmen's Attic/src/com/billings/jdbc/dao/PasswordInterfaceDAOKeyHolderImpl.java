package com.billings.jdbc.dao;

import java.sql.ResultSet;

import com.billings.jdbc.sql.PasswordInterfacePageSQL;
import com.billings.utils.Logger;
import com.billings.utils.SQLStatementUtils;

public class PasswordInterfaceDAOKeyHolderImpl implements PasswordInterfaceDAO {

	@Override
	public boolean validateUsernameAndPassword(String username, String password) {
		boolean validUser = false;
		
		String query = PasswordInterfacePageSQL.validateKeyHolderUsernameAndPassword;
		
		ResultSet results = SQLStatementUtils.executeQueryAndReturnResultSet(
				query, username, password);
		
		try {
			if (results.next()) {
				int matchesFound = results.getInt(1);
				validUser = matchesFound > 0;
			}
		
		} catch(Exception e) {
			Logger.log(e.getMessage());
		
		} finally {
			SQLStatementUtils.closeConnectionsWithResultSet();
		}
		
		return validUser;
	}

	@Override
	public void updateSignInHistory(String username) {
		String query = PasswordInterfacePageSQL.updateSignInHistoryForKeyHolder;
		
		SQLStatementUtils.executeQueryWithoutResultSet(query, username);
	}

}
