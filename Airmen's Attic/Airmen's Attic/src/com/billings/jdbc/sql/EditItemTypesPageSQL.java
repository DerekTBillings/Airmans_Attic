package com.billings.jdbc.sql;

public class EditItemTypesPageSQL {
	
	public static String getItemTypes = "select type_id, type_name "+
			"from item_type "+
			"order by type_name";
	
	public static String isTypeUsed = "select count(*) > '0' "+
			"from item "+
			"where type_id = ?";
	
	public static String deleteType = "delete from item_type where type_id = ?";
	
	public static String updateType = "update item_type "+
			"set type_name = ? "+
			"where type_id = ?";
	
	public static String isTypeNameUnique = "select count(*) = '0' "+
			"from item_type "+
			"where lower(type_name) = lower(?) "+
			"	and type_id != ?";
	
}
