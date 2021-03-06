package com.billings.controllers;

import java.net.URL;
import java.util.ResourceBundle;


import com.billings.main.WindowController;
import com.billings.resources.NotificationPageResources;


import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;

public class NotificationPageController implements Initializable {

	@FXML
	Label headerLbl;
	
	@FXML
	TextArea notificationMsg;
	
	@FXML
	Button submitBtn;
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		setupNodesWithTextValues();
		setupNotification();
		setupSubmitBtn();
	}
	
	private void setupNodesWithTextValues() {
		headerLbl.setText(NotificationPageResources.HEADER_LBL);
		notificationMsg.setText(NotificationPageResources.MESSAGE);
		submitBtn.setText(NotificationPageResources.SUBMIT_BTN);
	}
	
	private void setupNotification() {
		notificationMsg.setEditable(false);
		notificationMsg.setWrapText(true);
	}
	
	private void setupSubmitBtn() {
		submitBtn.setOnAction(e -> {
			WindowController.closeNodeContainingWindow(submitBtn);
		});
	}
	

}
