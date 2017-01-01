package com.billings.jdbc.factory;

import com.billings.jdbc.dao.CustomerInfoPageDAO;
import com.billings.jdbc.dao.CustomerInfoPageNewCustomerAndSponsor;
import com.billings.jdbc.dao.CustomerInfoPageNewCustomer;
import com.billings.jdbc.dao.CustomerInfoPageNewCustomerWithSponsorId;

public class CustomerInfoPageFactory {
	
	public static CustomerInfoPageDAO getNewCustomerImpl() {
		return new CustomerInfoPageNewCustomer();
	}
	
	public static CustomerInfoPageDAO getNewCustomerAndSponsorImpl() {
		return new CustomerInfoPageNewCustomerAndSponsor();
	}
	
	public static CustomerInfoPageDAO getNewCustoemrWithSponsorIdImpl() {
		return new CustomerInfoPageNewCustomerWithSponsorId();
	}
	
}
