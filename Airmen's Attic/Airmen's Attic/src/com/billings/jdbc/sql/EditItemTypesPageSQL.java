package com.billings.jdbc.sql;

public class EditItemTypesPageSQL {
	
	public static String getItemTypes = "select type_id as id, type_name as name "+
			"from item_type "+
			"order by type_name";
	
	public static String isTypeUsed = "select case when count(*) > '0' then 'true' else 'false' end as isUsed "+
			"from item "+
			"where type_id = ?";
	
	public static String deleteType = "delete from item_type where type_id = ?";
	
	public static String updateType = "update item_type "+
			"set type_name = ? "+
			"where type_id = ?";
	
	public static String isTypeNameUnique = "select case when count(*) = '0' then 'true' else 'false' end as isUsed "+
			"from item_type "+
			"where lower(type_name) = lower(?) "+
			"	and type_id != ?";
	
}
