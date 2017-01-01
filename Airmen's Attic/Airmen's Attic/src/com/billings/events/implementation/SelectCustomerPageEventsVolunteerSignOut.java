package com.billings.events.implementation;

import com.billings.events.interfaces.SelectCustomerPageEvents;
import com.billings.jdbc.dao.SignInPageDAO;
import com.billings.jdbc.dto.FoundCustomer;
import com.billings.jdbc.factory.SignInPageFactory;

public class SelectCustomerPageEventsVolunteerSignOut implements SelectCustomerPageEvents {

	@Override
	public void submit(FoundCustomer foundCustomer) {
		SignInPageDAO dao = SignInPageFactory.getVolunteerSignOutImpl();
		
		int personId = foundCustomer.getPersonId();
		
		//Poor naming convention. 
		//This implementation will sign out the passed in customer
		dao.signIn(personId);
	}

}
