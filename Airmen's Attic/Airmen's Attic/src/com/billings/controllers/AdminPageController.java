package com.billings.controllers;

import java.net.URL;
import java.util.ResourceBundle;


import com.billings.main.WindowController;
import com.billings.resources.AdminPageResources;
import com.billings.resources.CustomerInfoPageResources;
import com.billings.utils.FXMLFactory;


import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;

public class AdminPageController implements Initializable {

	@FXML
	Label adminHeaderLbl;
	@FXML
	Label atticInfoLbl;
	@FXML
	Label contactLbl;
	@FXML
	Label atticNotesLbl;
	@FXML
	Label miscLbl;
	@FXML
	Label bagCountTextLbl;
	@FXML
	Label bagCountLbl;
	@FXML
	Label historyLbl;

	@FXML
	TextArea atticNotes;
	@FXML
	TextArea atticInfo;

	@FXML
	Tab infoTab;
	@FXML
	Tab adminActionsTab;
	@FXML
	Tab notesTab;

	@FXML
	TableView contactsTbl;
	@FXML
	TableView historyTbl;

	@FXML
	TableColumn contactName;
	@FXML
	TableColumn contactsCategory;
	@FXML
	TableColumn contactsContactInfo;
	@FXML
	TableColumn contactsComments;
	@FXML
	TableColumn historyBagCount;
	@FXML
	TableColumn historyChurchCame;
	@FXML
	TableColumn historyAnimalDonations;
	@FXML
	TableColumn historyDate;

	@FXML
	Button raffleAdminBtn;
	@FXML
	Button customerLookupBtn;
	@FXML
	Button generateReportsBtn;
	@FXML
	Button addRemoveContactsBtn;
	@FXML
	Button addEventsBtn;
	@FXML
	Button editAtticInfoBtn;
	@FXML
	Button increaseBagCountBtn;

	@FXML
	CheckBox animalDonationChk;
	@FXML
	CheckBox churchCameChk;
	
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		setupNodesWithText();
		
		setupButtonEvents();
	}
	
	private void setupNodesWithText() {
		adminHeaderLbl.setText(AdminPageResources.ADMIN_HEADER_LBL);
		atticInfoLbl.setText(AdminPageResources.ATTIC_INFO_LBL);
		contactLbl.setText(AdminPageResources.CONTACT_LBL);
		atticNotesLbl.setText(AdminPageResources.ATTIC_NOTES_LBL);
		miscLbl.setText(AdminPageResources.MISC_LBL);
		bagCountTextLbl.setText(AdminPageResources.BAG_COUNT_TEXT_LBL);
		bagCountLbl.setText(AdminPageResources.BAG_COUNT_LBL);
		historyLbl.setText(AdminPageResources.HISTORY_LBL);
		atticNotes.setText(AdminPageResources.ATTIC_NOTES);
		atticInfo.setText(AdminPageResources.ATTIC_INFO);
		infoTab.setText(AdminPageResources.INFO_TAB);
		adminActionsTab.setText(AdminPageResources.ADMIN_ACTIONS_TAB);
		notesTab.setText(AdminPageResources.NOTES_TAB);
		contactName.setText(AdminPageResources.CONTACT_NAME); 
		contactsCategory.setText(AdminPageResources.CONTACTS_CATEGORY);
		contactsContactInfo.setText(AdminPageResources.CONTACTS_CONTACT_INFO);
		contactsComments.setText(AdminPageResources.CONTACTS_COMMENTS);
		historyBagCount.setText(AdminPageResources.HISTORY_BAG_COUNT); 
		historyChurchCame.setText(AdminPageResources.HISTORY_CHURCH_CAME); 
		historyAnimalDonations.setText(AdminPageResources.HISTORY_ANIMAL_DONATIONS); 
		historyDate.setText(AdminPageResources.HISTORY_DATE); 
		raffleAdminBtn.setText(AdminPageResources.RAFFLE_ADMIN_BTN);
		customerLookupBtn.setText(AdminPageResources.CUSTOMER_LOOKUP_BTN); 
		generateReportsBtn.setText(AdminPageResources.GENERATE_REPORTS_BTN);
		addRemoveContactsBtn.setText(AdminPageResources.ADD_REMOVE_CONTACTS_BTN);
		addEventsBtn.setText(AdminPageResources.ADD_EVENTS_BTN);
		editAtticInfoBtn.setText(AdminPageResources.EDIT_ATTIC_INFO_BTN); 
		increaseBagCountBtn.setText(AdminPageResources.INCREASE_BAG_COUNT_BTN);
		animalDonationChk.setText(AdminPageResources.ANIMAL_DONATION_CHK);
		churchCameChk.setText(AdminPageResources.CHURCH_CAME_CHK); 
	}
	
	private void setupButtonEvents() {
		setupRaffleAdminBtn();
		setupCustomerLookupBtn();
		setupGenerateReportsBtn();
		setupAddRemoveContactsBtn();
		setupAddEventsBtn();
		setupEditAtticInfoBtn();
	}

	private void setupRaffleAdminBtn() {
		raffleAdminBtn.setOnAction(e -> {
			WindowController.createPopupWindow(
					FXMLFactory.getRaffleAdminPage());
		});
	}

	private void setupCustomerLookupBtn() {
		customerLookupBtn.setOnAction(e -> {
			CustomerInfoPageResources.setupWithEditCustomer();
			
			WindowController.createPopupWindow(
					FXMLFactory.getCustomerInfoPage());
		});
	}

	private void setupGenerateReportsBtn() {
		generateReportsBtn.setOnAction(e -> {
			WindowController.createPopupWindow(
					FXMLFactory.getGenerateReportsPage());
		});
	}

	private void setupAddRemoveContactsBtn() {
		addRemoveContactsBtn.setOnAction(e -> {
			WindowController.createPopupWindow(
					FXMLFactory.getAddRemoveContactsPage());
		});
	}

	private void setupAddEventsBtn() {
		addEventsBtn.setOnAction(e -> {
			WindowController.createPopupWindow(
					FXMLFactory.getAtticEventsPage());
		});
	}

	private void setupEditAtticInfoBtn() {
		editAtticInfoBtn.setOnAction(e -> {
			WindowController.createPopupWindow(
					FXMLFactory.getEditAtticInfoPage());
		});
		
	}

}
