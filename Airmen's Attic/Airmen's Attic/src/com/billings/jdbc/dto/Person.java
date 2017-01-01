package com.billings.jdbc.dto;

import java.time.LocalDate;
import java.util.Date;

public class Person {
	
	private int personId;
	private String firstName;
	private String lastName;
	private String rank;
	private String dependentStatus;
	private LocalDate militaryIdExpirationDate;
	private String email;
	private String workPhone;
	private String cellPhone;
	private LocalDate birthDate;
	private String organization;
	
	private Person sponsor;
	
	public Person() {
		
	}
	
	public Person(String firstName, String lastName, String rank, 
			String dependentStatus, LocalDate militaryIdExpirationDate, 
			String email, String workPhone, String cellPhone,
			LocalDate birthDate, String organization) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.rank = rank;
		this.dependentStatus = dependentStatus;
		this.militaryIdExpirationDate = militaryIdExpirationDate;
		this.email = email;
		this.workPhone = workPhone;
		this.cellPhone = cellPhone;
		this.birthDate = birthDate;
		this.organization = organization;
	}
	
	@Override
	public Person clone() {
		return new Person(firstName, lastName, 
				rank, dependentStatus, militaryIdExpirationDate, 
				email, workPhone, cellPhone, birthDate, organization);
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
	public LocalDate getMilitaryIdExpirationDate() {
		return militaryIdExpirationDate;
	}
	public void setMilitaryIdExpirationDate(LocalDate militaryIdExpirationDate) {
		this.militaryIdExpirationDate = militaryIdExpirationDate;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getWorkPhone() {
		return workPhone;
	}
	public void setWorkPhone(String workPhone) {
		this.workPhone = workPhone;
	}
	public String getCellPhone() {
		return cellPhone;
	}
	public void setCellPhone(String cellPhone) {
		this.cellPhone = cellPhone;
	}
	public LocalDate getBirthDate() {
		return birthDate;
	}
	public void setBirthDate(LocalDate birthDate) {
		this.birthDate = birthDate;
	}
	public String getOrganization() {
		return organization;
	}
	public void setOrganization(String organization) {
		this.organization = organization;
	}
	public Person getSponsor() {
		return sponsor;
	}
	public void setSponsor(Person sponsor) {
		this.sponsor = sponsor;
	}
	
}
