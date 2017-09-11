package com.billings.jdbc.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.billings.jdbc.dto.Person;
import com.billings.jdbc.sql.CustomerInfoPageSQL;
import com.billings.utils.Logger;
import com.billings.utils.Messages;
import com.billings.utils.SQLStatementUtils;

public abstract class CustomerInfoPageDAO {
	
	public abstract boolean saveCustomer(Person person);
	
	public void signInByPhone(String phone, String signInAsX) {
		SQLStatementUtils.executeQueryWithoutResultSet(
				CustomerInfoPageSQL.signInByPhoneNumber, 
				phone, signInAsX);
	}
	
	protected boolean isInformationUnique(Person person) {
		boolean phoneNumbersAreUnique = true;
		
		ResultSet resultSet = SQLStatementUtils.executeQueryAndReturnResultSet(
			CustomerInfoPageSQL.countInstancesOfSuppliedPhoneNumbers,
			person.getCellPhone()
		);
		
		try {
			phoneNumbersAreUnique = getPhoneNumberInstanceCount(resultSet);
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			SQLStatementUtils.closeConnectionsWithResultSet();
		}
		
		return phoneNumbersAreUnique;
	}
	
	protected boolean getPhoneNumberInstanceCount(ResultSet resultSet) throws SQLException {
		resultSet.next();
		String countInColumn = resultSet.getString(1);
		int count = Integer.parseInt(countInColumn);
		return count == 0; //If the count is zero, the numbers are unique.
	}

	protected void linkCustomerToNewSponsor(String linkQuery, Person customer) {
		Person sponsor = customer.getSponsor();
		
		SQLStatementUtils.executeQueryWithoutResultSet(
			linkQuery,
			returnValueOrNull(sponsor.getCellPhone()),
			returnValueOrNull(customer.getCellPhone())
		);
	}
	
	protected void linkCustomerToSponsorWithId(String linkQuery, Person customer) {
		Person sponsor = customer.getSponsor();
		
		SQLStatementUtils.executeQueryWithoutResultSet(
			linkQuery, sponsor.getPersonId(),
			returnValueOrNull(customer.getCellPhone())
		);
	}
	
	protected String returnValueOrNull(String text) {
		if (text.equals("")) {
			return null;
		}
		
		return text;
	}
	
	protected void saveNewCustomer(Person person) {
		SQLStatementUtils.executeQueryWithoutResultSet(
			CustomerInfoPageSQL.saveNewCustomer, 
			person.getLastName(), person.getFirstName(), person.getRank(),
			person.getDependentStatus(), person.getCellPhone());
	}
	
	protected void notifyUserPhoneNumbersNotUnique() {
		Logger.notifyUser(Messages.needsUniquePhoneNumber);
	}
}
