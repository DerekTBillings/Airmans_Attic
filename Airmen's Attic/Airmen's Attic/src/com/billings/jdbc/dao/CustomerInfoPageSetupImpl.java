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
		String query = CustomerInfoPageSetupSQL.getRankList;
		
		return SQLStatementUtils.executeQuery(query, String.class);
	}

	public List<String> getRequiredFields() {
		String query = CustomerInfoPageSetupSQL.getRequiredFieldsList;
		
		return SQLStatementUtils.executeQuery(query, String.class);
	}

	@Override
	public Person getPersonInfoById(int personId) {
		String query = CustomerInfoPageSetupSQL.getPersonById;
		
		return (Person)SQLStatementUtils.executeQueryForSingleRow(query, Person.class, personId);
	}
	
	

}
