package com.billings.resources;

import javafx.scene.control.TextArea;

public class WelcomePageResources {

	public static final String WELCOME_PAGE_HEADER = "Welcome to the Airmen's Attic";
	public static final String CUSTOMER_SIGN_IN_BTN_TEXT = "Customer Sign In";
	public static final String CHECK_OUT_BTN_TEXT = "Check Out";
	public static final String VOLUNTEER_BTN_TEXT = "Volunteer Sign In";
	public static final String RAFFLE_BTN_TEXT = "Raffle";
	public static final String KEY_HOLDER_BTN_TEXT = "Key Holder Sign In";
	public static final String ADMIN_PAGE_BTN_TEXT = "Admin Page";
	public static final String VOLUNTEER_SIGN_OUT_BTN_TEXT = "Vol. Sign Out";
	
	private static TextArea message;
	
	public static void bindMessageTextArea(TextArea textArea) {
		message = textArea;
	}
	
	public static void setMessage(String text) {
		if (message != null)
			message.setText(text);
	}
	
}
