package com.billings.jdbc.sql;

public class NewRaffleItemPageSQL {

	public static final String getRaffleItemTypes = "SELECT Type_Name FROM item_type";
	
	public static final String createNewItemType = "INSERT INTO item_type(Type_Id, Type_Name) "+
			"values(null, ?)";
	
	public static final String saveNewRaffleItem = "INSERT INTO raffle_item (Raffle_Id, Name, Description, Type_Id, Status, Date_In, Date_Raffled, Date_To_Raffle) "+
			"VALUES (null, ?, ?, ( "+
			"	SELECT Type_Id "+
			"    FROM item_type "+
			"    WHERE Type_Name = ?), "+
			"    'In', NOW(), NULL, ?)";
	
	public static final String getRaffleItemId = "SELECT Raffle_Id "+
			"FROM raffle_item "+
			"WHERE Name = ? "+
			"	AND Description = ? "+
			"    AND Type_Id = ( "+
			"		SELECT Type_Id "+
			"        FROM item_type "+
			"        WHERE Type_Name = ?) "+
			"	AND Date_To_Raffle = ? "+
			"   AND Status = 'In'";
}
