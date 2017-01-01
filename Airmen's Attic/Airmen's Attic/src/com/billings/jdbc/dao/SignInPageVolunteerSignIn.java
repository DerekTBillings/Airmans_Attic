package com.billings.jdbc.dao;

import com.billings.jdbc.sql.SignInPageSQL;
import com.billings.resources.SignInPageResources;
import com.billings.utils.Logger;
import com.billings.utils.Messages;
import com.billings.utils.SQLStatementUtils;

public class SignInPageVolunteerSignIn extends SignInPageDAO {

	@Override
	public void signIn(int personId) {
		String role = SignInPageResources.ROLE_VOLUNTEER;
		String message = "";
		
		if (!super.isCustomerSignedIn(personId, role)) {
			SQLStatementUtils.executeQueryWithoutResultSet(
					SignInPageSQL.signIn, personId, role);
			message = String.format(Messages.SUCCESSFUL_SIGN_IN, "volunteer");
		} else {
			message = Messages.VOLUNTEER_ALREADY_SIGNED_IN;
		}
		
		Logger.notifyUser(message);
	}
	
}
