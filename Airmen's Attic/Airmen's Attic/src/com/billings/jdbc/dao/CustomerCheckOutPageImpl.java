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
		String query = CustomerCheckOutPageSQL.getCheckoutItemList;
		
		return SQLStatementUtils.executeQuery(query, CheckoutItem.class);
	}
	
	@Override
	public void checkout(List<CheckoutItem> checkoutItems, int personId) {
		checkoutCustomerItems(checkoutItems, personId);
		
		signCustomerOut(personId);
		
	}

	private void checkoutCustomerItems(List<CheckoutItem> checkoutItems, int personId) {
		List<CheckoutItem> checkedOutItems = filterCheckoutItemList(checkoutItems);
		
		int listSize = checkedOutItems.size();
		
		if (listSize > 0) {
			String query = createCheckoutQueryForSize(listSize);
			
			Object[] parameters = buildParameterList(checkedOutItems, personId, listSize);
			
			SQLStatementUtils.executeInsert(query, parameters);
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
		
		for (int i=0; i<listSize; i++) {
			if (i > 0) 
				query.append(",");

			query.append(CustomerCheckOutPageSQL.insertCheckoutItemHistoryValues);
		}
		
		return query.toString();
	}

	private Object[] buildParameterList(List<CheckoutItem> checkedOutItems,
			int userId, int listSize) {
		int paramCount = 3;
		Object[] parameterList = new Object[listSize * paramCount];
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
		String query = CustomerCheckOutPageSQL.customerSignOut;
		
		SQLStatementUtils.executeUpdate(query, personId);
	}
}
