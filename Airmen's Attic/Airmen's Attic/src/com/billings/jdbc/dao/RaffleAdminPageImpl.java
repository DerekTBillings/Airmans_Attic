package com.billings.jdbc.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.billings.jdbc.dto.Person;
import com.billings.jdbc.dto.RaffleItem;
import com.billings.jdbc.sql.RaffleAdminPageSQL;
import com.billings.jdbc.sql.RafflePageSQL;
import com.billings.utils.Messages;
import com.billings.utils.SQLStatementUtils;
import com.billings.utils.Common;
import com.billings.utils.Logger;

public class RaffleAdminPageImpl implements RaffleAdminPageDAO {

	@Override
	public List<RaffleItem> getRaffleItems(boolean doAddPendingFilter) {
		String query = RaffleAdminPageSQL.getRaffleItems;
		
		if (doAddPendingFilter)
			query = RaffleAdminPageSQL.getPendingRaffleItems;
		
		List<RaffleItem> raffleItems = getRaffleItems(query);
		
		populateWinners(raffleItems);
		
		return raffleItems;
	}
	
	@SuppressWarnings("unchecked")
	private List<RaffleItem> getRaffleItems(String query) {
		return SQLStatementUtils.executeQuery(query, RaffleItem.class);
	}

	private void populateWinners(List<RaffleItem> raffleItems) {
		for (RaffleItem item : raffleItems) {			
			Person winningCustomer = getRaffleItemWinner(item.getRaffleId());
			
			item.setWinningCustomer(winningCustomer);
		}
	}
	
	private Person getRaffleItemWinner(int raffleId) {		
		String query = RaffleAdminPageSQL.getWinnerForRaffleItem;
		
		return (Person)SQLStatementUtils.executeQueryForSingleRow(
				query, Person.class, raffleId);
	}

	@Override
	public List<Person> getPeopleInRaffleById(int raffleItemId) {
		String query = RaffleAdminPageSQL.getPeopleForRaffleItem;
		
		return SQLStatementUtils.executeQuery(query, Person.class, raffleItemId);
	}	

	@Override
	public boolean addPersonToRaffle(int itemId, int personId) {
		boolean success = false;
		
		String query = RaffleAdminPageSQL.isPersonInRaffleForItem;
		
		int resultCount = (Integer)SQLStatementUtils.executeQueryForSingleCell(
				query, Integer.class, itemId, personId);
		
		if (isCustomerInQueue(resultCount)) {
			addPersonToRaffleForItem(itemId, personId);		
			notifyCustomerOfSuccess();
			success = true;
		} else {
			notifyCustomerAlreadyInQueue();
			success = false;
		}
		
		return success;
	}

	private boolean isCustomerInQueue(int resultCount) {
		return resultCount == 0;
	}

	private void addPersonToRaffleForItem(int itemId, int personId) {
		String query = RaffleAdminPageSQL.addPersonToRaffleForItem;
		
		SQLStatementUtils.executeInsert(query, itemId, personId);
	}

	private void notifyCustomerOfSuccess() {
		Logger.notifyUser(Messages.CUSTOEMR_ADDED_TO_RAFFLE_QUEUE);
	}

	private void notifyCustomerAlreadyInQueue() {
		Logger.notifyUser(Messages.CUSTOMER_ALREADY_IN_RAFFLE_QUEUE);
	}

	@Override
	public void updateRaffleItemWinner(RaffleItem raffleItem) {
		String removeWinnerQuery = RaffleAdminPageSQL.removeWinnerStatusForRaffleItem;
		String addWinnerQuery = RaffleAdminPageSQL.addWinnerStatusForRaffleItem;
		String setRaffleDateQuery = RaffleAdminPageSQL.setRaffleDate;
		
		int raffleItemId = raffleItem.getRaffleId();
		
		Person winner = raffleItem.getWinningCustomer();
		int winnerId = winner.getPersonId();
		
		SQLStatementUtils.executeUpdate(removeWinnerQuery, raffleItemId);
		SQLStatementUtils.executeUpdate(addWinnerQuery, raffleItemId, winnerId);
		SQLStatementUtils.executeUpdate(setRaffleDateQuery, raffleItemId);
	}

	@Override
	public boolean isItemRaffled(int itemId) {
		String query = RaffleAdminPageSQL.isItemRaffled;
		
		return (Boolean)SQLStatementUtils.executeQueryForSingleCell(
				query, Boolean.class, itemId);
	}

	@Override
	public void setItemAsOut(int itemId) {
		String query = RaffleAdminPageSQL.setItemAsOut;
		
		SQLStatementUtils.executeUpdate(query, itemId);
	}

}
