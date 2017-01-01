package com.billings.events.interfaces;

import com.billings.jdbc.dto.CheckoutItem;

public interface CheckoutItemInfoPageEvents {
	
	public boolean setItemBtnEvent(CheckoutItem item);
	
}
