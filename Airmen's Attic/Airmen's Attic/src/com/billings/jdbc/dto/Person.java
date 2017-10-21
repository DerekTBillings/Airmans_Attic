package com.billings.jdbc.dto;

public class Person {
	
	private int personId;
	
	private String firstName;
	private String lastName;
	private String rank;
	private String dependentStatus;
	private String cellPhone;
	private String archiveStatus;
	
	private boolean isAdmin;
	
	private Person sponsor;
	
	public Person() {}
	
	public Person(String firstName, String lastName, String rank, 
			String dependentStatus, String cellPhone) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.rank = rank;
		this.dependentStatus = dependentStatus;
		this.cellPhone = cellPhone;
	}
	
	@Override
	public Person clone() {
		return new Person(firstName, lastName, 
				rank, dependentStatus, cellPhone);
	}
	
	public Person getNewSponsor() {
		this.sponsor = new Person();
		return sponsor;
	}
	
	public int getPersonId() {
		return personId;
	}
	public void setPersonId(int id) {
		this.personId = id;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getFullName() {
		return String.format("%s, %s", lastName, firstName);
	}
	public String getRank() {
		return rank;
	}
	public void setRank(String rank) {
		this.rank = rank;
	}
	public String getDependentStatus() {
		return dependentStatus;
	}
	public void setDependentStatus(String dependentStatus) {
		this.dependentStatus = dependentStatus;
	}
	public String getCellPhone() {
		return cellPhone;
	}
	public void setCellPhone(String cellPhone) {
		this.cellPhone = cellPhone;
	}
	public Person getSponsor() {
		return sponsor;
	}
	public void setSponsor(Person sponsor) {
		this.sponsor = sponsor;
	}
	public String getArchiveStatus() {
		return archiveStatus;
	}
	public void setArchiveStatus(String archiveStatus) {
		this.archiveStatus = archiveStatus;
	}
	public boolean isAdmin() {
		return isAdmin;
	}
	public void setAdmin(boolean isAdmin) {
		this.isAdmin = isAdmin;
	}
	
}
