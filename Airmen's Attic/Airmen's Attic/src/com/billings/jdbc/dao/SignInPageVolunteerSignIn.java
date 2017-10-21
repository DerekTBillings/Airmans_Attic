package com.billings.jdbc.dao;

import com.billings.jdbc.sql.SignInPageSQL;
import static com.billings.resources.SignInPageResources.ROLE_VOLUNTEER;
import com.billings.utils.Logger;
import com.billings.utils.Messages;
import com.billings.utils.SQLStatementUtils;

public class SignInPageVolunteerSignIn extends SignInPageDAO {

	@Override
	public void signIn(int personId) {
		if (!super.isCustomerSignedIn(personId, ROLE_VOLUNTEER)) {
			signInAsVolunteer(personId);
		} else {
			notify(Messages.VOLUNTEER_ALREADY_SIGNED_IN);
		}
	}

	private void signInAsVolunteer(int personId) {
		String query = SignInPageSQL.signIn;
		
		signVolunteerIn(personId, query);
		
		String name = getVolunteerName(personId);
		
		notify(String.format(Messages.SUCCESSFUL_SIGN_IN, name, "volunteer"));
	}

	private void signVolunteerIn(int personId, String query) {
		SQLStatementUtils.executeInsert(query, personId, ROLE_VOLUNTEER);
	}

	private String getVolunteerName(int personId) {
		String query = SignInPageSQL.getCustomerName;

		return (String)SQLStatementUtils.executeQueryForSingleCell(
				query, String.class, personId);
	}

	private void notify(String msg) {
		Logger.notifyUser(msg);
	}
	
}
