package com.billings.jdbc.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.billings.jdbc.sql.SignInPageSQL;
import com.billings.main.WindowController;
import com.billings.utils.FXMLFactory;
import com.billings.utils.Logger;
import com.billings.utils.Messages;
import com.billings.utils.SQLStatementUtils;

public class SignInPageAdminPageSignIn extends SignInPageDAO {

	@Override
	public void signIn(int personId) {
		ResultSet results = SQLStatementUtils.executeQueryAndReturnResultSet(
				SignInPageSQL.isCustomerAnAdmin, personId);
		
		boolean isAdminAccount = false;
		
		try {
			results.next();
			
			int idCount = results.getInt("count");
			isAdminAccount = idCount > 0;
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			SQLStatementUtils.closeConnectionsWithResultSet();
		}
		
		if (isAdminAccount) {
			openAdminPage();
		} else {
			notifyUserNotAdmin();
		}
	}
	
	private void openAdminPage() {
		WindowController.createPopupWindow(
			FXMLFactory.getAdminPage());
	}
	
	private void notifyUserNotAdmin() {
		Logger.notifyUser(Messages.notAnAdmin);
	}

}
