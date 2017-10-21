package com.billings.jdbc.dao;

import java.time.LocalDate;
import java.util.List;

import com.billings.jdbc.dto.RaffleItem;
import com.billings.jdbc.sql.NewRaffleItemPageSQL;
import com.billings.utils.SQLStatementUtils;

public class NewRaffleItemPageImpl implements NewRaffleItemPageDAO {

	@Override
	public List<String> getItemTypeList() {
		String query = NewRaffleItemPageSQL.getRaffleItemTypes;
		
		return SQLStatementUtils.executeQuery(query, String.class);
	}

	@Override
	public void createNewItemType(String newItemType) {
		String query = NewRaffleItemPageSQL.createNewItemType;
		
		SQLStatementUtils.executeInsert(query, newItemType);
	}

	@Override
	public void addNewRaffleItem(RaffleItem addRaffleItem) {
		String query = NewRaffleItemPageSQL.saveNewRaffleItem;
		
		String name = addRaffleItem.getName();
		String type = addRaffleItem.getType();
		String description = addRaffleItem.getDescription();
		LocalDate dateToRaffle = addRaffleItem.getDateToRaffle();
		
		SQLStatementUtils.executeInsert(
				query, name, description, type, dateToRaffle);
	}

	@Override
	public int getRaffleIdForItem(RaffleItem raffleItem) {
		String query = NewRaffleItemPageSQL.getRaffleItemId;
		
		String name = raffleItem.getName();
		String description = raffleItem.getDescription();
		String typeName = raffleItem.getType();
		LocalDate dateToRaffle = raffleItem.getDateToRaffle();
		
		return (Integer)SQLStatementUtils.executeQueryForSingleCell(
				query, Integer.class, 
				name, description, typeName, dateToRaffle);
	}

}
