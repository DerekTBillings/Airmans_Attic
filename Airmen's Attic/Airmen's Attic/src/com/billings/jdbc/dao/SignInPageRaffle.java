package com.billings.jdbc.dao;

import com.billings.resources.RafflePageResources;

public class SignInPageRaffle extends SignInPageDAO {

	@Override
	public void signIn(int personId) {
		RafflePageResources.setCustomerId(personId);
	}

}
