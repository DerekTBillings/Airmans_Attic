package com.billings.resources;

import com.billings.events.implementation.SignInPageEventsAdminPageSignIn;
import com.billings.events.implementation.SignInPageEventsCommon;
import com.billings.events.implementation.SignInPageEventsCustomerCheckOut;
import com.billings.events.implementation.SignInPageEventsCustomerLookup;
import com.billings.events.implementation.SignInPageEventsRaffle;
import com.billings.events.implementation.SignInPageEventsSignOut;
import com.billings.events.implementation.SignInPageEventsSponsorLookup;
import com.billings.events.implementation.SignInPageEventsVolunteerSignIn;
import com.billings.events.interfaces.SignInPageEvents;

public class SignInPageResources {
	
	public static String CUSTOMER_LOGIN_LABEL;
	public static String PHONE_NUMBER_LABEL;
	public static String CUSTOMER_NAME_LABEL;
	public static String CUSTOMER_NAME_INITIAL_TEXT;
	public static String LOGIN_BTN;
	public static String NEW_CUSTOMER_BTN;
	public static String CANCEL_BTN;
	public static boolean DISABLE_NEW_BUTTON;
	public static SignInPageEvents SIGN_IN_PAGE_EVENTS;
	
	public static final String ROLE_VOLUNTEER = "Volunteer";
	
	public static final String ROLE_CUSTOMER = "Customer";
	
	public static final String ROLE_EITHER = "%";
	
	public static void setupWithCustomerSignIn() {
		setupWithCommonLogin();
		setupWithCommonEvents();
		CUSTOMER_LOGIN_LABEL = "Customer Login";
	}
	
	public static void setupWithVolunteerSignIn() {
		setupWithCommonLogin();
		SIGN_IN_PAGE_EVENTS = new SignInPageEventsVolunteerSignIn();
		CUSTOMER_LOGIN_LABEL = "Volunteer Login";
	}
	
	private static void setupWithCommonLogin() {
		setupWithCommonValues();
		DISABLE_NEW_BUTTON = false;
		LOGIN_BTN = "Login";
	}
	
	private static void setupWithCommonValues() {
		PHONE_NUMBER_LABEL = "Phone:";
		CUSTOMER_NAME_LABEL = "Name:";
		CUSTOMER_NAME_INITIAL_TEXT = "Last, First";
		NEW_CUSTOMER_BTN = "New";
		CANCEL_BTN = "Cancel";
	}
	
	private static void setupWithCommonEvents() {
		SIGN_IN_PAGE_EVENTS = new SignInPageEventsCommon();
	}
	
	public static void setupWithVolunteerSignOut() {
		setupWithCommonValues();
		SIGN_IN_PAGE_EVENTS = new SignInPageEventsSignOut();
		CUSTOMER_LOGIN_LABEL = "Volunteer Logout";
		LOGIN_BTN = "Logout";
		DISABLE_NEW_BUTTON = true;
	}
	
	public static void setupWithRaffleSignIn() {
		setupWithCommonValues();
		SIGN_IN_PAGE_EVENTS = new SignInPageEventsRaffle();
		CUSTOMER_LOGIN_LABEL = "Sign In To Raffle";
		LOGIN_BTN = "Next";
		DISABLE_NEW_BUTTON = true;
	}
	
	public static void setupWithCustomerLookup() {
		setupWithCommonValues();
		SIGN_IN_PAGE_EVENTS = new SignInPageEventsCustomerLookup();
		CUSTOMER_LOGIN_LABEL = "Customer Lookup";
		LOGIN_BTN = "Find";
		DISABLE_NEW_BUTTON = true;
	}
	
	public static void setupWithSponsorLookup() {
		setupWithCommonValues();
		SIGN_IN_PAGE_EVENTS = new SignInPageEventsSponsorLookup();
		CUSTOMER_LOGIN_LABEL = "Sponsor Lookup";
		LOGIN_BTN = "Find";
		DISABLE_NEW_BUTTON = false;
	}
	
	public static void setupWithCustomerCheckout() {
		setupWithCommonValues();
		SIGN_IN_PAGE_EVENTS = new SignInPageEventsCustomerCheckOut();
		CUSTOMER_LOGIN_LABEL = "Customer Lookup";
		LOGIN_BTN = "Next";
		DISABLE_NEW_BUTTON = true;
	}
	
	public static void setupWithAdminPageSignIn() {
		setupWithCommonValues();
		SIGN_IN_PAGE_EVENTS = new SignInPageEventsAdminPageSignIn();
		CUSTOMER_LOGIN_LABEL = "Admin Login";
		LOGIN_BTN = "Login";
		DISABLE_NEW_BUTTON = true;
	}
}
