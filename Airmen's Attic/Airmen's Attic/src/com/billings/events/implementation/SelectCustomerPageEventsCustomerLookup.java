package com.billings.events.implementation;

import com.billings.events.interfaces.SelectCustomerPageEvents;
import com.billings.jdbc.dto.FoundCustomer;
import com.billings.main.WindowController;
import com.billings.resources.CustomerInfoPageResources;
import com.billings.utils.FXMLFactory;

public class SelectCustomerPageEventsCustomerLookup implements SelectCustomerPageEvents {

	@Override
	public void submit(FoundCustomer foundCustomer) {
		int personId = foundCustomer.getPersonId();
		
		CustomerInfoPageResources.setPersonId(personId);
		
		openCustomerLookup();
	}
	
	private void openCustomerLookup() {
		CustomerInfoPageResources.setupWithEditCustomer();
		
		WindowController.createPopupWindow(
			FXMLFactory.getCustomerInfoPage()
		);
	}

}
