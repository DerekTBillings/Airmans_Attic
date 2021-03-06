package com.billings.events.implementation;

import java.util.Collections;
import java.util.List;

import com.billings.events.interfaces.CheckoutItemInfoPageEvents;
import com.billings.jdbc.dao.CheckoutItemInfoPageDAO;
import com.billings.jdbc.dao.CheckoutItemInfoPageImpl;
import com.billings.jdbc.dto.CheckoutItem;
import com.billings.resources.CheckoutItemInfoPageResources;
import com.billings.resources.CustomerCheckOutPageResources;
import com.billings.utils.Logger;
import com.billings.utils.Messages;

public class CheckoutItemInfoPageNewItem implements CheckoutItemInfoPageEvents {

	@Override
	public boolean setItemBtnEvent(CheckoutItem item) {
		boolean success = false;
		boolean doUpdateCheckoutItemList = false;
		
		if (validateItem(item))	{
			success = true;
			
			capitalizeItemText(item);
			String itemType = item.getItemType();
			CheckoutItemInfoPageDAO dao = new CheckoutItemInfoPageImpl();
			
			if (!itemTypeExists(itemType)) {
				dao.createNewItemType(itemType);
				addItemTypeToList(itemType);
				
				doUpdateCheckoutItemList = true;
			}
			
			if (!dao.itemExists(item)) {
				dao.createNewItem(item);
			
			} else {
				Logger.notifyUser(Messages.ITEM_ALREADY_EXISTS);
			}
			
			int itemId = dao.getItemId(item);
			item.setItemId(itemId);
			
			if (!dao.isRecentItem(item)) {
				doUpdateCheckoutItemList = true;
			}
			
			if (doUpdateCheckoutItemList) {
				updateCheckoutItemList(item);
			}
		}
		
		return success;
	}
	
	private boolean validateItem(CheckoutItem item) {
		boolean valid = false;
		
		String name = item.getItemName();
		String type = item.getItemType();
		
		if (name != null && !name.isEmpty() && 
				type != null && !type.isEmpty()) {
			valid = true;
		}
		
		return valid;
	}

	private void capitalizeItemText(CheckoutItem item) {
		String name = item.getItemName();
		String type = item.getItemType();
		
		item.setItemName(capitalizeFirstLetter(name));
		item.setItemType(capitalizeFirstLetter(type));
	}
	
	private String capitalizeFirstLetter(String str) {
		char firstChar = str.charAt(0);
		firstChar = Character.toUpperCase(firstChar);
		
		return firstChar+str.substring(1);
	}

	private boolean itemTypeExists(String itemType) {
		List<String> typeList = CheckoutItemInfoPageResources.getTypeList();
		boolean exists = false;
		
		for (String item : typeList) {
			if (item.equalsIgnoreCase(itemType)) {
				exists = true;
			}
		}
		
		return exists;
	}
	
	private void addItemTypeToList(String itemType) {
		List<String> typeList = CheckoutItemInfoPageResources.getTypeList();
		typeList.add(itemType);
	}
	
	private void updateCheckoutItemList(CheckoutItem item) {
		List<CheckoutItem> checkoutItemList = CustomerCheckOutPageResources.getCheckoutItemList();
		checkoutItemList.add(item);
		
		Collections.sort(checkoutItemList);
	}

}
