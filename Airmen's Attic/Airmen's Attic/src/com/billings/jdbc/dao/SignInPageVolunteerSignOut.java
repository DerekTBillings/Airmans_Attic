package com.billings.jdbc.dao;

import com.billings.jdbc.sql.SignInPageSQL;
import static com.billings.resources.SignInPageResources.ROLE_VOLUNTEER;
import com.billings.utils.Logger;
import com.billings.utils.Messages;
import com.billings.utils.SQLStatementUtils;

public class SignInPageVolunteerSignOut extends SignInPageDAO {

	@Override
	public void signIn(int personId) {
		if (super.isCustomerSignedIn(personId, ROLE_VOLUNTEER)) {
			signOut(personId);
		} else {
			Logger.notifyUser(Messages.VOLUNTEER_NOT_SIGNED_IN);
		}
	}

	private void signOut(int personId) {
		String query = SignInPageSQL.signOut;
		
		SQLStatementUtils.executeUpdate(query, personId, ROLE_VOLUNTEER);
	}

}
