package com.billings.controllers;

import java.net.URL;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Set;


import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;


import com.billings.events.implementation.CustomerInfoPageEditCustomer;
import com.billings.events.interfaces.CustomerInfoPageEvents;
import com.billings.jdbc.dao.CustomerInfoPageSetupDAO;
import com.billings.jdbc.dao.CustomerInfoPageSetupImpl;
import com.billings.jdbc.dto.Person;
import com.billings.main.WindowController;
import com.billings.resources.CustomerInfoPageResources;
import com.billings.resources.SignInPageResources;
import com.billings.utils.FXMLFactory;
import com.billings.utils.InputValidation;

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
	Label dobLbl;
	@FXML
	Label workPhoneLbl;
	@FXML
	Label cellPhoneLbl;
	@FXML
	Label organizationLbl;
	@FXML
	Label dependentCheckLbl;
	@FXML
	Label emailLbl;
	@FXML
	Label militaryIdExpDateLbl;

	@FXML
	TextField firstName;
	@FXML
	TextField lastName;
	@FXML
	TextField workPhone;
	@FXML
	TextField cellPhone;
	@FXML
	TextField organization;
	@FXML
	TextField email;
	
	@FXML
	DatePicker dateOfBirth;
	@FXML
	DatePicker militaryIdExpDate;
	
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
		String workPhone = person.getWorkPhone();
		String cellPhone = person.getCellPhone();
		String email = person.getEmail();
		String rank = person.getRank();
		String organization = person.getOrganization();

		LocalDate dateOfBirth = person.getBirthDate();
		LocalDate militaryIdExpDate = person.getMilitaryIdExpirationDate();
		
		String dependentStatus = person.getDependentStatus();
		
		this.firstName.setText(firstName);
		this.lastName.setText(lastName);
		this.workPhone.setText(workPhone);
		this.cellPhone.setText(cellPhone);
		this.email.setText(email);
		this.rank.setValue(rank);
		this.dateOfBirth.setValue(dateOfBirth);
		this.militaryIdExpDate.setValue(militaryIdExpDate);
		this.dependentStatus.setText(dependentStatus);
		this.organization.setText(organization);
		
	}
	
	private void setupNodesWithTextValues() {
		newContactHeader.setText(CustomerInfoPageResources.NEW_CONTACT_HEADER);
		firstNameLbl.setText(CustomerInfoPageResources.FIRST_NAME_LBL);
		lastNameLbl.setText(CustomerInfoPageResources.LAST_NAME_LBL);
		rankLbl.setText(CustomerInfoPageResources.RANK_LBL);
		dobLbl.setText(CustomerInfoPageResources.DOB_LBL);
		workPhoneLbl.setText(CustomerInfoPageResources.WORK_PHONE_LBL);
		cellPhoneLbl.setText(CustomerInfoPageResources.CELL_PHONE_LBL);
		organizationLbl.setText(CustomerInfoPageResources.ORGANIZATION_LBL);
		dependentCheckLbl.setText(CustomerInfoPageResources.DEPENDENT_CHECK_LBL);
		militaryIdExpDateLbl.setText(CustomerInfoPageResources.MILITARY_ID_EXP_DATE_LBL);
		emailLbl.setText(CustomerInfoPageResources.EMAIL_LBL);
		submitBtn.setText(CustomerInfoPageResources.SUBMIT_BTN);
		cancelBtn.setText(CustomerInfoPageResources.CANCEL_BTN);
		editSponsorInfoBtn.setText(CustomerInfoPageResources.EDIT_SPONSOR_INFO_BTN);
		archiveCustomerBtn.setText(CustomerInfoPageResources.ARCHIVE_CUSTOMER_BTN);
		dateOfBirth.setPromptText(CustomerInfoPageResources.DATE_PROMPT_TEXT);
		militaryIdExpDate.setPromptText(CustomerInfoPageResources.DATE_PROMPT_TEXT);
	}
	
	private void removeNodesIfNecessary() {
		if (CustomerInfoPageResources.REMOVE_DELTE_BTN) {
			removeNode(archiveCustomerBtn);
		}
		
		if (CustomerInfoPageResources.REMOVE_EDIT_SPONSOR_BTN) {
			removeNode(editSponsorInfoBtn);
		}
		
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
		
		if (!validation.validatePhone(workPhone)) isValid = false;
		if (!validation.validatePhone(cellPhone)) isValid = false;
		
		if (!validation.validateEmail(email)) isValid = false;
		
		if (!validation.validateBirthDate(dateOfBirth)) isValid = false;
		if (!validation.validateMilExpirationDate(militaryIdExpDate)) isValid = false;
		
		if (!validation.validateRank(rank)) isValid = false;
		
		if (!validation.validateOrganization(organization)) isValid = false;
		
		return isValid;
	}
	
	private void populatePersonWithValues() {
		String firstNm = capitalizeFirstLetter(firstName.getText());
		String lastNm = capitalizeFirstLetter(lastName.getText());
		
		person.setFirstName(firstNm);
		person.setLastName(lastNm);
		person.setEmail(email.getText());
		person.setOrganization(organization.getText());
		
		person.setRank(rank.getValue());
		person.setMilitaryIdExpirationDate(militaryIdExpDate.getValue());
		person.setBirthDate(dateOfBirth.getValue());
		
		person.setWorkPhone(cleanPhoneNumber(
				workPhone.getText()));
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
			int personId = person.getPersonId();
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
		
		attemptToAddNodesToRequiredList(firstName, lastName, workPhone, cellPhone,
			organization, email, dateOfBirth, 
			militaryIdExpDate, rank);
		
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
}
