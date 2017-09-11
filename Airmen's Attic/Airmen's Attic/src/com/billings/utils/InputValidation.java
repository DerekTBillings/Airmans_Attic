package com.billings.utils;

import java.util.HashSet;
import java.util.Set;

import javafx.scene.Node;
import javafx.scene.control.ChoiceBox;
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
	
	public static void addErrorFormat(Node node) {
		node.getStyleClass().add("error");
	}
	
	public static void removeErrorFormat(Node node) {
		node.getStyleClass().remove("error");
	}
	
}
