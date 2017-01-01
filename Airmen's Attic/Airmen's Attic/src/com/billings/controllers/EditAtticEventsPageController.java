package com.billings.controllers;

import java.net.URL;
import java.util.ResourceBundle;

import com.billings.resources.EditAtticEventsPageResources;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class EditAtticEventsPageController implements Initializable {

	@FXML
	Label editAtticEventHeaderLbl;
	@FXML
	Label eventNameLbl;
	@FXML
	Label startDateLbl;
	@FXML
	Label endDateLbl;
	@FXML
	Label showToCustomerLbl;

	@FXML
	TextField eventName;

	@FXML
	DatePicker startDate;
	@FXML
	DatePicker endDate;

	@FXML
	CheckBox showToCustomer;

	@FXML
	Button saveBtn;
	@FXML
	Button cancelBtn;
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		setupNodesWithTextValues();
	}
	
	private void setupNodesWithTextValues() {
		editAtticEventHeaderLbl.setText(EditAtticEventsPageResources.EDIT_ATTIC_EVENT_HEADER_LBL);
		eventNameLbl.setText(EditAtticEventsPageResources.EVENT_NAME_LBL);
		startDateLbl.setText(EditAtticEventsPageResources.START_DATE_LBL);
		endDateLbl.setText(EditAtticEventsPageResources.END_DATE_LBL);
		showToCustomerLbl.setText(EditAtticEventsPageResources.SHOW_TO_CUSTOMER_LBL);
		saveBtn.setText(EditAtticEventsPageResources.SAVE_BTN);
		cancelBtn.setText(EditAtticEventsPageResources.CANCEL_BTN);
	}

}
