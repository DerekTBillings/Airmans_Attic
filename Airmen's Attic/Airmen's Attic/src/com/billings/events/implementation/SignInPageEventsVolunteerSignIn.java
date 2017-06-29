package com.billings.events.implementation;

import java.util.List;

import com.billings.events.interfaces.SignInPageEvents;
import com.billings.jdbc.dao.SignInPageDAO;
import com.billings.jdbc.dto.FoundCustomer;
import com.billings.jdbc.factory.SignInPageFactory;
import com.billings.resources.CustomerInfoPageResources;
import com.billings.resources.SelectCustomerPageResources;
import com.billings.utils.Logger;
import com.billings.utils.Messages;

public class SignInPageEventsVolunteerSignIn extends SignInPageEvents {
	
	@Override
	public boolean signInEvent(String phone, String name) {
		boolean success = false;
		
		super.dao = SignInPageFactory.getVolunteerSignInImpl();
		
		if (!phone.isEmpty()) {
			FoundCustomer volunteer = super.lookupByPhoneNumber(phone);
			
			if (!super.foundCustomerNullCheck(volunteer)) {
				success = true;
				dao.signIn(volunteer.getPersonId());
			}
		}
		
		if (!success && !name.isEmpty()) {
			List<FoundCustomer> volunteers = super.lookupByName(name);
			
			if (!super.foundCustomersEmptyCheck(volunteers)) {
				SelectCustomerPageResources.setupWithVolunteerSignIn();
				super.displayFoundCustomersToUser(volunteers);
				success = true;
			}
		}
		
		return success;
	}

	@Override
	public void newCustomerEvent() {
		CustomerInfoPageResources.setupWithNewVolunteerInfo();
		
		super.openNewCustomerPage();
	}

}
