package com.billings.resources;

import com.billings.jdbc.dto.Person;

public class AdminManagementResources {

	public static final String HEADER_LBL = "Admin Credentials";
	public static final String USERNAME_LBL = "Username:";
	public static final String PASSWORD_LBL = "Password:";
	public static final String CONFIRMATION_PASSWORD_LBL = "Confirm Password:";
	public static final String SAVE_BTN = "Save";
	public static final String CANCEL_BTN = "Cancel";
	
	public static final String INVALID_PASSWORD = "Your password was invalid.\r\n"+
			"A valid password must have at least: 8 characters, 1 number, and 1 letter.";
	
	public static final String PASSWORDS_MUST_MATCH = "Your passwords didn't match.";
	
	public static final String ADMIN_PRIVILEGES_REMOVED = "Admin privileges removed.";
	
	public static final String ADMIN_PRIVILEGES_GRANTED = "Admin privileges established.";
	
	private static Person person;
	

	public static int getPersonId() {
		return person.getPersonId();
	}

	public static void setPerson(Person person) {
		AdminManagementResources.person = person;
	}
	
	public static void setAdminStatus(boolean status) {
		person.setAdmin(status);
	}
}
