package com.billings.controllers;

import java.net.URL;
import java.util.ResourceBundle;

import com.billings.resources.AddRemoveContactsPageResources;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class AddRemoveContactsPageController implements Initializable {
	
	@FXML
	TableView contactTbl;

	@FXML
	TableColumn contactTblColumn;

	@FXML
	Label customerInfoHeaderLbl;
	@FXML
	Label companyLbl;
	@FXML
	Label contactInfoLbl;
	@FXML
	Label lastNameLbl;
	@FXML
	Label firstNameLbl;

	@FXML
	TextField firstName;
	@FXML
	TextField lastName;
	@FXML
	TextField company;
	@FXML
	TextField contactInfo;

	@FXML
	Button submitBtn;
	@FXML
	Button deleteBtn;
	@FXML
	Button newContactBtn;
	@FXML
	Button cancelBtn;
	
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		setupNodesWithTextValues();
	}
	
	private void setupNodesWithTextValues() {
		contactTblColumn.setText(AddRemoveContactsPageResources.CONTACT_TBL_COLUMN);
		customerInfoHeaderLbl.setText(AddRemoveContactsPageResources.CUSTOMER_INFO_HEADER_LBL);
		companyLbl.setText(AddRemoveContactsPageResources.COMPANY_LBL);
		contactInfoLbl.setText(AddRemoveContactsPageResources.CONTACT_INFO_LBL);
		lastNameLbl.setText(AddRemoveContactsPageResources.LAST_NAME_LBL);
		firstNameLbl.setText(AddRemoveContactsPageResources.FIRST_NAME_LBL);
		contactInfo.setText(AddRemoveContactsPageResources.CONTACT_INFO_LBL);
		submitBtn.setText(AddRemoveContactsPageResources.SUBMIT_BTN);
		deleteBtn.setText(AddRemoveContactsPageResources.DELETE_BTN);
		newContactBtn.setText(AddRemoveContactsPageResources.NEW_CONTACT_BTN);
		cancelBtn.setText(AddRemoveContactsPageResources.CANCEL_BTN);
	}

}
