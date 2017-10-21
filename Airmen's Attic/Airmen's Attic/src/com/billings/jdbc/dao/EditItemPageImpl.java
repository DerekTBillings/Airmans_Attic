package com.billings.jdbc.dao;

import java.util.List;

import com.billings.jdbc.dto.CheckoutItem;
import com.billings.jdbc.sql.EditItemPageSQL;
import com.billings.utils.SQLStatementUtils;

public class EditItemPageImpl implements EditItemPageDAO {

	@Override
	public List<CheckoutItem> getItems() {
		String query = EditItemPageSQL.getItems;
		
		return SQLStatementUtils.executeQuery(query, CheckoutItem.class);
	}

	@Override
	public void updateItem(CheckoutItem item) {
		
	}

	@Override
	public List<String> getItemTypes() {
		String query = EditItemPageSQL.getItemTypes;
		
		return SQLStatementUtils.executeQuery(query, String.class);
	}

	@Override
	public boolean saveChanges(CheckoutItem selectedItem) {
		String query = EditItemPageSQL.saveItemChanges;

		SQLStatementUtils.executeUpdate(query,
				selectedItem.getItemName(), selectedItem.getItemType(),
				getArchiveValue(selectedItem), selectedItem.getItemId());
		
		return true;
	}

	private Object getArchiveValue(CheckoutItem selectedItem) {
		return (selectedItem.isArchived()) ? "Y" : "N";
	}

	@Override
	public void addItemType(String itemType) {
		int count = getTypeInstanceCount(itemType);
		
		if (count == 0)
			saveItemType(itemType);
	}

	private int getTypeInstanceCount(String itemType) {
		String query = EditItemPageSQL.getTypeInstanceCount;
		
		return (Integer)SQLStatementUtils.executeQueryForSingleCell(query, Integer.class, itemType);
	}
	
	private void saveItemType(String itemType) {
		String query = EditItemPageSQL.saveItemType;
		
		SQLStatementUtils.executeInsert(query, itemType);
	}

}

