package com.billings.jdbc.dao;

import com.billings.jdbc.sql.AdminManagementSQL;
import com.billings.utils.SQLStatementUtils;

public class AdminManagementImpl implements AdminManagementDAO {
	
	@Override
	public void makeAdmin(int personId, String username, String password) {
		String query = AdminManagementSQL.makeAdmin;

		SQLStatementUtils.executeInsert(
				query, personId, username, password);
	}
	
	@Override
	public void removeAdmin(int personId) {
		String query = AdminManagementSQL.removeAdmin;
		
		SQLStatementUtils.executeDelete(query, personId);
	}

	@Override
	public boolean isUsernameAvailable(String username) {
		String query = AdminManagementSQL.isUsernameAvailable;
		
		return (Boolean)SQLStatementUtils.executeQueryForSingleCell(
				query, Boolean.class, username);
	}

}
