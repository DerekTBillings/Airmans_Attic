package com.billings.jdbc.dao;

import java.util.List;

import com.billings.jdbc.dto.ItemType;

public interface EditItemTypesPageDAO {
	
	public List<ItemType> getItemTypes();
	
	public boolean isTypeUsed(int typeId);
	
	public void deleteItemType(int typeId);
	
	public void updateItemType(ItemType type);

	public boolean isTypeNameUnique(int forTypeId, String text);
	
}
