package com.billings.events.implementation;

import java.sql.SQLException;
import java.util.List;

import com.billings.events.interfaces.SignInPageEvents;
import com.billings.jdbc.dto.FoundCustomer;
import com.billings.jdbc.factory.SignInPageFactory;
import com.billings.jdbc.sql.SignInPageSQL;
import com.billings.main.WindowController;
import com.billings.resources.CustomerInfoPageResources;
import com.billings.resources.SelectCustomerPageResources;
import com.billings.utils.FXMLFactory;

public class SignInPageEventsCustomerLookup extends SignInPageEvents {

	@Override
	public boolean signInEvent(String phone, String name) {
		boolean success = false;
		
		super.dao = SignInPageFactory.getCommonImpl();
		
		if (!phone.isEmpty()) {
			FoundCustomer customer = lookupByPhoneNumber(phone);
			
			if (!super.foundCustomerNullCheck(customer)) {
				success = true;
				int personId = customer.getPersonId();
				CustomerInfoPageResources.setPersonId(personId);
				
				openCustomerInfoPage();
			}
		}
		
		if (!success && !name.isEmpty()) {
			List<FoundCustomer> customers = lookupByName(name);
			
			if (!super.foundCustomersEmptyCheck(customers)) {
				SelectCustomerPageResources.setupWithCustomerLookup();
				super.displayFoundCustomersToUser(customers);
				success = true;
			}
		}
		
		return success;
	}
	
	private void openCustomerInfoPage() {
		CustomerInfoPageResources.setupWithEditCustomer();
		
		WindowController.createPopupWindow(
				FXMLFactory.getCustomerInfoPage());
	}
	
	@Override
	protected FoundCustomer lookupByPhoneNumber(String phone) {
		FoundCustomer customer = null;
		
		try {
			customer = dao.findCustomerByPhone(
					SignInPageSQL.findCustomerByPhone, phone);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return customer;
	}
	
	@Override
	protected List<FoundCustomer> lookupByName(String name) {
		String[] parsedName = parseName(name);
		String lastName = parsedName[0];
		String firstName = parsedName[1];
		
		List<FoundCustomer> customers = dao.findCustomersByName(
				SignInPageSQL.findCustomerByName, lastName, firstName);
		
		return customers;
	}

	@Override
	public void newCustomerEvent() {
		return;
	}
	
}
