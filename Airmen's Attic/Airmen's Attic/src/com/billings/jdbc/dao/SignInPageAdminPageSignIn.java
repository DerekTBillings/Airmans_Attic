package com.billings.jdbc.dao;

import com.billings.jdbc.sql.SignInPageSQL;
import com.billings.main.WindowController;
import com.billings.utils.FXMLFactory;
import com.billings.utils.Logger;
import com.billings.utils.Messages;
import com.billings.utils.SQLStatementUtils;

public class SignInPageAdminPageSignIn extends SignInPageDAO {

	@Override
	public void signIn(int personId) {		
		if (isAdminAccount(personId)) {
			openAdminPage();
		} else {
			notifyUserNotAdmin();
		}
	}

	private boolean isAdminAccount(int personId) {
		String query = SignInPageSQL.isCustomerAnAdmin;
		
		int count = (Integer)SQLStatementUtils.executeQueryForSingleCell(
				query, Integer.class, personId);
		
		return count > 0;
	}
	
	private void openAdminPage() {
		WindowController.createPopupWindow(
			FXMLFactory.getAdminPage());
	}
	
	private void notifyUserNotAdmin() {
		Logger.notifyUser(Messages.notAnAdmin);
	}

}
