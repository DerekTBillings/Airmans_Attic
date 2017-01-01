package com.billings.events.interfaces;

import javafx.collections.ObservableList;

import com.billings.jdbc.dto.RaffleItem;

public interface NewRaffleItemPageEvents {
	
	public ObservableList<String> getItemTypes();
	
	public void addNewItemType(String newItemType);
	
	public void addNewRaffleItem(RaffleItem newRaffleItem);
	
	public int getRaffleIdForItem(RaffleItem raffleItem);
	
}
