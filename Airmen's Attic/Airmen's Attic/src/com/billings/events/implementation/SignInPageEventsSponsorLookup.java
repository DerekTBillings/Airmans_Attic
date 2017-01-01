package com.billings.events.implementation;

import java.sql.SQLException;
import java.util.List;

import com.billings.controllers.CustomerInfoPageController;
import com.billings.events.interfaces.SignInPageEvents;
import com.billings.jdbc.dao.SignInPageDAO;
import com.billings.jdbc.dto.FoundCustomer;
import com.billings.jdbc.dto.Person;
import com.billings.jdbc.factory.SignInPageFactory;
import com.billings.jdbc.sql.SignInPageSQL;
import com.billings.resources.CustomerInfoPageResources;
import com.billings.resources.SelectCustomerPageResources;
import com.billings.utils.Logger;
import com.billings.utils.Messages;

public class SignInPageEventsSponsorLookup extends SignInPageEvents {

	SignInPageDAO dao;
	boolean success = false;
	
	@Override
	public boolean signInEvent(String phone, String name) {
		success = false;
		
		dao = SignInPageFactory.getCommonImpl();
		
		if (!phone.isEmpty()) {
			FoundCustomer foundCustomer = lookupByPhoneNumber(phone);
			
			if (success) {
				storeSponsorInfoInPerson(foundCustomer);
			} else {
				String notice = String.format(Messages.NO_X_FOUND_FOR_PHONE, "sponsors");
				Logger.notifyUser(notice);
			}
		} 
		
		if (!success && !name.isEmpty()) {
			lookupByName(name);
		}
		
		return success;
	}
	
	@Override
	protected FoundCustomer lookupByPhoneNumber(String phone) {
		FoundCustomer sponsorForCustomer = handleAndFindCustomerByPhone(phone);
		
		if (sponsorForCustomer != null) {
			success = true;
		}
		
		return sponsorForCustomer;
	}
	
	private FoundCustomer handleAndFindCustomerByPhone(String phone) {
		FoundCustomer sponsorForCustomer = null;
		
		try {
			sponsorForCustomer = dao.findCustomerByPhone(
					SignInPageSQL.findSponsorByPhone, phone);
		} catch(SQLException e) {
			Logger.log("No customer found for phone number "+phone);
		}
		
		return sponsorForCustomer;
	}
	
	private void storeSponsorInfoInPerson(FoundCustomer sponsor) {
		int sponsorId = sponsor.getPersonId();
		
		Person customer = CustomerInfoPageController.getPerson();
		
		Person emptySponsor = customer.getNewSponsor();

		emptySponsor.setPersonId(sponsorId);
	}

	@Override
	protected List<FoundCustomer> lookupByName(String name) {
		String[] parsedName = super.parseName(name);
		String lastName = parsedName[0];
		String firstName = parsedName[1];
		
		List<FoundCustomer> foundCustomers = dao.findCustomersByName(
				SignInPageSQL.findSponsorByName, lastName, firstName);
		
		if (foundCustomers.isEmpty()) {
			String notice = String.format(Messages.NO_X_FOUND_FOR_NAME, "sponsors");
			Logger.notifyUser(notice);
		} else {
			success = true;
			SelectCustomerPageResources.setupWithSponsorLookup();
			super.displayFoundCustomersToUser(foundCustomers);
		}
		
		return foundCustomers;
	}

	@Override
	public void newCustomerEvent() {
		CustomerInfoPageResources.setupWithEditSponsorInfo();
		
		super.openNewCustomerPage();
	}


}
