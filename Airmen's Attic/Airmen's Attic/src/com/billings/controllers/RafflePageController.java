package com.billings.controllers;

import java.net.URL;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.SingleSelectionModel;

import com.billings.jdbc.dao.RaffleAdminPageDAO;
import com.billings.jdbc.dao.RaffleAdminPageImpl;
import com.billings.jdbc.dto.RaffleItem;
import com.billings.main.WindowController;
import com.billings.resources.RafflePageResources;

public class RafflePageController implements Initializable {

	@FXML
	Label raffleHeaderLbl;
	@FXML
	Label raffleItemListLbl;
	@FXML
	Label itemDescriptionLbl;
	@FXML
	Label itemDescription;
	@FXML
	Label raffleDateLbl;
	@FXML
	Label raffleDate;

	@FXML
	ChoiceBox<RaffleItem> raffleItemList;

	@FXML
	Button addRaffleItemBtn;
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		setupNodesWithInitialText();
		
		loadRaffleItems();
		setupRaffleItemListOnClick();
		setupAddMeButton();
	}
	
	private void setupNodesWithInitialText() {
		raffleHeaderLbl.setText(RafflePageResources.RAFFLE_HEADER_LBL);
		raffleItemListLbl.setText(RafflePageResources.RAFFLE_ITEM_LIST_LBL);
		itemDescriptionLbl.setText(RafflePageResources.ITEM_DESCRIPTION_LBL);
		raffleDateLbl.setText(RafflePageResources.RAFFLE_DATE_LBL);
		addRaffleItemBtn.setText(RafflePageResources.ADD_RAFFLE_ITEM_BTN);
	}
	
	private void loadRaffleItems() {
		RaffleAdminPageDAO dao = new RaffleAdminPageImpl();
		
		List<RaffleItem> itemsInFaffle = dao.getRaffleItems(true);
		
		ObservableList<RaffleItem> raffleList = raffleItemList.getItems();
		raffleList.addAll(itemsInFaffle);
	}
	
	private void setupRaffleItemListOnClick() {		
		raffleItemList.valueProperty().addListener(
				(ObservableValue<? extends RaffleItem> observableValue, 
						RaffleItem prevItemSelected, 
						RaffleItem currItemSelected) -> {			
			String selectedItemDescription = currItemSelected.getDescription();
			String selectedItemDateToRaffle = currItemSelected.getDateToRaffle().toString();
			
			itemDescription.setText(selectedItemDescription);
			raffleDate.setText(selectedItemDateToRaffle);
		});
	}
	
	private void setupAddMeButton() {
		addRaffleItemBtn.setOnAction(e -> {
			RaffleItem itemSelected = (RaffleItem)raffleItemList.getValue();
			
			if (itemSelected != null) {
				int raffleId = itemSelected.getRaffleId();
				int personId = RafflePageResources.getCustomerId();
				
				boolean success = addPersonToRaffle(raffleId, personId);
				
				if (success) {
					closeThisPage(); 
				}
			}
		});
	}
	
	private boolean addPersonToRaffle(int raffleId, int personId) {
		RaffleAdminPageDAO dao = new RaffleAdminPageImpl();
		
		return dao.addPersonToRaffle(raffleId, personId);
	}
	
	private void closeThisPage() {
		WindowController.closeNodeContainingWindow(addRaffleItemBtn);
	}

}
