package com.billings.jdbc.dao;

public interface AdminManagementDAO {
	
	public void makeAdmin(int personId, String username, String password);
	
	public void removeAdmin(int personId);
	
	public boolean isUsernameAvailable(String username);
	
}
