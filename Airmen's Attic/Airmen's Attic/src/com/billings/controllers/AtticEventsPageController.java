package com.billings.controllers;

import java.net.URL;
import java.util.ResourceBundle;

import com.billings.resources.AtticEventsPageResources;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class AtticEventsPageController implements Initializable {

	@FXML
	TableView eventsTable;

	@FXML
	TableColumn eventName;
	@FXML
	TableColumn eventStart;
	@FXML
	TableColumn eventEnd;
	@FXML
	TableColumn showCustomer;

	@FXML
	Button newEventBtn;
	@FXML
	Button editEventBtn;
	@FXML
	Button removeEventBtn;
	@FXML
	Button closeBtn;

	@FXML
	Label atticEventsHeaderLbl;
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		setupNodesWithTextValues();
	}
	
	private void setupNodesWithTextValues() {
		eventName.setText(AtticEventsPageResources.EVENT_NAME);
		eventStart.setText(AtticEventsPageResources.EVENT_START);
		eventEnd.setText(AtticEventsPageResources.EVENT_END);
		showCustomer.setText(AtticEventsPageResources.SHOW_CUSTOMER);
		newEventBtn.setText(AtticEventsPageResources.NEW_EVENT_BTN);
		editEventBtn.setText(AtticEventsPageResources.EDIT_EVENT_BTN);
		removeEventBtn.setText(AtticEventsPageResources.REMOVE_EVENT_BTN);
		closeBtn.setText(AtticEventsPageResources.CLOSE_BTN);
		atticEventsHeaderLbl.setText(AtticEventsPageResources.ATTIC_EVENTS_HEADER_LBL);
	}

}
