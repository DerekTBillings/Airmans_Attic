package com.billings.jdbc.dto;

public class FoundCustomer {
	
	private int personId;
	
	private String customerName;
	
	private String sponsorName;
	
	private String contactInfo;
	
	public FoundCustomer(){}
	
	public FoundCustomer(int personId, String customerName, 
			String sponsorName, String contactInfo) {
		this.personId = personId;
		this.customerName = customerName;
		this.sponsorName = sponsorName;
		this.contactInfo = contactInfo;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getSponsorName() {
		return sponsorName;
	}

	public void setSponsorName(String sponsorName) {
		this.sponsorName = sponsorName;
	}

	public String getContactInfo() {
		return contactInfo;
	}

	public void setContactInfo(String contactInfo) {
		this.contactInfo = contactInfo;
	}

	public int getPersonId() {
		return personId;
	}

	public void setPersonId(int personId) {
		this.personId = personId;
	}
	
}
