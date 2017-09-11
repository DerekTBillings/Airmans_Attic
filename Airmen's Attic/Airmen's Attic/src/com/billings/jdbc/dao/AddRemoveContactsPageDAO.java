package com.billings.jdbc.dao;

import java.util.List;

import com.billings.jdbc.dto.Contact;

public interface AddRemoveContactsPageDAO {
	
	public List<Contact> getContacts();
	
	public void createContact(Contact contact);
	
	public void updateContact(Contact contact);
	
}
