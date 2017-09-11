package com.billings.jdbc.dao;

import java.util.List;

import com.billings.jdbc.dto.Person;
import com.billings.jdbc.dto.RaffleItem;

public interface RaffleAdminPageDAO {

	public List<RaffleItem> getRaffleItems(boolean doAddPendingFilter);
	
	public List<Person> getPeopleInRaffleById(int raffleItemId);
	
	public boolean addPersonToRaffleForItem(int itemId, int personId);
	
	public void updateRaffleItemWinner(RaffleItem raffleItem);

	public boolean isItemRaffled(int itemId);

	public void setItemAsOut(int itemId);
}
