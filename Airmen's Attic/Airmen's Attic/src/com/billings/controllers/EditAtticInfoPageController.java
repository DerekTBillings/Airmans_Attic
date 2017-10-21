package com.billings.controllers;

import java.net.URL;
import java.util.ResourceBundle;

import com.billings.events.interfaces.EditAtticInfoPageEvents;
import com.billings.jdbc.dao.EditAtticInfoPageDAO;
import com.billings.main.WindowController;
import com.billings.resources.EditAtticInfoPageResources;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;

public class EditAtticInfoPageController implements Initializable {

	@FXML
	TextArea atticInfo;

	@FXML
	Button updateBtn;
	@FXML
	Button cancelBtn;
	
	EditAtticInfoPageDAO dao;
	EditAtticInfoPageEvents events;
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		getDAO();
		getEvents();
		setupNodesWithTextValues();
		setupUpdateBtn();
		setupCancelBtn();
	}

	private void getDAO() {
		dao = EditAtticInfoPageResources.getDAO();
	}

	private void getEvents() {
		events = EditAtticInfoPageResources.getEvents();
	}

	private void setupNodesWithTextValues() {
		updateBtn.setText(EditAtticInfoPageResources.UPDATE_BTN);
		cancelBtn.setText(EditAtticInfoPageResources.CANCEL_BTN);
		atticInfo.setText(getInfo());
	}
	
	private String getInfo() {
		return dao.getInfo();
	}

	private void setupUpdateBtn() {
		updateBtn.setOnAction(e -> {
			String update = atticInfo.getText();
			
			dao.updateInfo(update);
			events.updateDisplay(update);
			
			closeWindow();
		});
	}

	private void setupCancelBtn() {
		cancelBtn.setOnAction(e -> {
			closeWindow();
		});
	}

	private void closeWindow() {
		WindowController.closeNodeContainingWindow(cancelBtn);
	}
	

}
