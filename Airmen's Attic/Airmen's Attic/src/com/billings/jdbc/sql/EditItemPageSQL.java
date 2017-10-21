package com.billings.jdbc.sql;

public class EditItemPageSQL {
	
	public static String getItems = "select item_id, name as item_name, type_name as item_type, is_archived = 'Y' as archived "+
			"from item i "+
			"	inner join item_type t on i.type_id = t.type_id";
	
	public static String getItemTypes = "select type_name from item_type order by type_name";
	
	public static String saveItemChanges = "update item "+
			"set name = ?, "+
			"	type_id = (select type_id from item_type where type_name = ?), "+
			"    is_archived = ? "+
			"where item_id = ?";
	
	public static String getTypeInstanceCount = "select count(*) "+
			"from item_type "+
			"where type_name = ?";

	public static String saveItemType = "insert into item_type(type_id, type_name) "+
			"values(null, ?)";
}
