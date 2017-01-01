package com.billings.jdbc.dao;

import com.billings.jdbc.sql.SignInPageSQL;
import com.billings.resources.SignInPageResources;
import com.billings.utils.Logger;
import com.billings.utils.Messages;
import com.billings.utils.SQLStatementUtils;

public class SignInPageCommon extends SignInPageDAO {

	@Override
	public void signIn(int personId) {
		String role = SignInPageResources.ROLE_CUSTOMER;
		String message = "";
		
		if (!super.isCustomerSignedIn(personId, role)) {
			SQLStatementUtils.executeQueryWithoutResultSet(
					SignInPageSQL.signIn, 
					personId, role);
			
			message = String.format(Messages.SUCCESSFUL_SIGN_IN, "customer");
		} else {
			message = Messages.CUSTOMER_ALREADY_SIGNED_IN;
		}
		
		Logger.notifyUser(message);
	}

}
