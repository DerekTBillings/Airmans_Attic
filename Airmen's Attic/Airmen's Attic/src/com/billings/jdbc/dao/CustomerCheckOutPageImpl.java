package com.billings.jdbc.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.billings.jdbc.dto.CheckoutItem;
import com.billings.jdbc.sql.CustomerCheckOutPageSQL;
import com.billings.resources.CustomerCheckOutPageResources;
import com.billings.utils.Logger;
import com.billings.utils.SQLStatementUtils;

public class CustomerCheckOutPageImpl implements CustomerCheckOutPageDAO {
	
	List<CheckoutItem> checkoutItemList;
	
	@Override
	public List<CheckoutItem> getCheckoutItemList() {
		
		ResultSet results = getCheckoutItemsResultSet();
		
		List<CheckoutItem> checkoutItemList = createItemList(results);
		
		return checkoutItemList;
	}
	
	private ResultSet getCheckoutItemsResultSet() {
		return SQLStatementUtils.executeQueryAndReturnResultSet(
			 CustomerCheckOutPageSQL.getCheckoutItemList);
	}
	
	private List<CheckoutItem> createItemList(ResultSet results) {
		List<CheckoutItem> itemList = null;
		
		try {
			itemList = buildItemList(results);
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			SQLStatementUtils.closeConnectionsWithResultSet();
		}
		
		return itemList;
	}
	
	private List<CheckoutItem> buildItemList(ResultSet results) throws SQLException {
		List<CheckoutItem> itemList = new ArrayList<CheckoutItem>(); 
		
		while (results.next()) {
			CheckoutItem item = buildItem(results);
			itemList.add(item);
		}
		
		return itemList;
	}
	
	private CheckoutItem buildItem(ResultSet results) throws SQLException {
		CheckoutItem item = new CheckoutItem();
		
		int itemId = results.getInt("Item_Id");
		String name = results.getString("Name");
		String type = results.getString("Type");
		
		item.setItemId(itemId);
		item.setItemName(name);
		item.setItemType(type);
		
		return item;
	}
	
	@Override
	public void checkout(List<CheckoutItem> checkoutItems, int personId) {
		
		checkoutCustomerItems(checkoutItems, personId);
		
		signCustomerOut(personId);
		
	}

	private void checkoutCustomerItems(List<CheckoutItem> checkoutItems,
			int personId) {
		List<CheckoutItem> checkedOutItems = filterCheckoutItemList(checkoutItems);
		
		int listSize = checkedOutItems.size();
		
		if (listSize > 0) {
			String query = createCheckoutQueryForSize(listSize);
			
			Object[] parameters = buildParameterList(checkedOutItems, personId, listSize);
			
			SQLStatementUtils.executeQueryWithoutResultSet(query, parameters);
		}
	}

	private List<CheckoutItem> filterCheckoutItemList(
			List<CheckoutItem> fullItemList) {
		
		List<CheckoutItem> selectedItems = new ArrayList<CheckoutItem>();
		
		for (CheckoutItem item : fullItemList) {
			if (item.getQuantity() > 0) {
				selectedItems.add(item);
			}
		}
		
		return selectedItems;
	}
	
	private String createCheckoutQueryForSize(int listSize) {
		StringBuffer query = new StringBuffer();
		
		query.append(CustomerCheckOutPageSQL.insertCheckoutItemHistory);
		
		boolean first = true;
		
		for (int i=0; i<listSize; i++) {
			if (!first) {
				query.append(",");
			} else {
				first = false;
			}
			query.append(CustomerCheckOutPageSQL.inserCheckoutItemHistoryValues);
		}
		
		return query.toString();
	}

	private Object[] buildParameterList(List<CheckoutItem> checkedOutItems,
			int userId, int listSize) {
		//There are 3 expected parameters so to get the total expected parameters
		//multiply the listSize by 3
		Object[] parameterList = new Object[listSize*3];
		int index = 0;
		
		for (int i=0; i<listSize; i++) {
			CheckoutItem item = checkedOutItems.get(i);
			
			parameterList[index] = userId;
			parameterList[index+1] = item.getItemId();
			parameterList[index+2] = item.getQuantity();
			
			index += 3;
		}
		
		return parameterList;
	}
	
	private void signCustomerOut(int personId) {
		SQLStatementUtils.executeQueryWithoutResultSet(
				CustomerCheckOutPageSQL.customerSignOut, personId);
	}
}
