package com.billings.utils;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

import com.billings.jdbc.dto.Person;
import com.billings.jdbc.dto.RaffleItem;

import javafx.event.EventTarget;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Alert.AlertType;

public class Common {
	
	public static LocalDate convertDateToLocalDate(Date date) {
		LocalDate convertedFormat = null;
		
		if (date != null) {
			Instant instant = Instant.ofEpochMilli(date.getTime());
			LocalDateTime dateTime = LocalDateTime.ofInstant(instant,  ZoneId.systemDefault());
			convertedFormat = dateTime.toLocalDate();
		}
		
		return convertedFormat;
	}
	
	public static RaffleItem buildRaffleItemFromResults(ResultSet results) throws SQLException {
		RaffleItem item = new RaffleItem();
		
		int raffleId = results.getInt("Raffle_Id");
		String name = results.getString("Name");
		String description = results.getString("Description");
		String type = results.getString("Type_Name");
		String status = results.getString("Status");
		Date dateIn = results.getDate("Date_In");
		Date raffleDate = results.getDate("Date_Raffled");
		Date dateToRaffle = results.getDate("Date_To_Raffle");
		
		LocalDate convertedDateIn = Common.convertDateToLocalDate(dateIn);
		LocalDate convertedRaffleDate = Common.convertDateToLocalDate(raffleDate);
		LocalDate convertedDateToRaffle = Common.convertDateToLocalDate(dateToRaffle);
		
		item.setRaffleId(raffleId);
		item.setName(name);
		item.setDescription(description);
		item.setType(type);
		item.setStatus(status);
		item.setDateIn(convertedDateIn);
		item.setDateRaffled(convertedRaffleDate);
		item.setDateToRaffle(convertedDateToRaffle);
		
		return item;
	}
	
	public static Person buildPersonFromResults(ResultSet results) throws SQLException {
		Person person = new Person();
		
		person.setPersonId(results.getInt("Person_Id"));
		person.setLastName(results.getString("Last_Name"));
		person.setFirstName(results.getString("First_Name"));
		person.setRank(results.getString("Rank"));
		person.setDependentStatus(results.getString("Dependent_Status"));
		person.setCellPhone(results.getString("Cell_Phone"));
		
		return person;
	}
	
	public static boolean isTargetAColumn(EventTarget target) {
		String targetText = target.toString();
		
		if (targetText.startsWith("Text") ||
				targetText.startsWith("TableColumn$1$1")) {
			return true;
		}
		
		return false;
	}
	
	public static boolean confirmPrompt(String message) {
		Alert alert = getConfirmPrompt(message);
		
		alert.showAndWait();

		return alert.getResult() == ButtonType.YES;
	}

	private static Alert getConfirmPrompt(String message) {
		return new Alert(AlertType.CONFIRMATION, message, 
				ButtonType.YES, ButtonType.NO);
	}
	
}
