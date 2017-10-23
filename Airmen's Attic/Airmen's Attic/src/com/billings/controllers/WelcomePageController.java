package com.billings.controllers;

import java.net.URL;
import java.util.ResourceBundle;

import com.billings.jdbc.dao.EditAtticInfoPageDAO;
import com.billings.jdbc.dao.EditAtticInfoPageMessageImpl;
import com.billings.jdbc.dao.EditAtticInfoPagePersonImpl;
import com.billings.main.WindowController;
import com.billings.resources.PasswordInterfaceResources;
import com.billings.resources.SignInPageResources;
import com.billings.resources.WelcomePageResources;
import com.billings.utils.Common;
import com.billings.utils.FXMLFactory;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;

public class WelcomePageController implements Initializable {

	@FXML
	private Label headerLabel;
	@FXML
	private TextArea announcementsTextArea;
	@FXML
	private Button customerSignInBtn;
	@FXML
	private Button checkOutBtn;
	@FXML
	private Button volunteerSignInBtn;
	@FXML
	private Button raffleBtn;
	@FXML
	private Button volunteerSignOutBtn;
	@FXML
	private Button adminPageBtn;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		setupHeaderLabel();
		setupCustomerSignInBtn();
		setupCheckOutBtn();
		setupVolunteerSignInBtn();
		setupRaffleBtn();
		setupAdminPageBtn();
		setupVolunteerSignOutBtn();
		setupAnnouncementsTextArea();
	}
	
	private void setupHeaderLabel() {
		headerLabel.setText(WelcomePageResources.WELCOME_PAGE_HEADER);
	}
	
	private void setupCustomerSignInBtn() {
		customerSignInBtn.setText(WelcomePageResources.CUSTOMER_SIGN_IN_BTN_TEXT);
		customerSignInBtn.setOnAction(e -> {
			SignInPageResources.setupWithCustomerSignIn();
			openSignInPage();
		});
	}
	
	private void openSignInPage() {
		WindowController.createPopupWindow(
				FXMLFactory.getSignInPage());
	}
	
	private void setupCheckOutBtn() {
		checkOutBtn.setText(WelcomePageResources.CHECK_OUT_BTN_TEXT);
		checkOutBtn.setOnAction(e -> {
			SignInPageResources.setupWithCustomerCheckout();
			
			openCustomerCheckOutPage();
		});
	}
	
	private void openCustomerCheckOutPage() {
		WindowController.createPopupWindow(
				FXMLFactory.getSignInPage());
	}
	
	private void setupVolunteerSignInBtn() {
		volunteerSignInBtn.setText(WelcomePageResources.VOLUNTEER_BTN_TEXT);
		volunteerSignInBtn.setOnAction(e -> {
			SignInPageResources.setupWithVolunteerSignIn();
			openSignInPage();
		});
	}
	
	private void setupRaffleBtn() {
		raffleBtn.setText(WelcomePageResources.RAFFLE_BTN_TEXT);
		
		raffleBtn.setOnAction(e -> {
			SignInPageResources.setupWithRaffleSignIn();
			openSignInPage();
		});
	}
	
	private void setupAdminPageBtn() {
		adminPageBtn.setText(WelcomePageResources.ADMIN_PAGE_BTN_TEXT);
		
		adminPageBtn.setOnAction(e -> {
			PasswordInterfaceResources.setupPageForAdmins();
			PasswordInterfaceResources.setPageRedirect(
					FXMLFactory.getAdminPage());
			
			WindowController.createPopupWindow(
					FXMLFactory.getPasswordInterfacePage());
		});
	}
	
	private void setupVolunteerSignOutBtn() {
		volunteerSignOutBtn.setText(WelcomePageResources.VOLUNTEER_SIGN_OUT_BTN_TEXT);
		
		volunteerSignOutBtn.setOnAction(e -> {
			openVolunteerSignOutPage();
		});
	}
	
	private void openVolunteerSignOutPage() {
		SignInPageResources.setupWithVolunteerSignOut();
		WindowController.createPopupWindow(
				FXMLFactory.getSignInPage());
	}
	
	private void setupAnnouncementsTextArea() {
		announcementsTextArea.setEditable(false);
		announcementsTextArea.setText(getAtticMessage());
		WelcomePageResources.bindMessageTextArea(announcementsTextArea);
	}
	
	private String getAtticMessage() {
		EditAtticInfoPageDAO dao = new EditAtticInfoPageMessageImpl();
		
		return dao.getInfo();
	}
	
	
}
