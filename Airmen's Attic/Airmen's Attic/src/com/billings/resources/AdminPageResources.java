package com.billings.resources;

import javafx.scene.control.TextArea;

public class AdminPageResources {

	public static final String EDIT_MESSAGE_BTN = "Edit Message";
	public static final String EDIT_ITEM_BTN = "Edit Items";
	public static final String EDIT_ITEM_TYPES_BTN = "Edit Item Types";
	public static final String ADMIN_HEADER_LBL = "Admin Page";
	public static final String ATTIC_INFO_LBL = "Attic Info";
	public static final String ATTIC_INFO = "";
	public static final String RAFFLE_ADMIN_BTN = "Raffle Admin";
	public static final String CUSTOMER_LOOKUP_BTN = "Customer Lookup"; 
	public static final String GENERATE_REPORTS_BTN = "Generate Reports";
	public static final String EDIT_ATTIC_INFO_BTN = "Edit Attic Info"; 
	
	private static TextArea atticInfo;
	
	public static void bindInfoTextArea(TextArea target) {
		atticInfo = target;
	}
	
	public static void setAtticInfo(String info) {
		if (atticInfo != null)
			atticInfo.setText(info);
	}

}
