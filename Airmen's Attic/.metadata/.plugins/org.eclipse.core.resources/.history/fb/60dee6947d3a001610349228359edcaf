package com.billings.jdbc.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.billings.jdbc.dto.CheckoutItem;
import com.billings.jdbc.sql.CheckoutItemInfoPageSQL;
import com.billings.jdbc.sql.CustomerCheckOutPageSQL;
import com.billings.utils.Logger;
import com.billings.utils.SQLStatementUtils;

public class CheckoutItemInfoPageImpl implements CheckoutItemInfoPageDAO {

	@Override
	public void createNewItem(CheckoutItem item) {
		String itemType = item.getItemType();
		String itemName = item.getItemName();
		
		SQLStatementUtils.executeQueryWithoutResultSet(
				CheckoutItemInfoPageSQL.createNewItem, 
				itemName, itemType);
		
	}
	
	@Override
	public void createNewItemType(String itemType) {
		SQLStatementUtils.executeQueryWithoutResultSet(
				CheckoutItemInfoPageSQL.createNewItemType, itemType);
	}
	
	@Override
	public int getItemId(CheckoutItem item) {
		int itemId = 0;
		
		ResultSet results = SQLStatementUtils.executeQueryAndReturnResultSet(
				CheckoutItemInfoPageSQL.getItemId,
				item.getItemName(), item.getItemType());
		
		try {
			results.next();
			itemId = results.getInt("Item_Id");
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			SQLStatementUtils.closeConnectionsWithResultSet();
		}
		
		return itemId;
	}

	@Override
	public List<String> getTypeList() {
		List<String> typeList = null;
		ResultSet results = SQLStatementUtils.executeQueryAndReturnResultSet(
				CheckoutItemInfoPageSQL.getTypeList);
		
		try {
			typeList = buildTypeList(results);
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			SQLStatementUtils.closeConnectionsWithResultSet();
		}
		
		return typeList;
	}
	
	private List<String> buildTypeList(ResultSet results) throws SQLException {
		List<String> typeList = new ArrayList<String>();
		
		while(results.next()) {
			String type = results.getString("Type_Name");
			typeList.add(type);
		}
		
		return typeList;
	}

	@Override
	public boolean itemExists(CheckoutItem item) {
		boolean itemExists = false;
		
		String itemName = item.getItemName();
		String itemType = item.getItemType();
		String query = CheckoutItemInfoPageSQL.getItemCount;
		
		ResultSet results = SQLStatementUtils.executeQueryAndReturnResultSet(
				query, itemName, itemType);
		
		int itemCount = getItemCount(results);
		
		itemExists = (itemCount > 0);
		
		return itemExists;
	}
	
	private int getItemCount(ResultSet results) {
		int itemCount = 0;
		
		try {
			results.next();
			itemCount = results.getInt(1);
			
		} catch(Exception e) {
			Logger.log(e.getMessage());
			
		} finally {
			SQLStatementUtils.closeConnectionsWithResultSet();
		}
		
		return itemCount;
	}

	@Override
	public boolean isRecentItem(CheckoutItem item) {
		boolean isRecentItem = false;
		
		int itemId = item.getItemId();
		String query = CheckoutItemInfoPageSQL.isRecentItem;
		
		ResultSet results = SQLStatementUtils.executeQueryAndReturnResultSet(
			query, itemId);
		
		int recentItemCount = getRecentItemCount(results);
		
		isRecentItem = (recentItemCount > 0);
		
		return isRecentItem;
	}
	
	private int getRecentItemCount(ResultSet results) {
		int itemsInResults = 0;
		
		try {
			results.next();
			itemsInResults = results.getInt(1);
			
		} catch (Exception e) {
			Logger.log(e.getMessage());
		
		} finally {
			SQLStatementUtils.closeConnectionsWithResultSet();
		}
		
		return itemsInResults;
	}

}
