package com.billings.jdbc.dao;

import java.util.List;

import com.billings.jdbc.dto.CheckoutItem;

public interface CheckoutItemInfoPageDAO {
	
	public void createNewItem(CheckoutItem item);
	
	public void createNewItemType(String itemType);
	
	public List<String> getTypeList();

	public int getItemId(CheckoutItem item);
	
	public boolean itemExists(CheckoutItem item);
	
	public boolean isRecentItem(CheckoutItem item);
	
}
