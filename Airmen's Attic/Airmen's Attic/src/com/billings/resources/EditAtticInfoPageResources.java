package com.billings.resources;

import com.billings.events.implementation.*;
import com.billings.events.interfaces.EditAtticInfoPageEvents;
import com.billings.jdbc.dao.*;

public class EditAtticInfoPageResources {
	public static String UPDATE_BTN = "Update";
	public static String CANCEL_BTN = "Cancel";
	
	private static EditAtticInfoPageDAO dao;
	private static EditAtticInfoPageEvents events;

	public static void initializeWithAtticInfo() {
		dao = new EditAtticInfoPageInfoImpl();
		events = new EditAtticInfoPageInfoEvents();
	}
	
	public static void initializeWithAtticMessage() {
		dao = new EditAtticInfoPageMessageImpl();
		events = new EditAtticInfoPageMessageEvents();
	}
	
	public static void initializeWithPerson(int personId) {
		dao = new EditAtticInfoPagePersonImpl(personId);
		events = new EditAtticInfoPagePersonEvents();
	}
	
	public static EditAtticInfoPageDAO getDAO() {
		if (dao == null)
			initializeWithAtticInfo();
		
		return dao;
	}

	public static EditAtticInfoPageEvents getEvents() {
		return events;
	}
}
