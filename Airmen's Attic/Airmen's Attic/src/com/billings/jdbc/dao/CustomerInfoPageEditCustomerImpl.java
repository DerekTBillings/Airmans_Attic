package com.billings.jdbc.dao;

import com.billings.jdbc.dto.Person;
import com.billings.jdbc.sql.CustomerInfoPageSQL;
import com.billings.utils.SQLStatementUtils;

public class CustomerInfoPageEditCustomerImpl extends CustomerInfoPageDAO {

	@Override
	public boolean saveCustomer(Person person) {
		SQLStatementUtils.executeQueryWithoutResultSet(
				CustomerInfoPageSQL.updateCustomer, 
				person.getLastName(), person.getFirstName(), person.getRank(),
				person.getDependentStatus(), person.getCellPhone(), person.getPersonId());
		
		return true;
	}
	
	public void toggleCustomerArchive(int personId, String newArchiveStatus) {
		SQLStatementUtils.executeQueryWithoutResultSet(
				CustomerInfoPageSQL.updateArchiveStatus, 
				newArchiveStatus, personId);
	}

}
