package com.billings.controllers;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import com.billings.events.implementation.NewRaffleItemPageEventsImpl;
import com.billings.events.interfaces.NewRaffleItemPageEvents;
import com.billings.jdbc.dto.RaffleItem;
import com.billings.main.WindowController;
import com.billings.resources.NewRaffleItemPageResources;
import com.billings.utils.InputValidation;

public class NewRaffleItemPageController implements Initializable {

	@FXML
	Label raffleItemHeaderLbl;
	@FXML
	Label nameLbl;
	@FXML
	Label descriptionLbl;
	@FXML
	Label typeLbl;
	@FXML
	Label dateToRaffleLbl;
	@FXML
	Label newItemTypeLbl;

	@FXML
	TextField name;
	@FXML
	TextField description;
	@FXML
	TextField newItemType;

	@FXML
	DatePicker raffleDate;

	@FXML
	ChoiceBox<String> type;

	@FXML
	Button createBtn;
	@FXML
	Button cancelBtn;
	
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		setupNodesWithTextValues();
		populateTypeChoiceBox();
		setupRaffleField();
		setupCreateBtn();
		setupCancelBtn();
	}

	private void setupNodesWithTextValues() {
		raffleItemHeaderLbl.setText(NewRaffleItemPageResources.RAFFLE_ITEM_HEADER_LBL);
		nameLbl.setText(NewRaffleItemPageResources.NAME_LBL);
		descriptionLbl.setText(NewRaffleItemPageResources.DESCRIPTION_LBL);
		typeLbl.setText(NewRaffleItemPageResources.TYPE_LBL);
		dateToRaffleLbl.setText(NewRaffleItemPageResources.DATE_TO_RAFFLE_LBL);
		raffleDate.setPromptText(NewRaffleItemPageResources.DATE_TO_RAFFLE_PROMPT);
		newItemTypeLbl.setText(NewRaffleItemPageResources.NEW_ITEM_TYPE_LBL);
		createBtn.setText(NewRaffleItemPageResources.CREATE_BTN);
		cancelBtn.setText(NewRaffleItemPageResources.CANCEL_BTN);
	}
	
	private void populateTypeChoiceBox() {
		NewRaffleItemPageEvents events = new NewRaffleItemPageEventsImpl();
		ObservableList<String> itemTypeList = events.getItemTypes();
		
		type.setItems(itemTypeList);
	}
	
	private void setupRaffleField() {
		raffleDate.setEditable(false);
	} 
	
	private void setupCreateBtn() {
		createBtn.setOnAction(e -> {
			if (checkForEmptyFields()) {
				RaffleItem raffleItem = populateRaffleItem();
				
				if (checkForNewItemType()) {
					addNewItemType();
				}
				
				saveNewRaffleItem(raffleItem);
				
				closeWindow();
			}
		});
	}
	
	private boolean checkForEmptyFields() {
		return hasFieldValues(name, description) &&
				hasType() && hasRaffleDate();
	}
	
	private boolean checkForNewItemType() {
		boolean isNewType = false;
		
		String existingType = type.getValue();
		String newType = newItemType.getText();
		
		if ((existingType == null || existingType.isEmpty()) 
				&& !newType.isEmpty()
				&& !type.getItems().contains(newType)) {
			isNewType = true;
		}
		
		return isNewType;
	}
	
	private void addNewItemType() {
		NewRaffleItemPageEvents events = new NewRaffleItemPageEventsImpl();
		
		String typeToAdd = newItemType.getText();
		
		events.addNewItemType(typeToAdd);
	}
	
	private void saveNewRaffleItem(RaffleItem newRaffleItem) {
		NewRaffleItemPageEvents events = new NewRaffleItemPageEventsImpl();
		events.addNewRaffleItem(newRaffleItem);
	}
	
	private boolean hasFieldValues(TextField... fields) {
		for (TextField field : fields) {
			String inputText = field.getText();
			
			if (inputText.isEmpty()) {
				InputValidation.addErrorFormat(field);
			}
		}
		
		return true;
	}
	
	private boolean hasType() {
		String newTypeInput = newItemType.getText();
		String selectedType = type.getValue();
		
		if ((newTypeInput == null || newTypeInput.isEmpty()) && 
				(selectedType == null || selectedType.isEmpty())) {
			InputValidation.addErrorFormat(type); 
			return false;
		} else {
			return true;
		}
	}	
	
	private boolean hasRaffleDate() {
		LocalDate raffleDateInput = raffleDate.getValue();
		
		if (raffleDateInput == null) {
			InputValidation.addErrorFormat(raffleDate);
			return false;
		} else {
			return true;
		}
	}
	
	private RaffleItem populateRaffleItem() {
		RaffleItem raffleItem = new RaffleItem();
		
		String itemName = name.getText(); 
		String itemDescription = description.getText();
		String typeInput = (type.getValue() != null) ? 
				type.getValue() : newItemType.getText();
		LocalDate dateToRaffle = raffleDate.getValue();
		
		raffleItem.setName(itemName);
		raffleItem.setDescription(itemDescription);
		raffleItem.setType(typeInput);
		raffleItem.setDateToRaffle(dateToRaffle);
		
		return raffleItem;
	}
	
	private void setupCancelBtn() {
		cancelBtn.setOnAction(e -> closeWindow());
	}
	
	private void closeWindow() {
		WindowController.closeNodeContainingWindow(cancelBtn);
	}
	
}
