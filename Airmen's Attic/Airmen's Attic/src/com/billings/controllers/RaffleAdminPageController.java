package com.billings.controllers;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import javafx.collections.ObservableList;
import javafx.event.EventTarget;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TableView.TableViewSelectionModel;
import javafx.scene.control.cell.PropertyValueFactory;

import com.billings.jdbc.dao.RaffleAdminPageDAO;
import com.billings.jdbc.dao.RaffleAdminPageImpl;
import com.billings.jdbc.dto.Person;
import com.billings.jdbc.dto.RaffleItem;
import com.billings.main.WindowController;
import com.billings.resources.RaffleAdminPageResources;
import com.billings.utils.FXMLFactory;


public class RaffleAdminPageController implements Initializable {
	
	@FXML
	TableView<RaffleItem> raffleItemTbl;
	@FXML
	TableView<Person> peopleInRaffleTbl;

	@FXML
	TableColumn<RaffleItem, String> raffleItemColumn;
	@FXML
	TableColumn<Person, String> personInRaffleName;
	@FXML
	TableColumn<Person, String> personInRaffleContact;

	@FXML
	Label raffleItemDescription;
	@FXML
	Label winnerLbl;
	@FXML
	Label winnerNameLbl;
	@FXML
	Label winnerName;
	@FXML
	Label winnerContactLbl;
	@FXML
	Label winnerContact;
	
	@FXML
	Button cancelBtn;
	@FXML
	Button raffleItemBtn;
	@FXML
	Button itemReceivedBtn;
	
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		setupNodesWithInitialText();
		
		RaffleAdminPageResources.raffleItemsInTbl = raffleItemTbl.getItems();
		
		setupPeopleInRaffleTbl();
		loadRaffleItems();
		linkRaffleItemWithTbl();
		populateRaffleItemTbl();
		
		setupRaffleItemTblClick();
		setupRaffleItemBtn();
		setupItemReceivedBtn();
		
		setupCancelBtn();
	}
	
	private void setupNodesWithInitialText() {
		raffleItemColumn.setText(RaffleAdminPageResources.RAFFLE_ITEM_COLUMN);
		personInRaffleName.setText(RaffleAdminPageResources.PERSON_IN_RAFFLE_NAME);
		personInRaffleContact.setText(RaffleAdminPageResources.PERSON_IN_RAFFLE_CONTACT);
		winnerLbl.setText(RaffleAdminPageResources.WINNER_LBL);
		winnerNameLbl.setText(RaffleAdminPageResources.WINNER_NAME_LBL);
		winnerContactLbl.setText(RaffleAdminPageResources.WINNER_CONTACT_LBL);
		cancelBtn.setText(RaffleAdminPageResources.CANCEL_BTN);
		raffleItemBtn.setText(RaffleAdminPageResources.RAFFLE_ITEM_BTN);
		itemReceivedBtn.setText(RaffleAdminPageResources.ITEM_RECEIVED_BTN);
	}
	
	private void setupPeopleInRaffleTbl() {
		personInRaffleName.setCellValueFactory(
			new PropertyValueFactory<Person, String>("fullName")
		);
		personInRaffleContact.setCellValueFactory(
			new PropertyValueFactory<Person, String>("cellPhone")
		);
		
		RaffleAdminPageResources.personsInRaffleTbl = peopleInRaffleTbl.getItems();
	}
	
	private void loadRaffleItems() {
		RaffleAdminPageDAO dao = new RaffleAdminPageImpl();
		
		RaffleAdminPageResources.raffleItems = dao.getRaffleItems(false);
	}
	
	private void linkRaffleItemWithTbl() {
		raffleItemColumn.setCellValueFactory(
			new PropertyValueFactory<RaffleItem, String>("name")
		);
	}	
	
	private void populateRaffleItemTbl() {		
		RaffleAdminPageResources.raffleItemsInTbl.addAll(
				RaffleAdminPageResources.raffleItems);
		
		RaffleAdminPageResources.addNewRaffleItemSlot();
	}
	
	private void setupRaffleItemTblClick() {
		raffleItemTbl.setOnMouseClicked(e -> {
			EventTarget target = e.getTarget();
			
			if (isTargetAColumn(target)) {
				RaffleAdminPageResources.personsInRaffleTbl.clear();
				
				winnerName.setText("");
				winnerContact.setText("");
				
				RaffleItem selectedRow = getSelectedItem();
				
				if (selectedRow != null) {
					String raffleName = selectedRow.getName();
					if (raffleName.equals("New")) {
						createNewRaffleItem();
					} else {
						populatePeopleInRaffle(selectedRow);
					}
				}
			}
		});
	}

	private boolean isTargetAColumn(EventTarget target) {
		String targetText = target.toString();
		
		boolean isCorrectTarget = false;
		
		if (targetText.startsWith("Text") ||
				targetText.startsWith("TableColumn$1$1")) {
			isCorrectTarget = true;
		}
		
		return isCorrectTarget;
	}
	
	private RaffleItem getSelectedItem() {
		TableViewSelectionModel<RaffleItem> selectedItem = raffleItemTbl.getSelectionModel();
		return selectedItem.getSelectedItem();
	}
	
	private void createNewRaffleItem() {
		WindowController.createPopupWindow(
			FXMLFactory.getNewRaffleItemPage());
	} 
	
	private void populatePeopleInRaffle(RaffleItem selectedItem) {
		RaffleAdminPageDAO dao = new RaffleAdminPageImpl();
		
		int raffleItemId = selectedItem.getRaffleId();
		
		List<Person> peopleInRaffle = dao.getPeopleInRaffleById(raffleItemId);
		
		loadPeopleInRaffleTable(peopleInRaffle);
		
		if (isRaffleItemWon()) {
			displayWinner();
		}
	}
	
	private void loadPeopleInRaffleTable(List<Person> peopleInRaffle) {
		ObservableList<Person> personsInRaffle = RaffleAdminPageResources.personsInRaffleTbl;
		personsInRaffle.addAll(peopleInRaffle);
	}
	
	private boolean isRaffleItemWon() {
		RaffleItem itemToCheck = getSelectedItem();
		
		if (itemToCheck.getWinningCustomer() == null) {
			return false;
		} else {
			return true;
		}
	}
	
	private void displayWinner() {
		RaffleItem raffleItem = getSelectedItem();
		Person winner = raffleItem.getWinningCustomer();
		
		String lastName = winner.getLastName();
		String firstName = winner.getFirstName();
		String name = String.format("%s %s", firstName, lastName);
		
		String phoneNumber = formatPhoneNumber(winner.getCellPhone());
		
		winnerName.setText(name);
		winnerContact.setText(phoneNumber);
	}
	
	private void setupRaffleItemBtn() {
		raffleItemBtn.setOnAction(e -> {
			ObservableList<Person> peopleInRaffle = RaffleAdminPageResources.personsInRaffleTbl;
			int countPeopleInQueue = peopleInRaffle.size();
			
			int winningIndex = (int)(Math.random() * countPeopleInQueue);
			
			Person winner = peopleInRaffle.get(winningIndex);
			
			saveWinner(winner);
			displayWinner();
		});
	}
	
	private String formatPhoneNumber(String phoneNumber) {
		StringBuffer workingNumber = new StringBuffer(phoneNumber);
		
		workingNumber.insert(0, '(');
		workingNumber.insert(4, ") ");
		workingNumber.insert(9, '-');
		
		return workingNumber.toString();
	}

	private void saveWinner(Person winner) {
		RaffleItem raffleItem = getSelectedItem();
		raffleItem.setWinningCustomer(winner);
		
		RaffleAdminPageDAO dao = new RaffleAdminPageImpl();
		dao.updateRaffleItemWinner(raffleItem);
	}
	
	private void setupItemReceivedBtn() {
		itemReceivedBtn.setOnAction(e -> {
			int itemId = getSelectedItemId();
			
			if (itemId != -1 && isItemRaffled(itemId)) {
				setItemAsOut(itemId);
				removeItemFromList();
				resetFields();
			}
		});
	}
	
	private int getSelectedItemId() {
		RaffleItem item = getSelectedItem();
		
		if (item != null) {
			return item.getRaffleId();
		}
		
		return -1;
	}
	
	private boolean isItemRaffled(int itemId) {
		RaffleAdminPageDAO dao = new RaffleAdminPageImpl();
		
		return dao.isItemRaffled(itemId);
	}
	
	private void setItemAsOut(int itemId) {
		RaffleAdminPageDAO dao = new RaffleAdminPageImpl();
		
		dao.setItemAsOut(itemId);
	}
	
	private void removeItemFromList() {
		RaffleItem item = getSelectedItem();
		ObservableList<RaffleItem> itemList = raffleItemTbl.getItems();
		itemList.remove(item);
	}
	
	private void resetFields() {
		peopleInRaffleTbl.getItems().clear();
		
		raffleItemDescription.setText(""); 
		winnerContact.setText("");
		winnerName.setText("");
	}
	
	private void setupCancelBtn() {
		cancelBtn.setOnAction(e -> {
			WindowController.closeNodeContainingWindow(cancelBtn);
		});
	}
}
