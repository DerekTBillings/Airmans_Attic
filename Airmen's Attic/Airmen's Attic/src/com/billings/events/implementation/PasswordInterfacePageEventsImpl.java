package com.billings.events.implementation;

import com.billings.events.interfaces.PasswordInterfacePageEvents;
import com.billings.jdbc.dao.PasswordInterfaceDAO;
import com.billings.main.WindowController;
import com.billings.resources.PasswordInterfaceResources;

public class PasswordInterfacePageEventsImpl
	implements PasswordInterfacePageEvents {

	@Override
	public boolean submit(String username, String password) {
		PasswordInterfaceDAO dao = PasswordInterfaceResources.getPageDAO();
		boolean isValid = false;
		
		isValid = dao.validateUsernameAndPassword(username, password);
		
		if (isValid) {
			dao.updateSignInHistory(username);
		}
		
		return isValid;
	}

}
