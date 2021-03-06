package com.billings.jdbc.dto;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import com.billings.utils.Common;

public class RaffleItem {

	private int raffleId;
	
	private String name;
	private String description;
	private String type;
	private String status;
	
	private LocalDate dateIn;
	private LocalDate dateToRaffle;
	private LocalDate dateRaffled;
	
	private Person winningCustomer;
	
	@Override
	public String toString() {
		return name;
	}
	
	public int getRaffleId() {
		return raffleId;
	}
	public void setRaffleId(int raffleId) {
		this.raffleId = raffleId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public LocalDate getDateIn() {
		return dateIn;
	}
	public void setDateIn(LocalDate dateIn) {
		this.dateIn = dateIn;
	}
	public Person getWinningCustomer() {
		return winningCustomer;
	}
	public void setWinningCustomer(Person winningCustomer) {
		this.winningCustomer = winningCustomer;
	}
	public LocalDate getDateToRaffle() {
		return dateToRaffle;
	}
	public void setDateToRaffle(LocalDate dateToRaffle) {
		this.dateToRaffle = dateToRaffle;
	}
	public LocalDate getDateRaffled() {
		return dateRaffled;
	}
	public void setDateRaffled(LocalDate dateRaffled) {
		this.dateRaffled = dateRaffled;
	}
	
}
