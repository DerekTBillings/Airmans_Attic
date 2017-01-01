package com.billings.jdbc.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.billings.jdbc.dto.CheckoutHistoryItem;
import com.billings.jdbc.sql.SignInPageSQL;
import com.billings.main.WindowController;
import com.billings.resources.CustomerCheckOutPageResources;
import com.billings.resources.SignInPageResources;
import com.billings.utils.FXMLFactory;
import com.billings.utils.Logger;
import com.billings.utils.Messages;
import com.billings.utils.SQLStatementUtils;

public class SignInPageCustomerCheckout extends SignInPageDAO {

	private List<CheckoutHistoryItem> customerHistory;
	private int personId;
	private String customerName;
	
	@Override
	public void signIn(int personId) {
		this.personId = personId;
		String role = SignInPageResources.ROLE_EITHER;
		
		if (super.isCustomerSignedIn(personId, role)) {
			getCustomerHistory();
			getCustomerName();
			setupCheckOutPage();
			navigateToCheckOut();
		} else {
			Logger.notifyUser(Messages.CUSTOMER_NOT_SIGNED_IN);
		}
		
	}
	
	private void getCustomerHistory() {
		
		ResultSet results = null;
		
		try {
			results = SQLStatementUtils.executeQueryAndReturnResultSet(
					SignInPageSQL.getCustomerHistory, personId);

			buildHistoryListFromResults(results);
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			SQLStatementUtils.closeConnectionsWithResultSet();
		}
	}
	
	private void buildHistoryListFromResults(ResultSet results) throws SQLException {
		customerHistory = new ArrayList<CheckoutHistoryItem>();
		
		while (results.next()) {
			CheckoutHistoryItem item = createHistoryItem(results);
			customerHistory.add(item);
		}
	}
	
	private void getCustomerName() {
		String query = SignInPageSQL.findCustomerNameById;
		
		ResultSet results = SQLStatementUtils.executeQueryAndReturnResultSet(
				query, personId);
		
		customerName = getCustomerNameFromResults(results);
	}
	
	private String getCustomerNameFromResults(ResultSet results) {
		String customerName = "";
		
		try {
			results.next();
			String firstName = results.getString("First_Name");
			String lastName = results.getString("Last_Name");
			
			customerName = lastName + ", "+firstName;
			
		} catch (Exception e) {
			Logger.log(e.getMessage());
		
		} finally {
			SQLStatementUtils.closeConnectionsWithResultSet();
		}
		
		return customerName;
	}
	
	private CheckoutHistoryItem createHistoryItem(ResultSet results) throws SQLException {
		CheckoutHistoryItem item = new CheckoutHistoryItem();
		
		String itemName = results.getString("Item_Name");
		
		if (itemName.isEmpty()) {
			itemName = results.getString("Raffle_Name");
		}
		
		int quantity = results.getInt("Quantity");
		
		Date date = results.getDate("Checkout_Date");
		
		item.setItemName(itemName);
		item.setQuantity(quantity);
		item.setDate(date);
		
		return item;
	}
	
	private void setupCheckOutPage() {
		CustomerCheckOutPageResources.setCustomerId(personId);
		CustomerCheckOutPageResources.setCustomeerName(customerName);
		CustomerCheckOutPageResources.setCustomerHistoryItems(customerHistory);
	}
	
	private void navigateToCheckOut() {
		WindowController.createPopupWindow(
				FXMLFactory.getCustomerCheckOutPage());
	}

}
