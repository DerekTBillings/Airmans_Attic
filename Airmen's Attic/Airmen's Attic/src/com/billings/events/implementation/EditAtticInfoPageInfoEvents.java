package com.billings.events.implementation;

import com.billings.events.interfaces.EditAtticInfoPageEvents;
import com.billings.resources.AdminPageResources;

public class EditAtticInfoPageInfoEvents implements EditAtticInfoPageEvents {

	@Override
	public void updateDisplay(String text) {
		AdminPageResources.setAtticInfo(text);
	}

}
