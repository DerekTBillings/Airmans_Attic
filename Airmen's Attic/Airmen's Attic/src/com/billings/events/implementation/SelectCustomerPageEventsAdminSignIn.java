package com.billings.events.implementation;

import com.billings.events.interfaces.SelectCustomerPageEvents;
import com.billings.jdbc.dao.SignInPageDAO;
import com.billings.jdbc.dto.FoundCustomer;
import com.billings.jdbc.factory.SignInPageFactory;

public class SelectCustomerPageEventsAdminSignIn implements SelectCustomerPageEvents{

	@Override
	public void submit(FoundCustomer foundCustomer) {
		SignInPageDAO dao = SignInPageFactory.getAdminPageSignInImpl();
		
		int personId = foundCustomer.getPersonId();
		
		dao.signIn(personId);
	}

}
