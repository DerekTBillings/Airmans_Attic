package com.billings.events.implementation;

import com.billings.events.interfaces.SelectCustomerPageEvents;
import com.billings.jdbc.dto.FoundCustomer;
import com.billings.main.WindowController;
import com.billings.resources.RafflePageResources;
import com.billings.utils.FXMLFactory;

public class SelectCustomerPageEventsRaffleSignIn implements SelectCustomerPageEvents {

	@Override
	public void submit(FoundCustomer foundCustomer) {
		int customerId = foundCustomer.getPersonId();
		
		RafflePageResources.setCustomerId(customerId);
		
		openRafflePage();
	}

	private static void openRafflePage() {
		WindowController.createPopupWindow(FXMLFactory.getRafflePage());
	}

}
