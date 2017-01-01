package com.billings.jdbc.factory;

import com.billings.jdbc.dao.SignInPageAdminPageSignIn;
import com.billings.jdbc.dao.SignInPageCommon;
import com.billings.jdbc.dao.SignInPageCustomerCheckout;
import com.billings.jdbc.dao.SignInPageDAO;
import com.billings.jdbc.dao.SignInPageRaffle;
import com.billings.jdbc.dao.SignInPageVolunteerSignIn;
import com.billings.jdbc.dao.SignInPageVolunteerSignOut;

public class SignInPageFactory {
	
	public static SignInPageDAO getCommonImpl() {
		return new SignInPageCommon();
	}
	
	public static SignInPageDAO getVolunteerSignInImpl() {
		return new SignInPageVolunteerSignIn();
	}
	
	public static SignInPageDAO getRaffleImpl() {
		return new SignInPageRaffle();
	}
	
	public static SignInPageDAO getVolunteerSignOutImpl() {
		return new SignInPageVolunteerSignOut();
	}
	
	public static SignInPageDAO getCustomerCheckOutImpl() {
		return new SignInPageCustomerCheckout();
	}
	
	public static SignInPageDAO getAdminPageSignInImpl() {
		return new SignInPageAdminPageSignIn();
	}
}
