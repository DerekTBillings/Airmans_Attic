package com.billings.jdbc.dto;

import java.util.Date;

public class CheckoutHistoryItem {
	
	private String itemName;
	
	private int quantity;
	
	private Date date;
	
	public String getItemName() {
		return itemName;
	}
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	
}
