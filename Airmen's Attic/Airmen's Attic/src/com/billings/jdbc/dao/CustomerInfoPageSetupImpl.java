package com.billings.jdbc.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.billings.jdbc.dto.Person;
import com.billings.jdbc.sql.CustomerInfoPageSetupSQL;
import com.billings.utils.Common;
import com.billings.utils.Logger;
import com.billings.utils.SQLStatementUtils;

public class CustomerInfoPageSetupImpl implements CustomerInfoPageSetupDAO {

	@Override
	public List<String> getRankList() {
		List<String> rankList = null;
		
		ResultSet results = SQLStatementUtils.executeQueryAndReturnResultSet(
				CustomerInfoPageSetupSQL.getRankList);
		
		try {
			rankList = getRankListFromResults(results);
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			SQLStatementUtils.closeConnectionsWithResultSet();
		}
		
		return rankList;
	}

	private List<String> getRankListFromResults(ResultSet results) throws SQLException{
		List<String> rankList = new ArrayList<String>();
		
		while(results.next()) {
			String rankOption = results.getString("Value");
			rankList.add(rankOption);
		}
		
		return rankList;
	}
	
	public List<String> getRequiredFields() {
		List<String> requiredFieldsList = null;
		
		ResultSet results = SQLStatementUtils.executeQueryAndReturnResultSet(
				CustomerInfoPageSetupSQL.getRequiredFieldsList);
		
		try {
			requiredFieldsList = getRequiredFieldsListFromResults(results);
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			SQLStatementUtils.closeConnectionsWithResultSet();
		}
		
		return requiredFieldsList;
	}

	private List<String> getRequiredFieldsListFromResults(ResultSet results) throws SQLException {
		List<String> requiredFieldsList = new ArrayList<String>();
		
		while(results.next()) {
			String requiredField = results.getString("Value");
			requiredFieldsList.add(requiredField);
		}
		
		return requiredFieldsList;
	}

	@Override
	public Person getPersonInfoById(int personId) {
		Person person = null;
		
		ResultSet results = SQLStatementUtils.executeQueryAndReturnResultSet(
				CustomerInfoPageSetupSQL.getPersonById, personId);
		
		try {
			if (results.next()) {
				person = Common.buildPersonFromResults(results);
				person.setArchiveStatus(results.getString("Archive_Status"));
			}
		} catch(Exception e) {
			Logger.log(e.getMessage());
		} finally {
			SQLStatementUtils.closeConnectionsWithResultSet();
		}
		
		return person;
	}
	
	

}
