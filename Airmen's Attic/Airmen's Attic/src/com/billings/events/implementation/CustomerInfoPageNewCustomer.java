package com.billings.events.implementation;

import com.billings.events.interfaces.CustomerInfoPageEvents;
import com.billings.jdbc.dao.CustomerInfoPageDAO;
import com.billings.jdbc.dto.Person;
import com.billings.jdbc.factory.CustomerInfoPageFactory;
import com.billings.resources.CustomerInfoPageResources;

public class CustomerInfoPageNewCustomer implements CustomerInfoPageEvents {

	@Override
	public boolean submitEvent(Person person) {
		CustomerInfoPageDAO customerDAO = CustomerInfoPageResources.CUSTOMER_DAO;
		boolean success = customerDAO.saveCustomer(person);
		
		signInNewCustomer(person);
		
		return success;
	}
	
	private void signInNewCustomer(Person person) {
		String phone = person.getCellPhone();
		
		CustomerInfoPageDAO dao = CustomerInfoPageResources.CUSTOMER_DAO;
		
		dao.signInByPhone(phone, 
				CustomerInfoPageResources.SIGN_IN_AS_X);
		
	}

}
