package com.billings.jdbc.sql;

public class SignInPageSQL {
	
	private static final String findEmployeeByX = "SELECT DISTINCT customer.Person_Id AS personId, concat(customer.Last_Name, ' ' , customer.First_Name) AS customerName, "+
		"   	customer.Cell_Phone AS cellPhone, "+
		"		if (customer.Dependent_Status='Y', concat(sponsor.Last_Name, ' ', sponsor.First_Name), '') AS sponsorName "+
		"	FROM person customer, person sponsor, person_link link "+
		"	WHERE (%s) "+
		"   	AND if (customer.Dependent_Status='Y', link.Dependent_Id = customer.Person_Id, link.Sponsor_Id is not null) "+
		"   	AND if (customer.Dependent_Status='Y', sponsor.Person_Id = link.Sponsor_Id, sponsor.Person_Id = customer.Person_Id)";

	private static final String findByPhone = "customer.Cell_Phone = ? "+
		"	OR customer.Work_Phone = ?";

	private static final String findByName = "LOWER(customer.Last_Name) like ? AND LOWER(customer.First_Name) like ? ";
		
	public static final String findCustomerByPhone = String.format(findEmployeeByX, findByPhone);

	public static final String findCustomerByName = String.format(findEmployeeByX, findByName);
	
	public static final String findCustomerNameById = "SELECT Last_Name, First_Name FROM person WHERE Person_Id = ?";
	
	private static final String findOnlySponsors = "AND customer.Dependent_Status='N'";
	
	public static final String findSponsorByName = findCustomerByName + findOnlySponsors;
	
	public static final String findSponsorByPhone = findCustomerByPhone + findOnlySponsors;
	
	public static final String signIn = "INSERT INTO sign_in_history (Person_Id, Time_In, Time_Out, Role) "+
		"VALUES(?, now(), null, ?)";

	public static final String signOut = "UPDATE sign_in_history "+
		"SET Time_Out = NOW() "+
		"WHERE Person_Id = ? "+
		"	AND Time_Out is null "+
		"   AND Time_In > CURDATE() "+
		"   AND Role = ?";
	    
	public static final String isCustomerSignedIn = "SELECT COUNT(Time_In) as signInCount "+
		"FROM sign_in_history "+
		"WHERE Person_Id = ? "+
		"	AND Role like ? "+
		"	AND Time_Out is null "+
		"   AND Time_In > CURDATE()";
	
	public static final String getCustomerHistory = "SELECT i.Name as Item_Name, r.Name as Raffle_Name, Quantity, Checkout_Date "+
		"FROM checkout_history ch "+
		"	INNER JOIN item i ON i.Item_Id = ch.Item_Id "+
		"   LEFT JOIN raffle_item r ON r.Raffle_Id = ch.Raffle_Id "+
		"WHERE ch.Person_Id = ? "+
		"ORDER BY Checkout_Date DESC";
	
	public static final String isCustomerAnAdmin = "SELECT count(Person_Id) AS count "+
		"FROM admin_accounts "+
		"WHERE Person_Id = ?";
}
