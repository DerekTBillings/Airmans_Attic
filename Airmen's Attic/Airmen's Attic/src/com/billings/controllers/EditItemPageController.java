package com.billings.controllers;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import com.billings.jdbc.dao.EditItemPageDAO;
import com.billings.jdbc.dao.EditItemPageImpl;
import com.billings.jdbc.dto.CheckoutItem;
import com.billings.resources.EditItemPageResources;
import com.billings.utils.Common;
import com.billings.utils.Logger;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class EditItemPageController implements Initializable {
	
	@FXML
	Label header;
	@FXML
	Label itemUpdateHeader;
	@FXML
	Label itemNameLbl;
	@FXML
	Label itemTypeLbl;
	@FXML
	Label newItemTypeLbl;
	@FXML
	Label archivedLbl;

	@FXML
	TableView itemsTbl;

	@FXML
	TableColumn itemCol;
	@FXML
	TableColumn typeCol;

	@FXML
	TextField itemName;
	@FXML
	TextField newItemType;

	@FXML
	ChoiceBox<String> itemType;

	@FXML
	Button saveBtn;
	
	@FXML
	CheckBox archivedStatus;
	
	List<CheckoutItem> items;
	List<String> itemTypes;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		setupInitialValues();
		getItemTypes();
		addItemTypes();
		getItems();
		mapColumns();
		addItemsToTable();
		setupTableOnClick();
		setupSaveBtn();
	}

	private void setupInitialValues() {
		header.setText(EditItemPageResources.header);
		itemUpdateHeader.setText(EditItemPageResources.itemUpdateHeader);
		itemNameLbl.setText(EditItemPageResources.itemNameLbl);
		itemTypeLbl.setText(EditItemPageResources.itemTypeLbl);
		newItemTypeLbl.setText(EditItemPageResources.newItemTypeLbl);
		itemCol.setText(EditItemPageResources.itemCol);
		typeCol.setText(EditItemPageResources.typeCol);
		saveBtn.setText(EditItemPageResources.saveBtn);
		archivedLbl.setText(EditItemPageResources.archivedLbl);
	}

	private void getItemTypes() {
		EditItemPageDAO dao = new EditItemPageImpl();
		
		this.itemTypes = dao.getItemTypes();
	}

	private void addItemTypes() {
		itemType.getItems().addAll(itemTypes);		
	}
	
	private void getItems() {
		EditItemPageDAO dao = new EditItemPageImpl();
		
		this.items = dao.getItems();
	}

	private void mapColumns() {
		mapAttributeToColumn(itemCol, "itemName");
		mapAttributeToColumn(typeCol, "itemType");
	}

	private void mapAttributeToColumn(TableColumn column, String attribute) {
		column.setCellValueFactory(getPropertyValue(attribute));
	}
	
	private PropertyValueFactory getPropertyValue(String attribute) {
		return new PropertyValueFactory<CheckoutItem, String>(attribute);
	}

	private void addItemsToTable() {
		itemsTbl.getItems().addAll(items);
	}
	
	private void setupTableOnClick() {
		itemsTbl.setOnMouseClicked(e -> {
			if (Common.isTargetAColumn(e.getTarget())) {
				CheckoutItem item = getSelectedItem();
				
				itemName.setText(item.getItemName());
				itemType.setValue(item.getItemType());
			}
		});
	}

	private CheckoutItem getSelectedItem() {
		return (CheckoutItem)itemsTbl.getSelectionModel().getSelectedItem();
	}

	private void setupSaveBtn() {
		saveBtn.setOnAction(e -> {
			CheckoutItem selectedItem = getSelectedItem();
			
			if (selectedItem != null)
				saveChanges(selectedItem);
		});
	}

	private void saveChanges(CheckoutItem selectedItem) {
		updateItem(selectedItem);
		
		EditItemPageDAO dao = new EditItemPageImpl();
		
		boolean success = dao.saveChanges(selectedItem);
		if (success) notifyUserOfUpdate();
	}

	private void updateItem(CheckoutItem selectedItem) {
		selectedItem.setItemName(itemName.getText());
		selectedItem.setArchived(archivedStatus.isSelected());
		
		String selectedType = newItemType.getText();
		
		if (selectedType.isEmpty()) {
			saveItemType(selectedType);
			selectedItem.setItemType(selectedType);
		} else {
			selectedItem.setItemType(itemType.getValue());
		}
	}

	private void saveItemType(String itemType) {
		EditItemPageDAO dao = new EditItemPageImpl();
		
		dao.addItemType(itemType);
	}
	
	private void notifyUserOfUpdate() {
		Logger.notifyUser(EditItemPageResources.updateComplete);
	}

}
