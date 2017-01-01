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
		person.setEmail(results.getString("Email_Address"));
		person.setWorkPhone(results.getString("Work_Phone"));
		person.setCellPhone(results.getString("Cell_Phone"));
		person.setOrganization(results.getString("Organization"));

		Date milExpDate = results.getDate("Military_Id_Exp_Date");
		Date birthDate = results.getDate("Birth_Date");
		
		LocalDate milExpLocal = Common.convertDateToLocalDate(milExpDate);
		LocalDate birthLocal = Common.convertDateToLocalDate(birthDate);
		
		person.setMilitaryIdExpirationDate(milExpLocal);
		person.setBirthDate(birthLocal);
		
		return person;
	}
	
}
