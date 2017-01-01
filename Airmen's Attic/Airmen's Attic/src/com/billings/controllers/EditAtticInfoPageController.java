package com.billings.controllers;

import java.net.URL;
import java.util.ResourceBundle;

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
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		setupNodesWithTextValues();
	}
	
	private void setupNodesWithTextValues() {
		updateBtn.setText(EditAtticInfoPageResources.UPDATE_BTN);
		cancelBtn.setText(EditAtticInfoPageResources.CANCEL_BTN);
	}

}
