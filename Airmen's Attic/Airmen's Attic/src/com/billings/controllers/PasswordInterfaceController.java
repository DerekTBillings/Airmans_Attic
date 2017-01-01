package com.billings.controllers;

import java.net.URL;
import java.util.ResourceBundle;









import com.billings.events.interfaces.PasswordInterfacePageEvents;
import com.billings.main.WindowController;
import com.billings.resources.CommonResources;
import com.billings.resources.PasswordInterfaceResources;



import com.billings.utils.Logger;
import com.billings.utils.Messages;
import com.billings.utils.InputValidation;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class PasswordInterfaceController implements Initializable {

	@FXML
	Label headerLbl;
	@FXML
	TextField username;
	@FXML
	PasswordField password;
	@FXML
	Button submitBtn;
	@FXML
	Button cancelBtn;
	@FXML
	Label usernameLbl;
	@FXML
	Label passwordLbl;
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		setupNodesWithText();
		setupSubmitBtn();
		setupCancelBtn();
		
		username.requestFocus();
	}
	
	private void setupNodesWithText() {
		headerLbl.setText(PasswordInterfaceResources.HEADER_LBL);
		username.setPromptText(PasswordInterfaceResources.USERNAME_PROMPT);
		password.setPromptText(PasswordInterfaceResources.PASSWORD_PROMPT);
		submitBtn.setText(PasswordInterfaceResources.SUBMIT_BTN);
		cancelBtn.setText(PasswordInterfaceResources.CANCEL_BTN);
		usernameLbl.setText(PasswordInterfaceResources.USERNAME_LBL);
		passwordLbl.setText(PasswordInterfaceResources.PASSWORD_LBL);
	}
	
	private void setupSubmitBtn() {
		PasswordInterfacePageEvents events = PasswordInterfaceResources.getPageEvents();
		
		submitBtn.setOnAction(e -> {
			setFocusIfEmpty(username);
			setFocusIfEmpty(password);
			
			if (!isEmpty(username) && !isEmpty(password)) {
				String user = username.getText();
				String pass = password.getText();
				boolean success = false;
				
				success = events.submit(user, pass);
				
				if (success) {
					if (PasswordInterfaceResources.doAnnotateResults) {
						annotateSuccess();
					}
					
					if (PasswordInterfaceResources.doPageRedirect) {
						pageRedirect();
					}
					
					closeWindow();
				} else {
					Logger.notifyUser(Messages.INVALID_USERNAME_OR_PASSWORD);
				}
			} 
		});
	}	
	
	private boolean isEmpty(TextField inputField) {
		String fieldText = inputField.getText();
		
		if (fieldText.isEmpty()) {
			return true;
		
		} else {
			return false;
		}
	}
	
	private void setFocusIfEmpty(TextField inputField) {
		String inputText = inputField.getText();
		
		if (inputText.isEmpty()) {
			InputValidation.addErrorFormat(inputField);
		} else {
			InputValidation.removeErrorFormat(inputField);
		}
	}
	
	private void annotateSuccess() {
		CommonResources.annotateSignedIntoSystem();
	}
	
	private void pageRedirect() {
		WindowController.createPopupWindow(
				PasswordInterfaceResources.getPageRedirect());
	}
	
	private void setupCancelBtn() {
		cancelBtn.setCancelButton(true);
		cancelBtn.setOnAction(e -> {
			closeWindow();
		});
		
	}
	
	private void closeWindow() {
		WindowController.closeNodeContainingWindow(cancelBtn);
	}

}
