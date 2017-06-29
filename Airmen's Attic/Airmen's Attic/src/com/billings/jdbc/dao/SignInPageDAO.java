package com.billings.jdbc.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.billings.jdbc.dto.FoundCustomer;
import com.billings.jdbc.sql.SignInPageSQL;
import com.billings.resources.SignInPageResources;
import com.billings.utils.Logger;
import com.billings.utils.SQLStatementUtils;

public abstract class SignInPageDAO {
	
	public abstract void signIn(int personId);

	public List<FoundCustomer> findCustomersByName(String query, String lastName, String firstName) {
		List<FoundCustomer> foundCustomer = new ArrayList<FoundCustomer>();
		
		lastName = cleanName(lastName);
		firstName = cleanName(firstName);
		
		ResultSet results = SQLStatementUtils.executeQueryAndReturnResultSet(
				query, lastName, firstName);
		
		try {
			buildFoundCustomerList(foundCustomer, results);
		} catch(SQLException e) {
			e.printStackTrace();
		
		} finally {
			SQLStatementUtils.closeConnectionsWithResultSet();
		}
		
		return foundCustomer;
	}

	private String cleanName(String name) {
		name = name.toLowerCase();
		name = name.trim();
		
		return name;
	}

	private void buildFoundCustomerList(List<FoundCustomer> foundCustomer,
			ResultSet results) throws SQLException {

		while (results.next()) {
			foundCustomer.add(buildCustomerFromResults(results));
		}
		
	}

	public FoundCustomer findCustomerByPhone(String query, String phone) throws SQLException {
		phone = cleanPhone(phone);
		
		ResultSet results = SQLStatementUtils.executeQueryAndReturnResultSet(
				query, phone, phone);
		
		FoundCustomer customer = null;
		try {
			if (results.next()) {
				customer = buildCustomerFromResults(results);
			}
		} catch(SQLException e) {
			throw e;
			
		} finally {
			SQLStatementUtils.closeConnectionsWithResultSet();
		}
		
		return customer;
	}
	
	private String cleanPhone(String phone) {
		phone = phone.replaceAll("[^0-9]", "");
		return phone;
	}
	
	protected FoundCustomer buildCustomerFromResults(ResultSet results) throws SQLException {
		FoundCustomer customer = new FoundCustomer();
		
		customer.setPersonId(results.getInt("personId"));
		customer.setCustomerName(results.getString("customerName"));
		customer.setSponsorName(results.getString("sponsorName"));
		customer.setContactInfo(results.getString("cellPhone"));
		
		return customer;
	}
	
	protected boolean isCustomerSignedIn(int personId, String role) {
		ResultSet results = SQLStatementUtils.executeQueryAndReturnResultSet(
			SignInPageSQL.isCustomerSignedIn,
			personId, role);
		
		boolean signedIn = getSignedInStateFromResultSet(results);
		
		return signedIn;
	}
	
	private boolean getSignedInStateFromResultSet(ResultSet results) {
		int customerSignInCount = 0;
		
		try {
			if (results.next()) {
				customerSignInCount = results.getInt("signInCount");
			}
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			SQLStatementUtils.closeConnectionsWithResultSet();
		}
		
		return customerSignInCount != 0;
	}
	
	
}
