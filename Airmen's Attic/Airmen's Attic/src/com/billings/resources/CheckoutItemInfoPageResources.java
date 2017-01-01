package com.billings.resources;

import java.util.List;

import com.billings.jdbc.dao.CheckoutItemInfoPageDAO;
import com.billings.jdbc.dao.CheckoutItemInfoPageImpl;
import com.billings.jdbc.dto.CheckoutItem;

public class CheckoutItemInfoPageResources {
	
	public static String HEADER_LBL = "Item Info";
	public static String NAME_LBL = "Item Name:";
	public static String TYPE_LBL = "Item Type:";
	public static String QUANTITY_LBL = "Quantity:";
	public static String NEW_TYPE_LBL = "New Item Type";
	public static String SET_ITEM_BTN = "Set Item";
	public static String CANCEL_BTN = "Cancel";
	
	private static CheckoutItem checkoutItem;
	private static List<String> typeList;

	public static CheckoutItem getCheckoutItem() {
		return checkoutItem;
	}

	public static void setCheckoutItem(CheckoutItem checkoutItem) {
		CheckoutItemInfoPageResources.checkoutItem = checkoutItem;
	}
	
	public static List<String> getTypeList() {
		if (typeList == null) {
			CheckoutItemInfoPageDAO dao = new CheckoutItemInfoPageImpl();
			typeList = dao.getTypeList();
		}
		
		return typeList;
	}
	
	
}
