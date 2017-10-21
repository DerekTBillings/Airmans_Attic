package com.billings.events.implementation;

import com.billings.events.interfaces.EditAtticInfoPageEvents;
import com.billings.resources.WelcomePageResources;

public class EditAtticInfoPageMessageEvents implements EditAtticInfoPageEvents {

	@Override
	public void updateDisplay(String text) {
		WelcomePageResources.setMessage(text);
	}

}
