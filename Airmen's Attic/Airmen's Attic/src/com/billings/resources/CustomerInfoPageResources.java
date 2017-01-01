package com.billings.resources;

import java.util.Arrays;
import java.util.List;

import com.billings.events.implementation.CustomerInfoPageEditCustomer;
import com.billings.events.implementation.CustomerInfoPageEditSponsor;
import com.billings.events.implementation.CustomerInfoPageNewCustomer;
import com.billings.events.interfaces.CustomerInfoPageEvents;
import com.billings.jdbc.dao.CustomerInfoPageDAO;
import com.billings.jdbc.dao.CustomerInfoPageNewCustomerAndSponsor;
import com.billings.jdbc.dao.CustomerInfoPageSetupDAO;
import com.billings.jdbc.dao.CustomerInfoPageSetupImpl;
import com.billings.jdbc.factory.CustomerInfoPageFactory;

public class CustomerInfoPageResources {

	public static String NEW_CONTACT_HEADER;
	public static String FIRST_NAME_LBL;
	public static String LAST_NAME_LBL;
	public static String RANK_LBL;
	public static String DOB_LBL;
	public static String WORK_PHONE_LBL;
	public static String CELL_PHONE_LBL;
	public static String ORGANIZATION_LBL;
	public static String DEPENDENT_CHECK_LBL;
	public static String MILITARY_ID_EXP_DATE_LBL;
	public static String EMAIL_LBL;
	public static String SUBMIT_BTN;
	public static String CANCEL_BTN;
	public static String EDIT_SPONSOR_INFO_BTN;
	public static String DELETE_CUSTOMER_BTN;
	public static String SIGN_IN_AS_X;
	public static String DATE_PROMPT_TEXT;
	
	public static boolean REMOVE_DELTE_BTN;
	public static boolean REMOVE_EDIT_SPONSOR_BTN;
	public static boolean REMOVE_DEPENDENT_CHECK;
	public static boolean LOAD_PERSON;
	
	public static List<String> RANK_LIST;
	public static List<String> REQUIRED_FIELDS;
	
	public static CustomerInfoPageEvents CUSTOMER_INFO_PAGE_EVENTS;
	
	public static CustomerInfoPageDAO CUSTOMER_DAO;
	
	
	public static void setupWithNewContactInfo() {
		setupWithCommonValues();
		CUSTOMER_INFO_PAGE_EVENTS = new CustomerInfoPageNewCustomer();
		NEW_CONTACT_HEADER = "New Contact";
		SIGN_IN_AS_X = "Customer";
		DELETE_CUSTOMER_BTN = "";
		REMOVE_DELTE_BTN = true;
		REMOVE_EDIT_SPONSOR_BTN = false;
		REMOVE_DEPENDENT_CHECK = false;
		LOAD_PERSON = false;
		CUSTOMER_DAO = CustomerInfoPageFactory.getNewCustomerImpl();
	}
	
	private static void setupWithCommonValues() {
		FIRST_NAME_LBL = "First Name:";
		LAST_NAME_LBL = "Last Name:";
		RANK_LBL = "Rank:";
		DOB_LBL = "Date of Birth:";
		WORK_PHONE_LBL = "Work Phone:";
		CELL_PHONE_LBL = "Cell Phone:";
		ORGANIZATION_LBL = "Organization/Unit: ";
		DEPENDENT_CHECK_LBL = "Check if Dependent:";
		MILITARY_ID_EXP_DATE_LBL = "Mil. Id Exp. Date";
		EMAIL_LBL = "Email:";
		SUBMIT_BTN = "Submit";
		CANCEL_BTN = "Cancel";
		EDIT_SPONSOR_INFO_BTN = "Edit Sponsor";
		DATE_PROMPT_TEXT = "MM/DD/YYYY";
		
		if (RANK_LIST == null) {
			setupRankList();
		}
		
		if (REQUIRED_FIELDS == null) {
			setupRequiredFieldsList();
		}
	}
	
	private static void setupRankList() {
		CustomerInfoPageSetupDAO dao = new CustomerInfoPageSetupImpl();
		
		RANK_LIST = dao.getRankList();
	}
	
	private static void setupRequiredFieldsList() {
		CustomerInfoPageSetupDAO dao = new CustomerInfoPageSetupImpl();
		
		REQUIRED_FIELDS = dao.getRequiredFields();
	}
	
	public static void setupWithNewVolunteerInfo() {
		setupWithNewContactInfo();
		SIGN_IN_AS_X = "Volunteer";
	}
	
	public static void setupWithEditSponsorInfo() {
		setupWithCommonValues();
		CUSTOMER_INFO_PAGE_EVENTS = new CustomerInfoPageEditSponsor();
		NEW_CONTACT_HEADER = "Edit Sponsor";
		DELETE_CUSTOMER_BTN = "";
		REMOVE_DELTE_BTN = true;
		REMOVE_EDIT_SPONSOR_BTN = true;
		REMOVE_DEPENDENT_CHECK = true;
		LOAD_PERSON = false;
		CUSTOMER_DAO = CustomerInfoPageFactory.getNewCustomerAndSponsorImpl();
	}
	
	public static void setupWithEditCustomer() {
		setupWithCommonValues();
		CUSTOMER_INFO_PAGE_EVENTS = new CustomerInfoPageEditCustomer();
		NEW_CONTACT_HEADER = "";
		DELETE_CUSTOMER_BTN = "Delete Customer";
		REMOVE_DELTE_BTN = false;
		REMOVE_EDIT_SPONSOR_BTN = true;
		REMOVE_DEPENDENT_CHECK = true;
		LOAD_PERSON = true;
	}
	
	public static void setupFoundSponsorId() {
		CUSTOMER_DAO = CustomerInfoPageFactory.getNewCustoemrWithSponsorIdImpl();
	}
	
}
