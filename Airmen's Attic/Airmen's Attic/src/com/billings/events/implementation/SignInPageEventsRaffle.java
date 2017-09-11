package com.billings.events.implementation;

import java.util.List;

import com.billings.events.interfaces.SignInPageEvents;
import com.billings.jdbc.dto.FoundCustomer;
import com.billings.jdbc.factory.SignInPageFactory;
import com.billings.main.WindowController;
import com.billings.resources.RafflePageResources;
import com.billings.resources.SelectCustomerPageResources;
import com.billings.utils.FXMLFactory;

public class SignInPageEventsRaffle extends SignInPageEvents {

	private boolean success = false;

	@Override
	public boolean signInEvent(String phone, String name) {
		super.dao = SignInPageFactory.getRaffleImpl();

		if (!phone.isEmpty()) {
			FoundCustomer foundCustomer = lookupByPhoneNumber(phone);

			if (!super.foundCustomerNullCheck(foundCustomer)) {
				success = true;
				
				RafflePageResources.setCustomerId(foundCustomer.getPersonId());
				openRafflePage();
			}
		}

		if (!success && !name.isEmpty()) {
			List<FoundCustomer> foundCustomers = lookupByName(name);

			if (!super.foundCustomersEmptyCheck(foundCustomers)) {
				success = true;

				SelectCustomerPageResources.setupWithRaffleSignIn();
				super.displayFoundCustomersToUser(foundCustomers);
			}
		}
		
		return success;
	}

	private static void openRafflePage() {
		WindowController.createPopupWindow(FXMLFactory.getRafflePage());
	}

	@Override
	public void newCustomerEvent() {
		openNewRaffleItemPage();
	}
	
	private void openNewRaffleItemPage() {
		WindowController.createPopupWindow(FXMLFactory.getNewRaffleItemPage());
	}

}
