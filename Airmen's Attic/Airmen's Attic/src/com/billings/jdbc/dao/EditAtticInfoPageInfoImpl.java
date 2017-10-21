package com.billings.jdbc.dao;

import com.billings.jdbc.sql.EditAtticInfoPageSQL;
import com.billings.utils.SQLStatementUtils;

public class EditAtticInfoPageInfoImpl implements EditAtticInfoPageDAO {

	@Override
	public String getInfo() {
		String query = EditAtticInfoPageSQL.getAtticInfo;
				
		return (String)SQLStatementUtils.executeQueryForSingleCell(query, String.class);
	}

	@Override
	public void updateInfo(String update) {
		String query = EditAtticInfoPageSQL.updateAtticInfo;
		
		SQLStatementUtils.executeUpdate(query, update);
	}

}
