package com.billings.events.implementation;

import com.billings.events.interfaces.CheckoutItemInfoPageEvents;
import com.billings.jdbc.dto.CheckoutItem;

public class CheckoutItemInfoPageSetItem implements CheckoutItemInfoPageEvents {

	@Override
	public boolean setItemBtnEvent(CheckoutItem item) {
		return true;
	}

}
