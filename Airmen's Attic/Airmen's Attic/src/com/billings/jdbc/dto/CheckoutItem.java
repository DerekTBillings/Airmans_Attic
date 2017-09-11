package com.billings.jdbc.dto;

public class CheckoutItem implements Comparable {
	
	private int itemId;
	private String itemName;
	private String itemType;
	private boolean archived;
	private int quantity;
	
	public CheckoutItem() {
		itemId = 0;
		itemName = "";
		itemType = "";
		quantity = 0;
	}
	
	public int getItemId() {
		return itemId;
	}
	public void setItemId(int itemId) {
		this.itemId = itemId;
	}
	public String getItemName() {
		return itemName;
	}
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
	public String getItemType() {
		return itemType;
	}
	public void setItemType(String itemType) {
		this.itemType = itemType;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public boolean isArchived() {
		return archived;
	}
	public void setArchived(boolean archived) {
		this.archived = archived;
	}

	@Override
	public int compareTo(Object otherItem) {
		String otherItemType = ((CheckoutItem)otherItem).itemType;
		String otherItemName = ((CheckoutItem)otherItem).itemName;
		
		int result = this.itemType.compareTo(otherItemType);
		
		if (result == 0) {
			result = this.itemName.compareTo(otherItemName);
		}
		
		return result;
	}
	
}
