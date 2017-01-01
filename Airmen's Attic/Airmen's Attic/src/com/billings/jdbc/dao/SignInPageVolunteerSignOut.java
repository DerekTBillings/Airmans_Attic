package com.billings.jdbc.dao;

import com.billings.jdbc.sql.SignInPageSQL;
import com.billings.resources.SignInPageResources;
import com.billings.utils.Logger;
import com.billings.utils.Messages;
import com.billings.utils.SQLStatementUtils;

public class SignInPageVolunteerSignOut extends SignInPageDAO {

	@Override
	public void signIn(int personId) {
		String role = SignInPageResources.ROLE_VOLUNTEER;
		
		if (super.isCustomerSignedIn(personId, role)) {
			SQLStatementUtils.executeQueryWithoutResultSet(
				SignInPageSQL.signOut, personId, role);
		
		} else {
			Logger.notifyUser(Messages.VOLUNTEER_NOT_SIGNED_IN);
		}
	}

}
