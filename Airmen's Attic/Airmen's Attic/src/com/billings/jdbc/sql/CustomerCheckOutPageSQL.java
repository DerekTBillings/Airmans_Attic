package com.billings.jdbc.sql;

public class CustomerCheckOutPageSQL {
	
	public static final String getCheckoutItemList = "SELECT i.Item_Id, i.Name, t.Type_Name AS Type "+
		"FROM item i "+
		"INNER JOIN item_type t ON i.Type_Id = t.Type_Id "+
		"WHERE i.Item_Id in ( "+
		"	SELECT Item_Id "+
		"    FROM checkout_history "+
		"    WHERE Checkout_Date > DATE_SUB(CURRENT_DATE(), INTERVAL ( "+
		"		SELECT Value "+
		"		FROM system_values "+
		"		WHERE Category = 'Item recency days') "+
		"	DAY)) "+
		"ORDER BY t.Type_Name ASC, i.Item_Id DESC";
	
	public static final String insertCheckoutItemHistory = "INSERT INTO checkout_history  "+
		"(Person_Id, Item_Id, Raffle_Id, Quantity, Checkout_Date)  "+
		"VALUES";
	
	public static final String inserCheckoutItemHistoryValues = " (?, ?, null, ?, CURDATE())";
	
	public static final String customerSignOut = "UPDATE sign_in_history "+
		"SET Time_Out = NOW() "+
		"WHERE Person_Id = ? "+
		"	AND Time_in > CURDATE()";
	
	
}
