package com.billings.events.implementation;

import java.util.List;

import com.billings.events.interfaces.SignInPageEvents;
import com.billings.jdbc.dto.FoundCustomer;
import com.billings.resources.CustomerInfoPageResources;

public class SignInPageEventsCustomerLookup extends SignInPageEvents {

	@Override
	public boolean signInEvent(String phone, String name) {
		return false;
		
	}

	@Override
	public void newCustomerEvent() {
		CustomerInfoPageResources.setupWithNewContactInfo();
		super.openNewCustomerPage();		
	}

	@Override
	protected FoundCustomer lookupByPhoneNumber(String phone) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected List<FoundCustomer> lookupByName(String name) {
		// TODO Auto-generated method stub
		return null;
	}
	
}
