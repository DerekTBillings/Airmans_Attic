package com.billings.jdbc.dao;

import java.util.List;

import com.billings.jdbc.dto.CheckoutItem;

public interface CustomerCheckOutPageDAO {

	public List<CheckoutItem> getCheckoutItemList();
	
	public void checkout(List<CheckoutItem> checkoutItems, int personId);
	
}
