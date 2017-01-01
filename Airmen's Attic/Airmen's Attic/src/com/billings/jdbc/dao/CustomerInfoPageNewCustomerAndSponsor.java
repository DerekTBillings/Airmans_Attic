package com.billings.jdbc.dao;

import com.billings.jdbc.dto.Person;
import com.billings.jdbc.sql.CustomerInfoPageSQL;
import com.billings.utils.Logger;
import com.billings.utils.SQLStatementUtils;

public class CustomerInfoPageNewCustomerAndSponsor extends CustomerInfoPageDAO {

	@Override
	public boolean saveCustomer(Person person) {
		
		if (isInformationUnique(person)) {
		
			saveNewCustomer(person);
			saveNewCustomer(person.getSponsor());
			
			linkCustomerToNewSponsor(
				CustomerInfoPageSQL.linkCustomerToSponsorUsingSponsorLookup, person);
			
			return true;
			
		} else {
			notifyUserPhoneNumbersNotUnique();
			return false;
		}
	}
	

}
