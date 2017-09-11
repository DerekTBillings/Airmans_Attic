package com.billings.jdbc.dao;

import java.util.List;

import com.billings.jdbc.dto.CheckoutItem;

public interface EditItemPageDAO {
	
	public List<CheckoutItem> getItems();
	
	public void updateItem(CheckoutItem item);

	public List<String> getItemTypes();

	public boolean saveChanges(CheckoutItem selectedItem);

	public void addItemType(String itemType);
	
}
