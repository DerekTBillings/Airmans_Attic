package com.billings.events.implementation;

import com.billings.events.interfaces.CustomerInfoPageEvents;
import com.billings.jdbc.dao.CustomerInfoPageDAO;
import com.billings.jdbc.dao.CustomerInfoPageEditCustomerImpl;
import com.billings.jdbc.dto.Person;
import com.billings.utils.Logger;
import com.billings.utils.Messages;

public class CustomerInfoPageEditCustomer implements CustomerInfoPageEvents {

	@Override
	public boolean submitEvent(Person person) {
		CustomerInfoPageDAO dao = new CustomerInfoPageEditCustomerImpl();
		
		boolean success = dao.saveCustomer(person);
		
		Logger.notifyUser(
			Messages.CUSTOMER_UPDATED);
		
		return success;
	}
	
	public void toggleCustomerArchive(int personId, String currentArchiveStatus) {
		CustomerInfoPageDAO dao = new CustomerInfoPageEditCustomerImpl();
		
		String archiveStatus = (currentArchiveStatus.equals("Active")) ? "Archived" : "Active";
		
		((CustomerInfoPageEditCustomerImpl)dao).toggleCustomerArchive(personId, archiveStatus);
		
		Logger.notifyUser(
			Messages.CUSTOMER_ARCHIVED);
	}

}
