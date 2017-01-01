package com.billings.events.interfaces;

import com.billings.jdbc.dto.FoundCustomer;

public interface SelectCustomerPageEvents {

	void submit(FoundCustomer foundCustomer);
	
}
