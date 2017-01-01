package com.billings.events.implementation;

import com.billings.controllers.CustomerInfoPageController;
import com.billings.events.interfaces.CustomerInfoPageEvents;
import com.billings.jdbc.dto.Person;

public class CustomerInfoPageEditSponsor implements CustomerInfoPageEvents {

	@Override
	public boolean submitEvent(Person person) {
		Person sponsor = CustomerInfoPageController.getPerson();
		/*All of the values are set on submit.
		*Because of this, the Person object will have all the values
		*of the sponsor. So set the person's sponsor object equal to a clone
		*of the current person, then allow the customer to submit their information.
		*/
		sponsor.setSponsor(sponsor.clone());
		return true;
	}

}
