package com.billings.events.implementation;

import com.billings.events.interfaces.CustomerInfoPageEvents;
import com.billings.jdbc.dto.Person;

public class CustomerInfoPageEditCustomer implements CustomerInfoPageEvents {

	@Override
	public boolean submitEvent(Person person) {
		return false;
		
	}

}
