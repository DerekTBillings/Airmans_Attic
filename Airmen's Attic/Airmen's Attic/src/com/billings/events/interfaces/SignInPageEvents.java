package com.billings.events.interfaces;

import java.sql.SQLException;
import java.util.List;

import com.billings.jdbc.dao.SignInPageDAO;
import com.billings.jdbc.dto.FoundCustomer;
import com.billings.jdbc.sql.SignInPageSQL;
import com.billings.main.WindowController;
import com.billings.resources.SelectCustomerPageResources;
import com.billings.resources.SignInPageResources;
import com.billings.utils.FXMLFactory;
import com.billings.utils.Logger;
import com.billings.utils.Messages;

public abstract class SignInPageEvents {

	protected SignInPageDAO dao;
	
	public abstract boolean signInEvent(String phone, String name);
	
	public abstract void newCustomerEvent();

	protected FoundCustomer lookupByPhoneNumber(String phone) {
		FoundCustomer customer = null;
		
		try {
			customer = dao.findCustomerByPhone(
					SignInPageSQL.findActiveCustomerByPhone, phone);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return customer;
	}
	
	protected List<FoundCustomer> lookupByName(String name) {
		String[] parsedName = parseName(name);
		String lastName = parsedName[0];
		String firstName = parsedName[1];
		
		List<FoundCustomer> customers = dao.findCustomersByName(
				SignInPageSQL.findActiveCustomerByName, lastName, firstName);
		
		return customers;
	}
	
	protected void openNewCustomerPage() {
		WindowController.createPopupWindow(
				FXMLFactory.getCustomerInfoPage());
	}
	
	protected boolean foundCustomerNullCheck(FoundCustomer foundCustomer) {
		boolean isNull = false;
		
		if (foundCustomer == null) {
			String notice = String.format(Messages.NO_X_FOUND_FOR_PHONE, "customers");
			Logger.notifyUser(notice);
			isNull = true;
		} 
		
		return isNull;
	}
	
	protected boolean foundCustomersEmptyCheck(List<FoundCustomer> foundCustomers) {
		boolean isEmpty = false;
		
		if (foundCustomers.isEmpty()) {
			isEmpty = true;
			String notice = String.format(Messages.NO_X_FOUND_FOR_NAME, "customers");
			Logger.notifyUser(notice);
		}
		
		return isEmpty;
	}
	
	protected String[] parseName(String name) {
		String[] nameSplit = new String[2];
		
		if (name.contains(",")) {
			nameSplit = name.split(",");
		} else if(name.contains(" ")){
			nameSplit = name.split(" ");
		} else {
			nameSplit[0] = name;
			nameSplit[1] = "%";
		}		
		
		return nameSplit;
	}
	
	protected void displayFoundCustomersToUser(List<FoundCustomer> foundCustomers) {
		SelectCustomerPageResources.setupWithFoundCustomers(foundCustomers);
		
		WindowController.createPopupWindow(
				FXMLFactory.getSelectCustomerPage());
	}
	
}
