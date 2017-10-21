package com.billings.jdbc.dao;

import com.billings.jdbc.sql.SignInPageSQL;
import com.billings.resources.SignInPageResources;
import com.billings.utils.Logger;
import com.billings.utils.Messages;
import com.billings.utils.SQLStatementUtils;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;

public class SignInPageCommon extends SignInPageDAO {

	@Override
	public void signIn(int personId) {
		String role = SignInPageResources.ROLE_CUSTOMER;
		
		if (!super.isCustomerSignedIn(personId, role)) {
			if (areRulesAccepted() && isNoteAccepted(personId)) 
				Logger.notifyUser(signIn(personId, role));
			
		} else {
			Logger.notifyUser(Messages.CUSTOMER_ALREADY_SIGNED_IN);
		}
	}

	private boolean areRulesAccepted() {
		return confirmPrompt(Messages.RULES_ACKNOWLEDGEMENT);
	}
	
	private boolean confirmPrompt(String message) {
		Alert alert = getConfirmPrompt(message);
		
		alert.showAndWait();

		return alert.getResult() == ButtonType.YES;
	}

	private Alert getConfirmPrompt(String message) {
		return new Alert(AlertType.CONFIRMATION, message, 
				ButtonType.YES, ButtonType.NO);
	}

	private boolean isNoteAccepted(int personId) {
		String note = getCustomerNote(personId);
		
		if (!note.isEmpty())
			return confirmPrompt("Admin Note:\r\n"+note);
		
		return true;
	}

	private String getCustomerNote(int personId) {
		EditAtticInfoPageDAO dao = new EditAtticInfoPagePersonImpl(personId);
		
		return dao.getInfo();
	}

	private String signIn(int personId, String role) {
		signCustomerIn(personId, role);
		
		String name = getCustomerName(personId);
		
		return String.format(Messages.SUCCESSFUL_SIGN_IN, name, "customer");
	}

	private void signCustomerIn(int personId, String role) {
		SQLStatementUtils.executeInsert(SignInPageSQL.signIn, personId, role);
	}

	private String getCustomerName(int personId) {
		String query = SignInPageSQL.getCustomerName;
		
		return (String)SQLStatementUtils.executeQueryForSingleCell(
				query, String.class, personId);
	}

}
