package com.billings.controllers;

import java.net.URL;
import java.util.ResourceBundle;

import com.billings.jdbc.dao.AdminManagementDAO;
import com.billings.jdbc.dao.AdminManagementImpl;
import com.billings.main.WindowController;
import com.billings.resources.AdminManagementResources;
import com.billings.utils.Logger;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class AdminManagementController implements Initializable {
	
	@FXML
	Label headerLbl;
	@FXML
	Label usernameLbl;
	@FXML
	Label passwordLbl;
	@FXML
	Label confirmationPasswordLbl;

	@FXML
	TextField username;
	
	@FXML
	PasswordField password;
	@FXML
	PasswordField confirmationPassword;
	
	@FXML
	Button saveBtn;
	@FXML
	Button cancelBtn;
	
	private AdminManagementDAO dao;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		setupInitialValues();
		
		initializeDAO();		
		setupSaveBtn();
		cancelBtn();
	}

	private void setupInitialValues() {
		headerLbl.setText(AdminManagementResources.HEADER_LBL);
		usernameLbl.setText(AdminManagementResources.USERNAME_LBL);
		passwordLbl.setText(AdminManagementResources.PASSWORD_LBL);
		confirmationPasswordLbl.setText(AdminManagementResources.CONFIRMATION_PASSWORD_LBL);
		saveBtn.setText(AdminManagementResources.SAVE_BTN);
		cancelBtn.setText(AdminManagementResources.CANCEL_BTN);
	}
	
	private void initializeDAO() {
		dao = new AdminManagementImpl();
	}

	private void setupSaveBtn() {
		saveBtn.setOnAction(e -> {
			if (fieldsAreValid()) {
				makeAdmin();
				notifyAdminSuccess();
				close();
			}
		});
	}

	private boolean fieldsAreValid() {
		if (!isUsernameAvailable())
			return false;
		if (!arePasswordsMatching())
			return false;
		
		return isValidPassword();
	}

	private boolean isUsernameAvailable() {
		return dao.isUsernameAvailable(username.getText());
	}

	private boolean arePasswordsMatching() {
		String targetPassword = password.getText();
		String confirmation = confirmationPassword.getText();
		
		if (!targetPassword.equals(confirmation)) {
			Logger.notifyUser(AdminManagementResources.PASSWORDS_MUST_MATCH);
			return false;
		}
		
		return true;
	}

	private boolean isValidPassword() {
		String pwd = password.getText();
		
		if (pwd.length() < 8 ||
				pwd.matches("[^0-9]") ||
				pwd.matches("[^a-zA-Z]")) {
			Logger.notifyUser(AdminManagementResources.INVALID_PASSWORD);
			
			return false;
		}
		
		return true;
	}
	
	private void makeAdmin() {
		dao.makeAdmin(AdminManagementResources.getPersonId(), 
				username.getText(), password.getText());
		
		AdminManagementResources.setAdminStatus(true);
	}

	private void notifyAdminSuccess() {
		Logger.notifyUser(AdminManagementResources.ADMIN_PRIVILEGES_GRANTED);
	}

	private void cancelBtn() {
		cancelBtn.setOnAction(e -> {
			close();
		});
	}
	
	private void close() {
		WindowController.closeNodeContainingWindow(cancelBtn);
	}

}
