package com.billings.jdbc.sql;

public class RaffleAdminPageSQL {

	public static final String getRaffleItems = "SELECT r.Raffle_Id, r.Name, r.Description, t.Type_Name as type, r.Status, r.Date_In, r.Date_Raffled, r.Date_To_Raffle "+ 
		"FROM raffle_item r "+
		"INNER JOIN item_type t on r.Type_Id = t.Type_Id "+
		"WHERE status = 'In' ";
	
	public static final String getWinnerForRaffleItem = "SELECT Last_Name, First_Name, Cell_Phone "+
		"FROM person p "+
		"INNER JOIN people_in_raffle pir on pir.Person_Id = p.Person_Id "+
		"WHERE Raffle_Id = ? "+
		"	AND Raffle_Status = 'Winner'";
	
	public static String getPendingRaffleItems = getRaffleItems +
		"and Date_Raffled is null";
	
	public static final String getPeopleForRaffleItem = "SELECT p.* "+
		"FROM person p "+
		"INNER JOIN people_in_raffle r ON p.Person_Id = r.Person_Id "+
		"WHERE r.Raffle_Id = ?";
	
	public static String addPersonToRaffleForItem = "INSERT INTO people_in_raffle(Raffle_Id, Person_Id, Raffle_Status) "+
		"VALUES(?, ?, null)";
	
	public static String isPersonInRaffleForItem = "SELECT COUNT(*) "+
		"FROM people_in_raffle "+
		"WHERE Raffle_Id = ? "+
		"	and Person_Id = ?";
	
	private static String updatePersonInRaffleTemplate = "UPDATE people_in_raffle "+
		"SET Raffle_Status = %s "+
		"WHERE Raffle_Id = ? %s";
	
	public static String removeWinnerStatusForRaffleItem = String.format(updatePersonInRaffleTemplate, 
		"NULL", "");
	
	public static String addWinnerStatusForRaffleItem = String.format(updatePersonInRaffleTemplate,
		"'Winner'", "and Person_Id = ?");

	public static String setRaffleDate = "UPDATE raffle_item "+
		"set date_raffled = sysdate() "+
		"where raffle_id = ?";

	public static String isItemRaffled = "SELECT case when date_raffled is not null then 'true' else 'false' end as status "+
			"FROM raffle_item "+
			"WHERE raffle_id = ?";

	public static String setItemAsOut = "UPDATE raffle_item "+
			"SET status = 'Out' "+
			"WHERE raffle_id = ?";
	
}
