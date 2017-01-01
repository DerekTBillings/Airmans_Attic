package com.billings.jdbc.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.billings.jdbc.dto.RaffleItem;
import com.billings.jdbc.sql.NewRaffleItemPageSQL;
import com.billings.utils.Logger;
import com.billings.utils.SQLStatementUtils;

public class NewRaffleItemPageImpl implements NewRaffleItemPageDAO {

	@Override
	public List<String> getItemTypeList() {
		List<String> itemTypeResults = new ArrayList<String>();
		
		String query = NewRaffleItemPageSQL.getRaffleItemTypes;
		
		ResultSet results = SQLStatementUtils.executeQueryAndReturnResultSet(query);
		
		try {
			while (results.next()) {
				String itemType = results.getString("Type_Name");
				itemTypeResults.add(itemType);
			}
		} catch (SQLException e) {
			Logger.log(e.getMessage());
		} finally {
			SQLStatementUtils.closeConnectionsWithResultSet();
		}
		
		
		return itemTypeResults;
	}

	@Override
	public void createNewItemType(String newItemType) {
		String query = NewRaffleItemPageSQL.createNewItemType;
		
		SQLStatementUtils.executeQueryWithoutResultSet(query, newItemType);
	}

	@Override
	public void addNewRaffleItem(RaffleItem addRaffleItem) {
		String name = addRaffleItem.getName();
		String description = addRaffleItem.getDescription();
		String type = addRaffleItem.getType();
		LocalDate dateToRaffle = addRaffleItem.getDateToRaffle();
		
		String query = NewRaffleItemPageSQL.saveNewRaffleItem;
		
		SQLStatementUtils.executeQueryWithoutResultSet(
				query, name, description, type, dateToRaffle);
		
	}

	@Override
	public int getRaffleIdForItem(RaffleItem raffleItem) {
		int raffleItemId = -1;
		String query = NewRaffleItemPageSQL.getRaffleItemId;
		
		String name = raffleItem.getName();
		String description = raffleItem.getDescription();
		String typeName = raffleItem.getType();
		LocalDate dateToRaffle = raffleItem.getDateToRaffle();
		
		ResultSet results = SQLStatementUtils.executeQueryAndReturnResultSet(
				query, name, description, typeName, dateToRaffle);
		
		try {
			results.next();
			raffleItemId = results.getInt("Raffle_Id");
		} catch (Exception e) {
			Logger.log(e.getMessage());
		} finally {
			SQLStatementUtils.closeConnectionsWithResultSet();
		}
		
		return raffleItemId;
	}

}
