package com.billings.jdbc.dao;

public interface PasswordInterfaceDAO {
	
	public boolean validateUsernameAndPassword(String username, String password);
	
	public void updateSignInHistory(String username);
}
