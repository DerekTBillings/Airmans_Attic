package com.billings.jdbc.dao;

import com.billings.jdbc.sql.EditAtticInfoPageSQL;
import com.billings.utils.SQLStatementUtils;

public class EditAtticInfoPageMessageImpl implements EditAtticInfoPageDAO {

	@Override
	public String getInfo() {
		String query = EditAtticInfoPageSQL.getAtticMessage;
				
		return (String)SQLStatementUtils.executeQueryForSingleCell(query, String.class);
	}

	@Override
	public void updateInfo(String update) {
		String query = EditAtticInfoPageSQL.updateAtticMessage;
		
		SQLStatementUtils.executeUpdate(query, update);
	}

}
