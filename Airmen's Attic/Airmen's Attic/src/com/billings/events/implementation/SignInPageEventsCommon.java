package com.billings.events.implementation;

import java.sql.SQLException;
import java.util.List;

import com.billings.events.interfaces.SignInPageEvents;
import com.billings.jdbc.dto.FoundCustomer;
import com.billings.jdbc.factory.SignInPageFactory;
import com.billings.jdbc.sql.SignInPageSQL;
import com.billings.resources.CustomerInfoPageResources;
import com.billings.resources.SelectCustomerPageResources;
import com.billings.resources.SignInPageResources;
import com.billings.utils.Logger;
import com.billings.utils.Messages;

public class SignInPageEventsCommon extends SignInPageEvents {

	private boolean success = false;
	
	@Override
	public boolean signInEvent(String phone, String name) {
		success = false;
		
		super.dao = SignInPageFactory.getCommonImpl();
		
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
		SelectCustomerPageResources.setupWithCustomerSignIn();
		super.displayFoundCustomersToUser(foundCustomers);
	}

	@Override
	public void newCustomerEvent() {
		CustomerInfoPageResources.setupWithNewContactInfo();
		
		super.openNewCustomerPage();
	}

}
