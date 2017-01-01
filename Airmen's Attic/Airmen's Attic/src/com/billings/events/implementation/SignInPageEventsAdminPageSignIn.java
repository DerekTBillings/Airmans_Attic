package com.billings.events.implementation;

import java.util.List;

import com.billings.events.interfaces.SignInPageEvents;
import com.billings.jdbc.dto.FoundCustomer;
import com.billings.jdbc.factory.SignInPageFactory;
import com.billings.resources.CustomerInfoPageResources;
import com.billings.resources.SelectCustomerPageResources;

public class SignInPageEventsAdminPageSignIn extends SignInPageEvents {

private boolean success = false;
	
	@Override
	public boolean signInEvent(String phone, String name) {
		success = false;
		
		super.dao = SignInPageFactory.getAdminPageSignInImpl();
		
		if (!phone.isEmpty()) {
			FoundCustomer foundCustomer = lookupByPhoneNumber(phone);
			
			if (!foundCustomerNullCheck(foundCustomer)) {
				success = true;
				
				customerSignIn(foundCustomer);
			}
		}
		
		if (!success && !name.isEmpty()) {
			List<FoundCustomer> foundCustomers = lookupByName(name);
			
			if (!foundCustomersEmptyCheck(foundCustomers)) {
				success = true;
				openSelectCustomerPage(foundCustomers);
			}
		}
		
		return success;
	}
	
	private void customerSignIn(FoundCustomer foundCustomer) {		
			dao.signIn(foundCustomer.getPersonId());
	}
	
	private void openSelectCustomerPage(List<FoundCustomer> foundCustomers) {
		SelectCustomerPageResources.setupWithAdminPageSignIn();
		super.displayFoundCustomersToUser(foundCustomers);
	}

	@Override
	public void newCustomerEvent() {
		//The new customer button is disabled
	}

}
