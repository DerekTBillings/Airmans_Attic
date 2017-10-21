package com.billings.jdbc.dao;

import java.sql.ResultSet;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.billings.jdbc.dto.CheckoutHistoryItem;
import com.billings.jdbc.sql.SignInPageSQL;
import com.billings.main.WindowController;
import com.billings.resources.CustomerCheckOutPageResources;
import com.billings.resources.SignInPageResources;
import com.billings.utils.FXMLFactory;
import com.billings.utils.Logger;
import com.billings.utils.Messages;
import com.billings.utils.SQLStatementUtils;

@SuppressWarnings("unchecked")
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
		String query = SignInPageSQL.getCustomerHistory;
		
		customerHistory = SQLStatementUtils.executeQuery(
				query, CheckoutHistoryItem.class, personId);
	}
	
	private void getCustomerName() {
		String query = SignInPageSQL.findCustomerNameById;
		
		customerName = (String)SQLStatementUtils.executeQueryForSingleCell(
				query, String.class, personId);
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
