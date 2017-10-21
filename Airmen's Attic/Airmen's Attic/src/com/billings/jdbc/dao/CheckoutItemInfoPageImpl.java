package com.billings.jdbc.dao;

import java.util.List;

import com.billings.jdbc.dto.CheckoutItem;
import com.billings.jdbc.sql.CheckoutItemInfoPageSQL;
import com.billings.utils.SQLStatementUtils;

public class CheckoutItemInfoPageImpl implements CheckoutItemInfoPageDAO {

	@Override
	public void createNewItem(CheckoutItem item) {
		String query = CheckoutItemInfoPageSQL.createNewItem;
		
		SQLStatementUtils.executeInsert(query, item.getItemName(), item.getItemType());		
	}
	
	@Override
	public void createNewItemType(String itemType) {
		String query = CheckoutItemInfoPageSQL.createNewItemType;
		
		SQLStatementUtils.executeInsert(query, itemType);
	}
	
	@Override
	public int getItemId(CheckoutItem item) {		
		String query = CheckoutItemInfoPageSQL.getItemId;
		
		return (Integer)SQLStatementUtils.executeQueryForSingleCell(
				query, Integer.class, item.getItemName(), item.getItemType());
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<String> getTypeList() {
		String query = CheckoutItemInfoPageSQL.getTypeList;
		
		return SQLStatementUtils.executeQuery(query, String.class);
	}

	@Override
	public boolean itemExists(CheckoutItem item) {
		String query = CheckoutItemInfoPageSQL.getItemCount;
		
		int itemCount = (Integer)SQLStatementUtils.executeQueryForSingleCell(
				query, Integer.class, item.getItemName(), item.getItemType());

		return itemCount > 0;
	}

	@Override
	public boolean isRecentItem(CheckoutItem item) {
		String query = CheckoutItemInfoPageSQL.isRecentItem;
		
		int recentItemCount = (Integer)SQLStatementUtils.executeQueryForSingleCell(
				query, Integer.class, item.getItemId());
		
		return recentItemCount > 0;
	}

}
