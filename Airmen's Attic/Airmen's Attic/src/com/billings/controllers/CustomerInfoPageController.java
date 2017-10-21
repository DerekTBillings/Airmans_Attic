package com.billings.controllers;

import java.net.URL;
import java.util.HashSet;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Set;

import com.billings.events.implementation.CustomerInfoPageEditCustomer;
import com.billings.events.interfaces.CustomerInfoPageEvents;
import com.billings.jdbc.dao.AdminManagementDAO;
import com.billings.jdbc.dao.AdminManagementImpl;
import com.billings.jdbc.dao.CustomerInfoPageSetupDAO;
import com.billings.jdbc.dao.CustomerInfoPageSetupImpl;
import com.billings.jdbc.dto.Person;
import com.billings.main.WindowController;
import com.billings.resources.AdminManagementResources;
import com.billings.resources.CustomerInfoPageResources;
import com.billings.resources.EditAtticInfoPageResources;
import com.billings.resources.SignInPageResources;
import com.billings.utils.FXMLFactory;
import com.billings.utils.InputValidation;
import com.billings.utils.Logger;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;

public class CustomerInfoPageController implements Initializable {

	@FXML
	Label newContactHeader;
	@FXML
	Label firstNameLbl;
	@FXML
	Label lastNameLbl;
	@FXML
	Label rankLbl;
	@FXML
	Label cellPhoneLbl;
	@FXML
	Label dependentCheckLbl;

	@FXML
	TextField firstName;
	@FXML
	TextField lastName;
	@FXML
	TextField cellPhone;
	
	@FXML
	ChoiceBox<String> rank;
	
	@FXML
	CheckBox dependentStatus;
	
	@FXML
	Button submitBtn;
	@FXML
	Button cancelBtn;
	@FXML
	Button editSponsorInfoBtn;
	@FXML
	Button archiveCustomerBtn;
	@FXML
	Button toggleAdminBtn;
	@FXML
	Button addNoteBtn;
	
	private static Person person;
	
	private CustomerInfoPageEvents pageEvents;
	
	private Set<Node> requiredFieldsList;
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		createOrLoadPerson();
		
		pageEvents = CustomerInfoPageResources.CUSTOMER_INFO_PAGE_EVENTS;
		
		setupNodesWithTextValues();
		
		removeNodesIfNecessary();
		
		setupSubmitBtn();
		setupEditSponsorInfoBtn();
		setupCancelBtn();
		setupArchiveCustomerBtn();
		setupAddNoteBtn();
		setupToggleAdminBtn();
		
		populateRankList();
		
		createRequiredFieldsList();
	}

	private void createOrLoadPerson() {
		if (CustomerInfoPageResources.LOAD_PERSON) {
			person = getPersonInfo();
			
			if (person != null) {
				loadPersonInfo();
			}
		} else {
			person = new Person();
		}
	}
	
	private Person getPersonInfo() {
		int personId = CustomerInfoPageResources.getLoadedCustomerId();
		
		CustomerInfoPageSetupDAO dao = new CustomerInfoPageSetupImpl();
		Person person = dao.getPersonInfoById(personId);

		return person;
	}
	
	private void loadPersonInfo() {
		String firstName = person.getFirstName();
		String lastName = person.getLastName();
		String cellPhone = person.getCellPhone();
		String rank = person.getRank();
		
		String dependentStatus = person.getDependentStatus();
		
		this.firstName.setText(firstName);
		this.lastName.setText(lastName);
		this.cellPhone.setText(cellPhone);
		this.rank.setValue(rank);
		this.dependentStatus.setText(dependentStatus);
		
	}
	
	private void setupNodesWithTextValues() {
		newContactHeader.setText(CustomerInfoPageResources.NEW_CONTACT_HEADER);
		firstNameLbl.setText(CustomerInfoPageResources.FIRST_NAME_LBL);
		lastNameLbl.setText(CustomerInfoPageResources.LAST_NAME_LBL);
		rankLbl.setText(CustomerInfoPageResources.RANK_LBL);
		cellPhoneLbl.setText(CustomerInfoPageResources.CELL_PHONE_LBL);
		dependentCheckLbl.setText(CustomerInfoPageResources.DEPENDENT_CHECK_LBL);
		submitBtn.setText(CustomerInfoPageResources.SUBMIT_BTN);
		cancelBtn.setText(CustomerInfoPageResources.CANCEL_BTN);
		editSponsorInfoBtn.setText(CustomerInfoPageResources.EDIT_SPONSOR_INFO_BTN);
		archiveCustomerBtn.setText(CustomerInfoPageResources.ARCHIVE_CUSTOMER_BTN);
		toggleAdminBtn.setText(CustomerInfoPageResources.TOGGLE_ADMIN_BTN);
		addNoteBtn.setText(CustomerInfoPageResources.ADD_NOTE_BTN);
	}
	
	private void removeNodesIfNecessary() {
		if (CustomerInfoPageResources.REMOVE_DELETE_BTN)
			removeNode(archiveCustomerBtn);
		if (CustomerInfoPageResources.REMOVE_EDIT_SPONSOR_BTN)
			removeNode(editSponsorInfoBtn);
		if (CustomerInfoPageResources.REMOVE_ADD_NOTE_BTN)
			removeNode(addNoteBtn);
		if (CustomerInfoPageResources.REMOVE_TOGGLE_ADMIN_BTN)
			removeNode(toggleAdminBtn);
		
		if (CustomerInfoPageResources.REMOVE_DEPENDENT_CHECK) {
			removeNode(dependentCheckLbl);
			removeNode(dependentStatus);
		}
	}
	
	private void removeNode(Node nodeToRemove) {
		Pane parentNode = (Pane)nodeToRemove.getParent(); 
		
		parentNode.getChildren()
			.remove(nodeToRemove);
	}

	private void setupSubmitBtn() {
		submitBtn.setOnAction(e -> {
			submitBtnOnAction();
		});
	}
	
	private void submitBtnOnAction() {
		if (isSponsorRequiredAndComplete() &&
				fieldsAreCorrect()) {
			populatePersonWithValues();
			boolean success = pageEvents.submitEvent(person);
			if (success) {
				closePage();
			}
		}
	}
	
	private boolean isSponsorRequiredAndComplete() {
		Person sponsor = person.getSponsor();
		
		if (dependentStatus.isSelected()){
			
			if (sponsor == null) {
				InputValidation.addErrorFormat(editSponsorInfoBtn);
				return false;
			} else if (sponsor.getPersonId() != 0) {
				CustomerInfoPageResources.setupFoundSponsorId();
			}
			
		} 
		
		InputValidation.removeErrorFormat(editSponsorInfoBtn);
		return true;
	}
	
	private boolean fieldsAreCorrect() {
		boolean isValid = true;
		
		InputValidation validation = new InputValidation(requiredFieldsList);
		
		if (!validation.validateName(firstName)) isValid = false;
		if (!validation.validateName(lastName)) isValid = false;
		if (!validation.validatePhone(cellPhone)) isValid = false;
		if (!validation.validateRank(rank)) isValid = false;
		
		return isValid;
	}
	
	private void populatePersonWithValues() {
		String firstNm = capitalizeFirstLetter(firstName.getText());
		String lastNm = capitalizeFirstLetter(lastName.getText());
		
		person.setFirstName(firstNm);
		person.setLastName(lastNm);
		person.setRank(rank.getValue());
		person.setCellPhone(cleanPhoneNumber(
				cellPhone.getText()));
		
		if (dependentStatus.isSelected()) {
			person.setDependentStatus("Y");
		} else {
			person.setDependentStatus("N");
		}
	}
	
	private String capitalizeFirstLetter(String name) {
		char firstChar = name.charAt(0);
		firstChar = Character.toUpperCase(firstChar);
		
		name = firstChar+name.substring(1);
		
		return name;
	}
	
	private String cleanPhoneNumber(String original) {
		return original.replaceAll("[^0-9]", "");
	}
	
	private void setupEditSponsorInfoBtn() {
		editSponsorInfoBtn.setOnAction(e -> {
			InputValidation.removeErrorFormat(editSponsorInfoBtn);
			openSignInPage();
		});
	}
	
	private void openSignInPage() {
		SignInPageResources.setupWithSponsorLookup();
		WindowController.createPopupWindow(
				FXMLFactory.getSignInPage());
	}
	
	private void setupCancelBtn() {
		cancelBtn.setOnAction(e -> {
			closePage();
		});
	}
	
	private void closePage() {
		WindowController.closeNodeContainingWindow(cancelBtn);
	}
	
	private void setupArchiveCustomerBtn() {
		archiveCustomerBtn.setOnAction(e -> {
			int personId = getPersonId();
			String archiveStatus = person.getArchiveStatus();
			
			((CustomerInfoPageEditCustomer)pageEvents).toggleCustomerArchive(personId, archiveStatus);
			
			closePage();
		});
	}
	
	private void populateRankList() {
		rank.setItems(FXCollections.observableArrayList(
						CustomerInfoPageResources.RANK_LIST));
	}
	
	public static Person getPerson() {
		return person;
	}
	
	private void createRequiredFieldsList() {
		List<String> requiredFields = CustomerInfoPageResources.REQUIRED_FIELDS;
		int listSize = requiredFields.size();
		
		requiredFieldsList = new HashSet<Node>();
	}
	
	private void attemptToAddNodesToRequiredList(Node... nodes) {
		for (Node node : nodes) {
			if (isNodeRequired(node)) {
				requiredFieldsList.add(node);
			}
		}
	}
	
	private boolean isNodeRequired(Node node) {
		boolean required = false;
		String nodeId = node.getId();
		
		List<String> requiredFields = CustomerInfoPageResources.REQUIRED_FIELDS;
		
		if (requiredFields.contains(nodeId)) {
			required = true;
		}
		
		return required;
	}
	
	private void setupAddNoteBtn() {
		addNoteBtn.setOnAction(e -> {
			setupInfoPage();
			openEditInfoPage();
		});
	}

	private void setupInfoPage() {
		EditAtticInfoPageResources.initializeWithPerson(getPersonId());
	}

	private int getPersonId() {
		return person.getPersonId();
	}

	private void openEditInfoPage() {
		WindowController.createPopupWindow(
				FXMLFactory.getEditAtticInfoPage());
	}

	private void setupToggleAdminBtn() {
		toggleAdminBtn.setOnAction(e -> {
			if (person.isAdmin()) {
				removeAdmin();
				displayRemovalMessage();
			}
			else
				makeAdmin();
		});
	}

	private void removeAdmin() {
		AdminManagementDAO dao = new AdminManagementImpl();
		
		dao.removeAdmin(person.getPersonId());
		
		person.setAdmin(false);
	}
	
	private void displayRemovalMessage() {
		Logger.notifyUser(AdminManagementResources.ADMIN_PRIVILEGES_REMOVED);
	}
	
	private void makeAdmin() {
		AdminManagementResources.setPerson(person);
		
		WindowController.createPopupWindow(
				FXMLFactory.getAdminManagementPage());
	}
}
