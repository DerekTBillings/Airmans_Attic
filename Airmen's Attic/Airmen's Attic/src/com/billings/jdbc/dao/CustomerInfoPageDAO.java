package com.billings.jdbc.dao;

import com.billings.jdbc.dto.Person;
import com.billings.jdbc.sql.CustomerInfoPageSQL;
import com.billings.utils.Logger;
import com.billings.utils.Messages;
import com.billings.utils.SQLStatementUtils;

public abstract class CustomerInfoPageDAO {
	
	public abstract boolean saveCustomer(Person person);
	
	public void signInByPhone(String phone, String signInAsX) {
		String query = CustomerInfoPageSQL.signInByPhoneNumber;
		
		SQLStatementUtils.executeInsert(query, phone, signInAsX);
	}
	
	protected boolean isInformationUnique(Person person) {
		String query = CustomerInfoPageSQL.countInstancesOfSuppliedPhoneNumbers;
		
		int count = (Integer)SQLStatementUtils.executeQueryForSingleCell(
			query, Integer.class, person.getCellPhone());
		
		return count == 0;
	}

	protected void linkCustomerToNewSponsor(String linkQuery, Person customer) {
		Person sponsor = customer.getSponsor();
		
		SQLStatementUtils.executeUpdate(linkQuery,
			returnValueOrNull(sponsor.getCellPhone()),
			returnValueOrNull(customer.getCellPhone())
		);
	}
	
	protected void linkCustomerToSponsorWithId(String linkQuery, Person customer) {
		Person sponsor = customer.getSponsor();
		
		SQLStatementUtils.executeUpdate(
			linkQuery, sponsor.getPersonId(),
			returnValueOrNull(customer.getCellPhone())
		);
	}
	
	protected String returnValueOrNull(String text) {
		return (text.equals("")) ? null : text;
	}
	
	protected void saveNewCustomer(Person person) {
		String query = CustomerInfoPageSQL.saveNewCustomer;
		
		SQLStatementUtils.executeInsert(query,
			person.getLastName(), person.getFirstName(), person.getRank(),
			person.getDependentStatus(), person.getCellPhone());
	}
	
	protected void notifyUserPhoneNumbersNotUnique() {
		Logger.notifyUser(Messages.needsUniquePhoneNumber);
	}
}
