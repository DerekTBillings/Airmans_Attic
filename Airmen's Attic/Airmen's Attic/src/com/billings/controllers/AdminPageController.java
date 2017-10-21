package com.billings.controllers;

import java.net.URL;
import java.util.ResourceBundle;

import com.billings.jdbc.dao.EditAtticInfoPageDAO;
import com.billings.jdbc.dao.EditAtticInfoPageInfoImpl;
import com.billings.main.WindowController;
import com.billings.resources.AdminPageResources;
import com.billings.resources.EditAtticInfoPageResources;
import com.billings.resources.SignInPageResources;
import com.billings.utils.FXMLFactory;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;

public class AdminPageController implements Initializable {

	@FXML
	Label adminHeaderLbl;
	@FXML
	Label atticInfoLbl;
	
	@FXML
	TextArea atticInfo;

	@FXML
	Button raffleAdminBtn;
	@FXML
	Button customerLookupBtn;
	@FXML
	Button generateReportsBtn;
	@FXML
	Button editAtticInfoBtn;
	@FXML
	Button editItemBtn;
	@FXML
	Button editItemTypesBtn;
	@FXML
	Button editMessageBtn;
	
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		setupNodesWithText();
		setupButtonEvents();
		setupAtticInfoField();
	}
	
	private void setupNodesWithText() {
		adminHeaderLbl.setText(AdminPageResources.ADMIN_HEADER_LBL);
		atticInfoLbl.setText(AdminPageResources.ATTIC_INFO_LBL);
		atticInfo.setText(AdminPageResources.ATTIC_INFO);
		raffleAdminBtn.setText(AdminPageResources.RAFFLE_ADMIN_BTN);
		customerLookupBtn.setText(AdminPageResources.CUSTOMER_LOOKUP_BTN); 
		generateReportsBtn.setText(AdminPageResources.GENERATE_REPORTS_BTN);
		editAtticInfoBtn.setText(AdminPageResources.EDIT_ATTIC_INFO_BTN); 
		editItemBtn.setText(AdminPageResources.EDIT_ITEM_BTN);
		editItemTypesBtn.setText(AdminPageResources.EDIT_ITEM_TYPES_BTN);
		editMessageBtn.setText(AdminPageResources.EDIT_MESSAGE_BTN);
		atticInfo.setText(getAtticInfo());
	}
	
	private String getAtticInfo() {
		EditAtticInfoPageDAO dao = new EditAtticInfoPageInfoImpl();
		
		return dao.getInfo();
	}

	private void setupButtonEvents() {
		setupRaffleAdminBtn();
		setupCustomerLookupBtn();
		setupGenerateReportsBtn();
		setupEditAtticInfoBtn();
		setupEditItemBtn();
		setupEditItemTypesBtn();
		setupEditMessageBtn();
	}

	private void setupRaffleAdminBtn() {
		raffleAdminBtn.setOnAction(e -> {
			WindowController.createPopupWindow(
					FXMLFactory.getRaffleAdminPage());
		});
	}

	private void setupCustomerLookupBtn() {
		customerLookupBtn.setOnAction(e -> {
			SignInPageResources.setupWithCustomerLookup();
			
			WindowController.createPopupWindow(
					FXMLFactory.getSignInPage());
		});
	}

	private void setupGenerateReportsBtn() {
		generateReportsBtn.setOnAction(e -> {
			WindowController.createPopupWindow(
					FXMLFactory.getGenerateReportsPage());
		});
	}

	private void setupEditAtticInfoBtn() {		
		editAtticInfoBtn.setOnAction(e -> {
			EditAtticInfoPageResources.initializeWithAtticInfo();
			
			WindowController.createPopupWindow(
					FXMLFactory.getEditAtticInfoPage());
		});
	}
	
	private void setupEditItemBtn() {
		editItemBtn.setOnAction(e -> {
			WindowController.createPopupWindow(
					FXMLFactory.getEditItemPage());
		});
	}

	private void setupEditItemTypesBtn() {
		editItemTypesBtn.setOnAction(e -> {
			WindowController.createPopupWindow(
					FXMLFactory.getEditItemTypesPage());
		});
	}

	private void setupEditMessageBtn() {
		editMessageBtn.setOnAction(e ->{
			EditAtticInfoPageResources.initializeWithAtticMessage();
			
			WindowController.createPopupWindow(
					FXMLFactory.getEditAtticInfoPage());
		});
		
	}
	
	private void setupAtticInfoField() {
		atticInfo.setEditable(false);
		AdminPageResources.bindInfoTextArea(atticInfo);
	}

}
