package com.billings.events.implementation;

import java.util.List;

import com.billings.events.interfaces.SignInPageEvents;
import com.billings.jdbc.dto.FoundCustomer;
import com.billings.jdbc.factory.SignInPageFactory;
import com.billings.main.WindowController;
import com.billings.resources.CustomerCheckOutPageResources;
import com.billings.resources.SelectCustomerPageResources;
import com.billings.utils.FXMLFactory;

public class SignInPageEventsCustomerCheckOut extends SignInPageEvents {

	private boolean success = false;
	
	@Override
	public boolean signInEvent(String phone, String name) {
		success = false;
		
		super.dao = SignInPageFactory.getCustomerCheckOutImpl();
		
		if (!phone.isEmpty()) {
			FoundCustomer foundCustomer = super.lookupByPhoneNumber(phone);
			
			int personId = foundCustomer.getPersonId();
			
			if (!super.foundCustomerNullCheck(foundCustomer)) {
				success = true;
				//This method will submit the person's information to the Customer Checkout Page
				dao.signIn(personId);
			}
		}
		
		if (!success && !name.isEmpty()) {
			List<FoundCustomer> foundCustomers = super.lookupByName(name);
			
			if (!foundCustomersEmptyCheck(foundCustomers)) {
				success = true;
				SelectCustomerPageResources.setupWithCustomerCheckout();
				super.displayFoundCustomersToUser(foundCustomers);
			}
		}
		
		return success;
	}

	@Override
	public void newCustomerEvent() {
		
	}
	

}
