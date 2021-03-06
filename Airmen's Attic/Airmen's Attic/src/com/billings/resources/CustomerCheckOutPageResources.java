package com.billings.resources;

import java.util.List;

import javafx.collections.ObservableList;

import com.billings.jdbc.dao.CustomerCheckOutPageDAO;
import com.billings.jdbc.dao.CustomerCheckOutPageImpl;
import com.billings.jdbc.dto.CheckoutHistoryItem;
import com.billings.jdbc.dto.CheckoutItem;

public class CustomerCheckOutPageResources {

	public static String CHECK_OUT_LBL = "Customer Checkout For: ";
	public static String FILTER_LBL = "Filter Text:";
	
	public static String CHECKOUT_BTN = "Checkout";
	public static String CANCEL_BTN = "Cancel";
	
	public static String CHECKOUT_TAB = "Checkout";
	public static String HISTORY_TAB = "History";
	
	public static String CHECKOUT_ITEM = "Item";
	public static String CHECKOUT_ITEM_TYPE = "Item Type";
	public static String CHECKOUT_QUANTITY = "Quantity";
	public static String HISTORY_QUANTITY = "Quantity";
	public static String HISTORY_ITEM = "Item";
	public static String HISTORY_DATE = "Date";
	
	private static int personId;
	private static String customerName;

	private static List<CheckoutItem> checkoutItemList;
	private static List<CheckoutHistoryItem> customerHistoryItems;
	private static ObservableList<CheckoutItem> checkoutItemListContainer;
	
	public static void setCustomerId(int personId) {
		CustomerCheckOutPageResources.personId = personId;
	}
	
	public static int getCustomerId() {
		return personId;
	}

	public static void setCustomeerName(String customerName) {
		CustomerCheckOutPageResources.customerName = customerName;
	}
	
	public static String getCustomerName() {
		return customerName;
	}
	
	public static List<CheckoutItem> getCheckoutItemList() {
		if (checkoutItemList == null) {
			setupCheckoutItemList();
		}
		
		return checkoutItemList;
	}
	
	public static void setupCheckoutItemList() {
		CustomerCheckOutPageDAO dao = new CustomerCheckOutPageImpl();
		checkoutItemList = dao.getCheckoutItemList();
	}

	public static List<CheckoutHistoryItem> getCustomerHistoryItems() {
		return customerHistoryItems;
	}

	public static void setCustomerHistoryItems(
			List<CheckoutHistoryItem> historyItems) {
		customerHistoryItems = historyItems;
	}

	public static void setCheckoutItemListContainer(ObservableList<CheckoutItem> checkoutItemListContainer) {
		CustomerCheckOutPageResources.checkoutItemListContainer = checkoutItemListContainer;
	}
	
	public static void reloadCheckoutListContainer() {
		loadCheckoutListContainer(getCheckoutItemList());
	}
	
	public static void loadCheckoutListContainer(List<CheckoutItem> checkoutItems) {
		checkoutItemListContainer.clear();
		
		checkoutItemListContainer.addAll(checkoutItems);
		checkoutItemListContainer.add(newEmptyItem());
	}
	
	public static CheckoutItem newEmptyItem() {
		CheckoutItem newEmptyItem = new CheckoutItem();
		
		newEmptyItem.setItemName("New");
		newEmptyItem.setItemType("New");
		
		return newEmptyItem;
	}

	
}
