package com.billings.resources;

import com.billings.jdbc.dto.RaffleItem;

public class NewRaffleItemPageResources {

	public static String RAFFLE_ITEM_HEADER_LBL = "New Raffle Item";
	public static String NAME_LBL = "Item Name:";
	public static String DESCRIPTION_LBL = "Description:";
	public static String TYPE_LBL = "Item Type:";
	public static String DATE_TO_RAFFLE_LBL = "Raffle Date:";
	public static String DATE_TO_RAFFLE_PROMPT = "MM/DD/YYYY";
	public static String CREATE_BTN = "Create";
	public static String CANCEL_BTN = "Cancel";
	public static String NEW_ITEM_TYPE_LBL = "New Type:";
	
	private static RaffleItem raffleItem;

	public static RaffleItem getRaffleItem() {
		return raffleItem;
	}

	public static void setRaffleItem(RaffleItem item) {
		raffleItem = item;
	}
	
	
	
}
