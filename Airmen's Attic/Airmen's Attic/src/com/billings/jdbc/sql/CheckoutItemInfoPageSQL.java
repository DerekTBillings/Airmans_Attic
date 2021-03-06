package com.billings.jdbc.sql;

public class CheckoutItemInfoPageSQL {

	public static final String getTypeList = "SELECT Type_Name FROM item_type ORDER BY Type_Name";
	
	public static final String createNewItemType = "INSERT INTO item_type(Type_Id, Type_Name) "+
		"values(null, ?)";
	
	public static final String createNewItem = "INSERT INTO item (Item_Id, Name, Type_Id) "+
		"VALUES (null, ?, ( "+
		"	SELECT Type_Id "+
		"    FROM item_type "+
		"    WHERE Type_Name = ? "+
		"))";
	
	public static final String getItemId = "SELECT Item_Id  "+
		"FROM item "+
		"WHERE Name = ? "+
		"	AND Type_Id = ( "+
		"		SELECT Type_Id "+
		"       FROM item_type "+
		"        WHERE Type_Name = ?)";
	
	public static final String getItemCount = "SELECT COUNT(i.Item_Id) "+
		"FROM item i "+
		"INNER JOIN item_type t ON i.Type_Id = t.Type_Id "+
		"WHERE UPPER(i.Name) = UPPER(?) "+
		"	AND UPPER(t.Type_Name) = UPPER(?)";
	
	public static final String isRecentItem = "SELECT COUNT(Item_Id) "+
		"FROM item "+
		"WHERE Item_Id = ? "+
		"	AND Item_Id IN ( "+
		"		SELECT Item_Id "+
		"        FROM checkout_history "+
		"        WHERE Checkout_Date > DATE_SUB(CURRENT_DATE(), INTERVAL ( "+
		"			SELECT Value "+
		"            FROM system_values "+
		"            WHERE Category = 'Item recency days') Day))";
	
}
