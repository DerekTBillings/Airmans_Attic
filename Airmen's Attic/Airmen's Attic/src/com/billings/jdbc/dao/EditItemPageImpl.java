package com.billings.jdbc.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.billings.jdbc.dto.CheckoutItem;
import com.billings.jdbc.sql.EditItemPageSQL;
import com.billings.utils.Logger;
import com.billings.utils.SQLStatementUtils;

public class EditItemPageImpl implements EditItemPageDAO {

	@Override
	public List<CheckoutItem> getItems() {
		String query = EditItemPageSQL.getItems;
		
		ResultSet rs = SQLStatementUtils.executeQueryAndReturnResultSet(query);
		
		List<CheckoutItem> items = tryGetItemsFromResults(rs);
		
		SQLStatementUtils.closeConnectionsWithResultSet();
		
		return items;
	}
	
	private List<CheckoutItem> tryGetItemsFromResults(ResultSet rs) {
		try {
			return getItemsFromResults(rs);
		} catch(Exception e) {
			Logger.log(e.getMessage());
		}
		
		return new ArrayList<CheckoutItem>();
	}

	private List<CheckoutItem> getItemsFromResults(ResultSet rs) throws Exception {
		List<CheckoutItem> items = new ArrayList<CheckoutItem>();
		
		while (rs.next()) {
			CheckoutItem item = new CheckoutItem();
			
			item.setItemId(rs.getInt("item_id"));
			item.setItemName(rs.getString("name"));
			item.setItemType(rs.getString("type_name"));
			item.setArchived(rs.getBoolean("archived"));
			
			items.add(item);
		}
		
		return items;
	}

	@Override
	public void updateItem(CheckoutItem item) {
		
	}

	@Override
	public List<String> getItemTypes() {
		String query = EditItemPageSQL.getItemTypes;
		
		ResultSet rs = SQLStatementUtils.executeQueryAndReturnResultSet(query);
		
		List<String> itemTypes = tryGetItemTypes(rs);
		
		SQLStatementUtils.closeConnectionsWithResultSet();
		
		return itemTypes;
	}

	private List<String> tryGetItemTypes(ResultSet rs) {
		try {
			return getItemTypes(rs);
		} catch (Exception e) {
			Logger.log(e.getMessage());
		}
		
		return new ArrayList<String>();
	}

	private List<String> getItemTypes(ResultSet rs) throws Exception {
		List<String> types = new ArrayList<String>();
		
		while (rs.next()) 
			types.add(rs.getString("type_name"));
		
		return types;
	}

	@Override
	public boolean saveChanges(CheckoutItem selectedItem) {
		try {
			String query = EditItemPageSQL.saveItemChanges;
			
			SQLStatementUtils.executeQueryWithoutResultSet(
					query, selectedItem.getItemName(), selectedItem.getItemType(),
					getArchiveValue(selectedItem), selectedItem.getItemId());
			
			return true;
		} catch(Exception e) {
			Logger.log(e.getMessage());
			
			return false;
		}
	}

	private Object getArchiveValue(CheckoutItem selectedItem) {
		return (selectedItem.isArchived()) ? "Y" : "N";
	}

	@Override
	public void addItemType(String itemType) {
		int count = getTypeInstanceCount(itemType);
		
		if (count == 0) {
			saveItemType(itemType);
		}
	}

	private int getTypeInstanceCount(String itemType) {
		String query = EditItemPageSQL.getTypeInstanceCount;
		
		ResultSet rs = SQLStatementUtils.executeQueryAndReturnResultSet(query, itemType);
		
		int count = tryGetCountFromResults(rs);
		
		SQLStatementUtils.closeConnectionsWithResultSet();
		
		return count;
	}
	
	private int tryGetCountFromResults(ResultSet rs) {
		try {
			return getCountFromResults(rs);
		} catch(Exception e) {
			Logger.log(e.getMessage());
		}
		
		return 0;
	}

	private int getCountFromResults(ResultSet rs) throws SQLException {
		if (rs.next())
			return rs.getInt(1);
		
		return 0;
	}
	
	private void saveItemType(String itemType) {
		String query = EditItemPageSQL.saveItemType;
		
		SQLStatementUtils.executeQueryWithoutResultSet(query, itemType);
	}

}

