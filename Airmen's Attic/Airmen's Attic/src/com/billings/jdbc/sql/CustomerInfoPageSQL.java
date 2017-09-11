package com.billings.jdbc.sql;

public class CustomerInfoPageSQL {
	
	public static final String saveNewCustomer = "INSERT INTO person ( "+
		"	Person_Id, Last_Name, First_Name, Rank, "+
		"    Dependent_Status, Cell_Phone, Archive_Status) "+
		"VALUES ( "+
		"	NULL, ?, ?, ?, ?, ?, 'Active')";
	
	public static final String countInstancesOfSuppliedPhoneNumbers = "SELECT COUNT(Person_Id) "+
		"FROM person "+
		"WHERE Cell_Phone = ?";
	
	private static final String createLinkTemplate = ("INSERT INTO person_link (Sponsor_Id, Dependent_Id) "+
			"VALUES (%s "+
			"    (SELECT Person_Id "+
			"     FROM person "+
			"     WHERE Cell_Phone = ?))");
	
	
	public static final String lookupCustomerIdByPhoneNumbers = "SELECT Person_Id "+
			"     FROM person "+
			"     WHERE Cell_Phone = ?";
	
	public static final String linkCustomerToSponsorUsingSponsorId = String.format(createLinkTemplate, "?,");

	private static final String sponsorIdLookupFormat = "("+lookupCustomerIdByPhoneNumbers+"), ";
	
	public static final String linkCustomerToSponsorUsingSponsorLookup = String.format(createLinkTemplate, sponsorIdLookupFormat);
	
	public static final String signInByPhoneNumber = "INSERT INTO sign_in_history (Person_Id, Time_In, Time_Out, Role) "+
			"VALUES(( "+
			"		SELECT Person_Id "+
			"        FROM person "+
			"        WHERE Cell_Phone = ? "+
			"	), now(), null, ?)";
	
	public static final String updateCustomer = "UPDATE person "+
			"SET Last_Name = ?, First_Name = ?, Rank = ?, "+
			"	Dependent_Status = ?, Cell_Phone = ? "+
			"WHERE Person_Id = ?";
	
	public static final String updateArchiveStatus = "UPDATE person "+
			"SET Archive_Status = ? "+
			"WHERE Person_Id = ?";
}
