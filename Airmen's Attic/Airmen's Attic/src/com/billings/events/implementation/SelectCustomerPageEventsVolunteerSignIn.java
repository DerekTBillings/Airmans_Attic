package com.billings.events.implementation;

import com.billings.events.interfaces.SelectCustomerPageEvents;
import com.billings.jdbc.dao.SignInPageDAO;
import com.billings.jdbc.dto.FoundCustomer;
import com.billings.jdbc.factory.SignInPageFactory;

public class SelectCustomerPageEventsVolunteerSignIn implements SelectCustomerPageEvents {

	@Override
	public void submit(FoundCustomer foundCustomer) {
		int personId = foundCustomer.getPersonId();
		
		SignInPageDAO dao = SignInPageFactory.getVolunteerSignInImpl();
		dao.signIn(personId);
	}

}
