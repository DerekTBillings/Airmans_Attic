package com.billings.events.implementation;

import com.billings.events.interfaces.SelectCustomerPageEvents;
import com.billings.jdbc.dao.SignInPageDAO;
import com.billings.jdbc.dto.FoundCustomer;
import com.billings.jdbc.factory.SignInPageFactory;

public class SelectCustomerPageEventsCustomerCheckout implements SelectCustomerPageEvents {

	@Override
	public void submit(FoundCustomer foundCustomer) {
		SignInPageDAO dao = SignInPageFactory.getCustomerCheckOutImpl();
		
		int personId = foundCustomer.getPersonId();

		//This method will submit the person's information to the Customer Checkout Page
		dao.signIn(personId);
	}

}
