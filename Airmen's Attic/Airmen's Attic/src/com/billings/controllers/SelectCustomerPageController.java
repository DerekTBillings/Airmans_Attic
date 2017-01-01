package com.billings.controllers;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TableView.TableViewSelectionModel;
import javafx.scene.control.cell.PropertyValueFactory;

import com.billings.events.interfaces.SelectCustomerPageEvents;
import com.billings.jdbc.dto.FoundCustomer;
import com.billings.main.WindowController;
import com.billings.resources.SelectCustomerPageResources;
import com.billings.utils.FXMLFactory;
import com.billings.utils.Logger;

public class SelectCustomerPageController implements Initializable{

	@FXML
	Label pageHeaderLbl;

	@FXML
	TableView foundCustomersTbl;

	@FXML
	TableColumn customerId;
	@FXML
	TableColumn customer;
	@FXML
	TableColumn sponsor;
	@FXML
	TableColumn contact;

	@FXML
	Button selectBtn;
	@FXML
	Button cancelBtn;
	
	SelectCustomerPageEvents pageEvents;
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		pageEvents = SelectCustomerPageResources.pageEvents;
		
		setupNodesWithTextValues();
		
		setupTableColumnMapping();
		populateFoundCustomersTbl();
		
		setupSelectBtn();
		setupCancelBtn();
	}
	
	private void setupNodesWithTextValues() {
		pageHeaderLbl.setText(SelectCustomerPageResources.PAGE_HEADER_LBL);
		customer.setText(SelectCustomerPageResources.CUSTOMER);
		sponsor.setText(SelectCustomerPageResources.SPONSOR);
		contact.setText(SelectCustomerPageResources.CONTACT);
		selectBtn.setText(SelectCustomerPageResources.SELECT_BTN);
		cancelBtn.setText(SelectCustomerPageResources.CANCEL_BTN);
	}

	private void setupTableColumnMapping() {
		setupColumnMappingLink(customerId, "personId");
		setupColumnMappingLink(customer, "customerName");
		setupColumnMappingLink(sponsor, "sponsorName");
		setupColumnMappingLink(contact, "contactInfo");
	}
	
	private void setupColumnMappingLink(TableColumn column, String foundCustomerAttribute) {
		column.setCellValueFactory(
			new PropertyValueFactory<FoundCustomer, String>(foundCustomerAttribute)
		);
	}
	
	private void populateFoundCustomersTbl() {
		List<FoundCustomer> foundCustomers = SelectCustomerPageResources.getFoundCustomers();
		
		ObservableList<FoundCustomer> customersForTbl =
				foundCustomersTbl.getItems();
		
		customersForTbl.addAll(foundCustomers);
	}
	
	private void setupSelectBtn() {
		selectBtn.setOnAction(e -> {
			TableViewSelectionModel<FoundCustomer> selectedRow = foundCustomersTbl.getSelectionModel();
			FoundCustomer selectedCustomer = selectedRow.getSelectedItem();
			
			if (selectedCustomer != null) {
				pageEvents.submit(selectedCustomer);
				closePage();
			}
		});
	}
	
	private void setupCancelBtn() {
		cancelBtn.setOnAction(e -> {
			WindowController.createPopupWindow(
					FXMLFactory.getSignInPage());
			
			closePage();
		});
	}
	
	private void closePage() {
		WindowController.closeNodeContainingWindow(cancelBtn);
	}
	
}
