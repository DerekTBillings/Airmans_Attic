package com.billings.jdbc.dao;

import java.util.List;

import com.billings.jdbc.dto.RaffleItem;

public interface NewRaffleItemPageDAO {
	
	public List<String> getItemTypeList();
	
	public void createNewItemType(String newItemType);
	
	public void addNewRaffleItem(RaffleItem addRaffleItem);
	
	public int getRaffleIdForItem(RaffleItem raffleItem);
	
}
