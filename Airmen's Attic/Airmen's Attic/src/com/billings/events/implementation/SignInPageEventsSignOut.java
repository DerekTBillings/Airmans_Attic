package com.billings.events.implementation;

import java.util.List;

import com.billings.events.interfaces.SignInPageEvents;
import com.billings.jdbc.dto.FoundCustomer;
import com.billings.jdbc.factory.SignInPageFactory;

public class SignInPageEventsSignOut extends SignInPageEvents {

	private boolean success = false;
	
	@Override
	public boolean signInEvent(String phone, String name) {
		super.dao = SignInPageFactory.getVolunteerSignOutImpl();
		
		if (!phone.isEmpty()) {
			FoundCustomer foundCustomer = super.lookupByPhoneNumber(phone);
			
			if (!super.foundCustomerNullCheck(foundCustomer)) {
				success = true;
				
				int personId = foundCustomer.getPersonId();
				dao.signIn(personId);
			}
		}
		
		if (!success && !name.isEmpty()) {
			List<FoundCustomer> foundCustomers = super.lookupByName(name);
			
			if (!super.foundCustomersEmptyCheck(foundCustomers)) {
				success = true;
			}
		}
		
		return success;
	}
	
	@Override
	public void newCustomerEvent() {
		// TODO Auto-generated method stub
		
	}

	

}
