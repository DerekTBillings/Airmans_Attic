package com.billings.resources;

import com.billings.events.implementation.PasswordInterfacePageEventsImpl;
import com.billings.jdbc.dao.PasswordInterfaceDAOAdminImpl;
import com.billings.jdbc.dao.PasswordInterfaceDAO;
import com.billings.jdbc.dao.PasswordInterfaceDAOKeyHolderImpl;

public class PasswordInterfaceResources {

	public static String HEADER_LBL = "Please Enter Password";
	public static String USERNAME_PROMPT = "Last Name Only";
	public static String PASSWORD_PROMPT = "Password";
	public static String SUBMIT_BTN = "Submit";
	public static String CANCEL_BTN = "Cancel";
	public static String USERNAME_LBL = "Username";
	public static String PASSWORD_LBL = "Password";
	
	private static PasswordInterfacePageEventsImpl pageEvents;
	private static PasswordInterfaceDAO pageDAO;
	
	private static String pageRedirect = "";
	
	public static boolean doPageRedirect = false;
	public static boolean doAnnotateResults = false;
	
	public static PasswordInterfacePageEventsImpl getPageEvents() {
		if (pageEvents == null) {
			pageEvents = new PasswordInterfacePageEventsImpl();
		}
		
		return pageEvents;
	}
	
	public static PasswordInterfaceDAO getPageDAO() {
		if (pageDAO == null) {
			pageDAO = new PasswordInterfaceDAOKeyHolderImpl();
		}
		
		return pageDAO;
	}
	
	public static void setupPageForAdmins() { 
		pageDAO = new PasswordInterfaceDAOAdminImpl();
		doPageRedirect = true;
		doAnnotateResults = false;
	}
	
	public static void setPageRedirect(String path) {
		pageRedirect = path;
	}
	
	public static String getPageRedirect() {
		return pageRedirect;
	}

	public static void overridePageRedirect(boolean doRedirect) {
		doPageRedirect = doRedirect;
	}
}
