package com.billings.jdbc.sql;

public class SignInPageSQL {
	
	private static final String findEmployeeByX = "SELECT DISTINCT customer.Person_Id AS personId, concat(customer.Last_Name, ' ' , customer.First_Name) AS customerName, "+
		"   	customer.Cell_Phone AS contactInfo, "+
		"		if (customer.Dependent_Status='Y', concat(sponsor.Last_Name, ' ', sponsor.First_Name), '') AS sponsorName "+
		"	FROM person customer, person sponsor, person_link link "+
		"	WHERE (%s %s) "+
		"   	AND if (customer.Dependent_Status='Y', link.Dependent_Id = customer.Person_Id, link.Sponsor_Id is not null) "+
		"   	AND if (customer.Dependent_Status='Y', sponsor.Person_Id = link.Sponsor_Id, sponsor.Person_Id = customer.Person_Id)";

	private static final String findByPhone = "customer.Cell_Phone = ?";

	private static final String findByName = "LOWER(customer.Last_Name) like ? AND LOWER(customer.First_Name) like ? ";
	
	private static final String findByActiveStatus = "AND customer.Archive_Status = 'Active'";
		
	public static final String findActiveCustomerByPhone = String.format(findEmployeeByX, findByPhone, findByActiveStatus);
	public static final String findCustomerByPhone = String.format(findEmployeeByX, findByPhone, "");

	public static final String findActiveCustomerByName = String.format(findEmployeeByX, findByName, findByActiveStatus);
	public static final String findCustomerByName = String.format(findEmployeeByX, findByName, "");
	
	public static final String findCustomerNameById = "SELECT concat(first_name, ' ', last_name, ' (', rank, ')') FROM person WHERE Person_Id = ?";
	
	private static final String findOnlySponsors = "AND customer.Dependent_Status='N'";
	
	public static final String findSponsorByName = findActiveCustomerByName + findOnlySponsors;
	
	public static final String findSponsorByPhone = findActiveCustomerByPhone + findOnlySponsors;
	
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
	
	public static final String getCustomerHistory = "select item_name, quantity, substr(checkout_date, 1, instr(checkout_date, ' ')-1) as SQLDate "+
			"from ( "+
			"		select name as item_name, quantity, checkout_date, person_id "+
			"		from checkout_history ch "+
			"			inner join item i on ch.item_id = i.item_id "+
			"		union "+
			"		select name as item_name, '1', date_raffled, person_id "+
			"		from raffle_item ri "+
			"			inner join people_in_raffle pir on ri.raffle_id = pir.raffle_id) results "+
			"where person_id = ?";
	
	
	public static final String isCustomerAnAdmin = "SELECT count(Person_Id) AS count "+
		"FROM admin_accounts "+
		"WHERE Person_Id = ?";
	
	public static final String getCustomerName = "select concat(first_name, ' ', last_name) "+
			"from person "+
			"where person_id = ?";
}
