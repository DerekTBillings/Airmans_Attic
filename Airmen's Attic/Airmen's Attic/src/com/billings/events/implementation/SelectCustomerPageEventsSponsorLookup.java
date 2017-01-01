package com.billings.events.implementation;

import com.billings.controllers.CustomerInfoPageController;
import com.billings.events.interfaces.SelectCustomerPageEvents;
import com.billings.jdbc.dto.FoundCustomer;
import com.billings.jdbc.dto.Person;

public class SelectCustomerPageEventsSponsorLookup implements SelectCustomerPageEvents {

	@Override
	public void submit(FoundCustomer foundCustomer) {
		Person customer = CustomerInfoPageController.getPerson();
		
		Person sponsor = customer.getNewSponsor();
		
		sponsor.setPersonId(foundCustomer.getPersonId());
	}

}
