package com.billings.jdbc.dao;


import com.billings.jdbc.dto.Person;
import com.billings.jdbc.sql.CustomerInfoPageSQL;
import com.billings.utils.SQLStatementUtils;

public class CustomerInfoPageNewCustomer extends CustomerInfoPageDAO {

	@Override
	public boolean saveCustomer(Person person) {
		
		if (isInformationUnique(person)) {
			saveNewCustomer(person);
			
			return true;
		
		} else {
			notifyUserPhoneNumbersNotUnique();
			
			return false;
		}
	}

}
