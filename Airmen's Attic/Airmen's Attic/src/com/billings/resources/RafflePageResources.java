package com.billings.resources;


public class RafflePageResources {

	public static String RAFFLE_HEADER_LBL = "Raffle";
	public static String RAFFLE_ITEM_LIST_LBL = "Raffle Item";
	public static String ITEM_DESCRIPTION_LBL = "Description";
	public static String RAFFLE_DATE_LBL = "Raffle Date";
	public static String ADD_RAFFLE_ITEM_BTN = "Add Me To Raffle";
	
	private static int CUSTOMER_ID;
	
	public static int getCustomerId() {
		return CUSTOMER_ID;
	}
	
	public static void setCustomerId(int customerId) {
		CUSTOMER_ID = customerId;
	}
	
}
