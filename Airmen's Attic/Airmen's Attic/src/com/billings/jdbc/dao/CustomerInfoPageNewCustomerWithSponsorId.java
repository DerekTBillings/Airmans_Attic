package com.billings.jdbc.dao;

import com.billings.jdbc.dto.Person;
import com.billings.jdbc.sql.CustomerInfoPageSQL;

public class CustomerInfoPageNewCustomerWithSponsorId extends CustomerInfoPageDAO {

	@Override
	public boolean saveCustomer(Person person) {
		
		if (isInformationUnique(person)) {
			
			saveNewCustomer(person);
			linkCustomerToSponsorWithId(CustomerInfoPageSQL.linkCustomerToSponsorUsingSponsorId, person);
			
			return true;
			
		} else {
			notifyUserPhoneNumbersNotUnique();
			return false;
		}
		
	}
	
}
