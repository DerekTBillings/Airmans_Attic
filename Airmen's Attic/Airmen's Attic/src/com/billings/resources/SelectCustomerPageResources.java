package com.billings.resources;

import java.util.ArrayList;
import java.util.List;

import com.billings.events.implementation.SelectCustomerPageEventsAdminSignIn;
import com.billings.events.implementation.SelectCustomerPageEventsCommon;
import com.billings.events.implementation.SelectCustomerPageEventsCustomerCheckout;
import com.billings.events.implementation.SelectCustomerPageEventsCustomerLookup;
import com.billings.events.implementation.SelectCustomerPageEventsRaffleSignIn;
import com.billings.events.implementation.SelectCustomerPageEventsSponsorLookup;
import com.billings.events.implementation.SelectCustomerPageEventsVolunteerSignIn;
import com.billings.events.interfaces.SelectCustomerPageEvents;
import com.billings.jdbc.dto.FoundCustomer;
import com.billings.utils.Messages;

public class SelectCustomerPageResources {

	public static String PAGE_HEADER_LBL = "Select Customer";
	public static String CUSTOMER = "Customer";
	public static String SPONSOR = "Sponsor";
	public static String CONTACT = "Last 5 of phone";
	public static String SELECT_BTN = "Select Customer";
	public static String CANCEL_BTN = "Cancel";
	public static String SUCCESS_MSG;
	
	private static List<FoundCustomer> FOUND_CUSTOMERS;
	
	public static SelectCustomerPageEvents pageEvents;
	
	public static void setupWithFoundCustomers(List<FoundCustomer> customers) {
		customers = trimCustomersPhonesToLastFive(customers);
		
		FOUND_CUSTOMERS = customers;
	}

	private static List<FoundCustomer> trimCustomersPhonesToLastFive(
			List<FoundCustomer> customers) {
		
		for (FoundCustomer customer : customers) {
			String customerInfo = customer.getContactInfo();
			
			if (customerInfo.length() > 5) {
				int length = customerInfo.length();
				customerInfo = customerInfo.substring(length-5);
			}
			
			customer.setContactInfo(customerInfo);
		}
		
		return customers;
	}

	public static List<FoundCustomer> getFoundCustomers() {
		return FOUND_CUSTOMERS;
	}
	
	public static void setupWithSponsorLookup() {
		setupWithCommonValues();
		pageEvents = new SelectCustomerPageEventsSponsorLookup();
	}
	
	public static void setupWithCustomerSignIn() {
		setupWithCommonValues();
		pageEvents = new SelectCustomerPageEventsCommon();
	}
	
	public static void setupWithVolunteerSignIn() {
		setupWithCommonValues();
		pageEvents = new SelectCustomerPageEventsVolunteerSignIn();
	}
	
	public static void setupWithRaffleSignIn() {
		setupWithCommonValues();
		pageEvents = new SelectCustomerPageEventsRaffleSignIn();
	}
	
	public static void setupWithCustomerCheckout() {
		setupWithCommonValues();
		pageEvents = new SelectCustomerPageEventsCustomerCheckout();
	}
	
	public static void setupWithAdminPageSignIn() {
		setupWithCommonValues();
		pageEvents = new SelectCustomerPageEventsAdminSignIn();
	}
	
	public static void setupWithCustomerLookup() {
		setupWithCommonValues();
		pageEvents = new SelectCustomerPageEventsCustomerLookup();
	}
	
	private static void setupWithCommonValues() {
		if (FOUND_CUSTOMERS == null) {
			FOUND_CUSTOMERS = new ArrayList<FoundCustomer>();
		}
	}
	
}
