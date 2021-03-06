package com.billings.resources;

import java.util.List;

import javafx.collections.ObservableList;

import com.billings.jdbc.dto.Person;
import com.billings.jdbc.dto.RaffleItem;

public class RaffleAdminPageResources {

	public static String RAFFLE_ITEM_COLUMN = "Raffle Item(s)";
	public static String PERSON_IN_RAFFLE_NAME = "Name";
	public static String PERSON_IN_RAFFLE_CONTACT = "Contact";
	public static String WINNER_LBL = "Winner";
	public static String WINNER_NAME_LBL = "Name:";
	public static String WINNER_CONTACT_LBL = "Contact";
	public static String CANCEL_BTN = "Close";
	public static String RAFFLE_ITEM_BTN = "Raffle Item";
	public static String ITEM_RECEIVED_BTN = "Item Received";
	
	public static ObservableList<RaffleItem> raffleItemsInTbl;
	public static ObservableList<Person> personsInRaffleTbl;
	public static List<RaffleItem> raffleItems;
	
	public static boolean hasRaffleList() {
		return raffleItemsInTbl != null;
	}
	
	public static void addRaffleItem(RaffleItem raffleItem) {
		int emptyItemIndex = -1;
		
		try {
			emptyItemIndex = getIndexOfEmptyItem();
			addItemToListAtIndex(raffleItem, emptyItemIndex);
			
		} catch (Exception e) {
			addRaffleItemAndPlaceHolder(raffleItem);
		}
		
	}
	
	private static int getIndexOfEmptyItem() throws Exception {
		int startingIndex = raffleItemsInTbl.size()-1;
		
		for (int i=startingIndex; i>=0; i--) {
			RaffleItem item = raffleItemsInTbl.get(i);
			String name = item.getName();
			
			if (name.equals("New")) {
				return i;
			}
		}
		throw new Exception("Element not found");
	}
	
	private static void addItemToListAtIndex(RaffleItem item, int targetIndex) {
		int size = raffleItemsInTbl.size();
		
		if (targetIndex < size) {
			RaffleItem tempItem = raffleItemsInTbl.get(targetIndex);
			raffleItemsInTbl.set(targetIndex, item);
			
			addItemToListAtIndex(tempItem, targetIndex+1);
		
		} else {
			raffleItemsInTbl.add(item);
		}
	}
	
	private static void addRaffleItemAndPlaceHolder(RaffleItem item) {
		raffleItemsInTbl.add(item);
		addNewRaffleItemSlot();
	}

	public static void addNewRaffleItemSlot() {
		RaffleItem newItemSlot = createEmptyRaffleItem();
		RaffleAdminPageResources.raffleItemsInTbl.add(newItemSlot);
	}
	
	private static RaffleItem createEmptyRaffleItem() {
		RaffleItem newItem = new RaffleItem();
		
		newItem.setName("New");
		
		return newItem;
	}
	
}
