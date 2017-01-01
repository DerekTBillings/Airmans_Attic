package com.billings.events.interfaces;

import com.billings.jdbc.dto.Person;

public interface CustomerInfoPageEvents {

	boolean submitEvent(Person person);
	
}
