package com.billings.utils;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import javafx.scene.Node;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

public class InputValidation {

	Set<Node> requiredFields;
	
	public InputValidation(Set<Node> requiredFields) {
		this.requiredFields = requiredFields;
	}
	
	public InputValidation() {
		requiredFields = new HashSet<Node>();
	}
	
	public boolean validateName(TextField nameField) {
		boolean allowed = true;
		
		String name = nameField.getText();
		name = name.trim();
		
		nameField.setText(name);
		
		String parsedName = name.replaceAll("[a-zA-Z.]", "");
		int numOfBadChars = parsedName.length();
		
		if ((requiredFields.contains(nameField) &&
				name.length() < 2) ||
				numOfBadChars != 0) {
			allowed = false;
		}
		
		if (!allowed) {
			addErrorFormat(nameField);
		} else {
			removeErrorFormat(nameField);
		}
		
		return allowed;
	}
	
	public boolean validatePhone(TextField phoneField) {
		boolean allowed = true;
		
		String phone = phoneField.getText();
		
		phone = phone.trim();
		
		phoneField.setText(phone);
		
		int numOfBadChars = phone.replaceAll("[0-9()-. ]", "").length();
		
		phone = phone.replaceAll("[^0-9]", "");
		
		if (phone.startsWith("1")) {
			phone = phone.substring(1);
		}
		
		int numOfDigits = phone.length();
		
		if ((requiredFields.contains(phoneField) &&
				numOfDigits != 10) ||
				numOfBadChars != 0) {
			allowed = false;
		}
		
		if (!allowed) {
			addErrorFormat(phoneField);
			if (numOfDigits > 0) {
				Logger.notifyUser(Messages.invalidPhoneNumber);
			}
		} else {
			phoneField.setText(phone);
			removeErrorFormat(phoneField);
		}
		
		return allowed;
	}
	
	public boolean validateEmail(TextField emailField) {
		boolean allowed = true;
		
		String email = emailField.getText();
		email = email.trim();
		
		emailField.setText(email);
		
		int numOfAts = email.replaceAll("[^@]", "").length();
		boolean containsAPeriod = email.contains(".");
		int numOfChars = email.length();
		
		if (requiredFields.contains(emailField) ||
				numOfChars > 0) {
			if (numOfAts != 1 &&
					numOfChars < 4 &&
					!containsAPeriod) {
				
				allowed = false;
			}
		} 
		
		if (!allowed) {
			addErrorFormat(emailField);
		} else {
			removeErrorFormat(emailField);
		}
		
		return allowed;
	}
	
	public boolean validateMilExpirationDate(DatePicker dateField) {
		boolean allowed = true;
		
		LocalDate dateValue = dateField.getValue();
		LocalDate currDate = LocalDate.now();
		
		if (requiredFields.contains(dateField) &&
				(dateValue == null || dateValue.equals(""))) {
			allowed = false;
		}
		
		if (dateValue != null && 
				currDate.compareTo(dateValue) > 0) {
			allowed = false;
		}
		
		if (!allowed) {
			addErrorFormat(dateField);
		} else {
			removeErrorFormat(dateField);
		}
		
		return allowed;
	}
	
	public boolean validateBirthDate(DatePicker dateField) {
		boolean allowed = true;

		LocalDate dateValue = dateField.getValue();
		LocalDate currDate = LocalDate.now();
		
		if (requiredFields.contains(dateField) &&
				(dateValue == null || dateValue.equals(""))) {
			allowed = false;
		}
		
		if (dateValue != null && 
				currDate.compareTo(dateValue) < 0) {
			allowed = false;
		}
		
		if (!allowed) {
			addErrorFormat(dateField);
		} else {
			removeErrorFormat(dateField);
		}
		
		return allowed;
	}
	
	public boolean validateRank(ChoiceBox rank) {
		boolean allowed = true;
		
		Object value = rank.getValue();
		
		if (requiredFields.contains(rank) &&
				(value == null || value.equals(""))) {
			allowed = false;
		}
		
		if (!allowed) {
			addErrorFormat(rank);
		} else {
			removeErrorFormat(rank);
		}
		
		return allowed;
	}
	
	public boolean validateOrganization(TextField organization) {
		boolean allowed = true;
		
		String orgValue = organization.getText();
		orgValue = orgValue.trim();
		
		organization.setText(orgValue);
		
		if (requiredFields.contains(organization) &&
				orgValue.equals("")) {
			allowed = false;
		}
		
		if (!allowed) {
			addErrorFormat(organization);
		} else {
			removeErrorFormat(organization);
		}
		
		return allowed;
	}
	
	public static void addErrorFormat(Node node) {
		node.getStyleClass().add("error");
	}
	
	public static void removeErrorFormat(Node node) {
		node.getStyleClass().remove("error");
	}
	
}
