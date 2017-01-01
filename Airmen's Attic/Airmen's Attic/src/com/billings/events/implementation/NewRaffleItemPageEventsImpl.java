package com.billings.events.implementation;

import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import com.billings.events.interfaces.NewRaffleItemPageEvents;
import com.billings.jdbc.dao.NewRaffleItemPageDAO;
import com.billings.jdbc.dao.NewRaffleItemPageImpl;
import com.billings.jdbc.dto.RaffleItem;
import com.billings.resources.RaffleAdminPageResources;

public class NewRaffleItemPageEventsImpl implements NewRaffleItemPageEvents{

	@Override
	public ObservableList<String> getItemTypes() {
		NewRaffleItemPageDAO dao = new NewRaffleItemPageImpl();
		
		List<String> itemTypeList = dao.getItemTypeList();
		ObservableList<String> convertedItemTypeList = FXCollections.observableList(itemTypeList);
		
		return convertedItemTypeList;
	}

	@Override
	public void addNewItemType(String newItemType) {
		NewRaffleItemPageDAO dao = new NewRaffleItemPageImpl();
		
		dao.createNewItemType(newItemType);
	}

	@Override
	public void addNewRaffleItem(RaffleItem newRaffleItem) {
		NewRaffleItemPageDAO dao = new NewRaffleItemPageImpl();
		
		dao.addNewRaffleItem(newRaffleItem);
		
		int raffleId = getRaffleIdForItem(newRaffleItem);
		newRaffleItem.setRaffleId(raffleId);
		
		RaffleAdminPageResources.addRaffleItem(newRaffleItem);
	}

	@Override
	public int getRaffleIdForItem(RaffleItem raffleItem) {
		NewRaffleItemPageDAO dao = new NewRaffleItemPageImpl();
		
		return dao.getRaffleIdForItem(raffleItem);
		
	}

}
