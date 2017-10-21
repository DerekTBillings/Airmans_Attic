package com.billings.jdbc.dao;

import java.sql.SQLException;
import java.util.List;

import com.billings.jdbc.dto.FoundCustomer;
import com.billings.jdbc.sql.SignInPageSQL;
import com.billings.utils.SQLStatementUtils;

@SuppressWarnings("unchecked")
public abstract class SignInPageDAO {
	
	public abstract void signIn(int personId);

	public List<FoundCustomer> findCustomersByName(String query, String lastName, String firstName) {
		lastName = standardize(lastName);
		firstName = standardize(firstName);
		
		List<FoundCustomer> customers = SQLStatementUtils.executeQuery(
				query, FoundCustomer.class, lastName, firstName);
		
		return customers;
	}

	private String standardize(String name) {
		return name.toLowerCase().trim();
	}

	public FoundCustomer findCustomerByPhone(String query, String phone) throws SQLException {
		phone = sanitize(phone);
		
		FoundCustomer customer = (FoundCustomer)SQLStatementUtils.executeQueryForSingleRow(
				query, FoundCustomer.class, phone);
		
		return customer;
	}
	
	private String sanitize(String phone) {
		return phone.replaceAll("[^0-9]", "");
	}
	
	protected boolean isCustomerSignedIn(int personId, String role) {
		String query = SignInPageSQL.isCustomerSignedIn;
		
		int signInCount = (Integer)SQLStatementUtils.executeQueryForSingleCell(
			query, Integer.class, personId, role);
		
		return isSignedIn(signInCount);
	}

	private boolean isSignedIn(int signInCount) {
		return signInCount != 0;
	}	
	
}
