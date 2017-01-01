package com.billings.controllers;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;

import com.billings.events.interfaces.SignInPageEvents;
import com.billings.main.WindowController;
import com.billings.resources.CustomerInfoPageResources;
import com.billings.resources.SignInPageResources;
import com.billings.utils.FXMLFactory;
import com.billings.utils.Logger;
import com.billings.utils.Messages;

public class SignInPageController implements Initializable {

	@FXML
	Label selectEmployeeLabel;
	@FXML
	Label phoneNumberLbl;
	@FXML
	Label customerNameLbl;
	
	@FXML
	TextField customerPhoneNumber;
	@FXML
	TextField customerName;
	
	@FXML
	Button customerLoginBtn;
	@FXML
	Button newCustomerBtn;
	@FXML
	Button cancelSignInBtn;
	
	SignInPageEvents signInPageEvents;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		setDisplayTextForNodes();
		
		setupCancelBtnOnAction();
		setupNewBtnOnAction();
		setupSignInPageFunctions();
		setupSignInBtnOnAction();
		
		if (isNewButtonDisabled()) {
			removeNewButtonFromScene();
		}
		
	}
	
	private void setDisplayTextForNodes() {
		selectEmployeeLabel.setText(SignInPageResources.CUSTOMER_LOGIN_LABEL);
		phoneNumberLbl.setText(SignInPageResources.PHONE_NUMBER_LABEL);
		customerNameLbl.setText(SignInPageResources.CUSTOMER_NAME_LABEL);
		
		customerLoginBtn.setText(SignInPageResources.LOGIN_BTN);
		newCustomerBtn.setText(SignInPageResources.NEW_CUSTOMER_BTN);
		cancelSignInBtn.setText(SignInPageResources.CANCEL_BTN);
		
		customerName.setPromptText(SignInPageResources.CUSTOMER_NAME_INITIAL_TEXT);
	}
	
	private void setupCancelBtnOnAction() {
		cancelSignInBtn.setOnAction(e -> {
			closeThisWindow();
		});
	}
	
	private void closeThisWindow() {
		WindowController.closeNodeContainingWindow(cancelSignInBtn);
	}
	
	private void setupNewBtnOnAction() {
		newCustomerBtn.setOnAction(e -> {
			signInPageEvents.newCustomerEvent();
			closeThisWindow();
		});
	}
	
	
	private void setupSignInPageFunctions() {
		signInPageEvents = SignInPageResources.SIGN_IN_PAGE_EVENTS;
	}
	
	private void setupSignInBtnOnAction() {
		customerLoginBtn.setOnAction(e -> {
			boolean successfulSignIn = signInPageEvents.signInEvent(
					getCleanPhoneNumber(),
					customerName.getText());
			if (successfulSignIn) {
				closeThisWindow();
			}
		});
	}
	
	private String getCleanPhoneNumber() {
		String phoneNumber = customerPhoneNumber.getText();
		
		phoneNumber = phoneNumber.trim();
		
		if (phoneNumber.startsWith("1")) {
			phoneNumber = phoneNumber.substring(1);
		}
		
		phoneNumber = phoneNumber.replaceAll("[^0-9]", "");
		
		if (phoneNumber.length() > 0 && phoneNumber.length() != 10) {
			Logger.notifyUser(Messages.invalidPhoneNumber);
			phoneNumber = "";
		}
		
		return phoneNumber;
	}
	
	private boolean isNewButtonDisabled() {
		return SignInPageResources.DISABLE_NEW_BUTTON;
	}
	
	private void removeNewButtonFromScene() {
		Pane parentNode = (Pane)newCustomerBtn.getParent();
		ObservableList<Node> children = parentNode.getChildren();
		children.remove(newCustomerBtn);
	}

}
