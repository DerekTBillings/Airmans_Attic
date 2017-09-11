package com.billings.controllers;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import com.billings.jdbc.dao.EditItemTypesPageDAO;
import com.billings.jdbc.dao.EditItemTypesPageImpl;
import com.billings.jdbc.dto.CheckoutItem;
import com.billings.jdbc.dto.ItemType;
import com.billings.resources.EditItemTypesPageResources;
import com.billings.utils.Common;
import com.billings.utils.Logger;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class EditItemTypesPageController implements Initializable {
	
	@FXML
	Label headerLbl;
	@FXML
	Label editHeaderLbl;
	@FXML
	Label typeNameLbl;

	@FXML
	TableView typesTbl;

	@FXML
	TableColumn typesCol;

	@FXML
	TextField typeName;

	@FXML
	Button deleteBtn;
	@FXML
	Button saveBtn;
	
	private List<ItemType> types;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		setupInitialValues();
		loadItemTypes();
		populateTypesTbl();
		setupTblOnClick();
		setupDeleteBtn();
		setupSaveBtn();
	}

	private void setupInitialValues() {
		headerLbl.setText(EditItemTypesPageResources.HEADER_LBL);
		editHeaderLbl.setText(EditItemTypesPageResources.EDIT_HEADER_LBL);
		typeNameLbl.setText(EditItemTypesPageResources.TYPE_NAME_LBL);
		typesCol.setText(EditItemTypesPageResources.TYPES_COL);
		deleteBtn.setText(EditItemTypesPageResources.DELETE_BTN);
		saveBtn.setText(EditItemTypesPageResources.SAVE_BTN);		
	}

	private void loadItemTypes() {
		EditItemTypesPageDAO dao = new EditItemTypesPageImpl();
		
		types = dao.getItemTypes();
	}
	
	private void populateTypesTbl() {
		setupColumnBinding();
		
		typesTbl.getItems().addAll(types);
	}
	
	private void setupColumnBinding() {
		typesCol.setCellValueFactory(getPropertyValue("name"));
	}
	
	private PropertyValueFactory getPropertyValue(String attribute) {
		return new PropertyValueFactory<CheckoutItem, String>(attribute);
	}

	private void setupTblOnClick() {
		typesTbl.setOnMouseClicked(e -> {
			if (Common.isTargetAColumn(e.getTarget()))
				displaySelectedItemType();
		});
	}

	private void displaySelectedItemType() {
		ItemType selected = getSelectedItemType();
		
		typeName.setText(selected.getName());
	}

	private ItemType getSelectedItemType() {
		return (ItemType)typesTbl.getSelectionModel().getSelectedItem();
	}

	private void setupDeleteBtn() {
		deleteBtn.setOnAction(e -> {
			if (isTypeSelected() && !isTypeUsed())
				deleteSelectedType();
			else
				notifyCannotDelete();
		});
	}

	private boolean isTypeUsed() {
		EditItemTypesPageDAO dao = new EditItemTypesPageImpl();
		return dao.isTypeUsed(getSelectedTypeId());
	}

	private int getSelectedTypeId() {
		return getSelectedItemType().getId();
	}

	private void deleteSelectedType() {
		EditItemTypesPageDAO dao = new EditItemTypesPageImpl();
		dao.deleteItemType(getSelectedTypeId());
	}

	private void notifyCannotDelete() {
		String msg = EditItemTypesPageResources.CANNOT_DELETE_TYPE_MESSAGE;
		Logger.notifyUser(msg);
	}

	private void setupSaveBtn() {
		saveBtn.setOnAction(e ->{
			if (isTypeSelected() && isTypeNameUnique()) {
				ItemType selectedType = getSelectedItemType();
				selectedType.setName(typeName.getText());
				
				EditItemTypesPageDAO dao = new EditItemTypesPageImpl();
				dao.updateItemType(selectedType);
				
				refreshTable();
			}
		});
	}

	private boolean isTypeSelected() {
		return getSelectedItemType() != null;
	}

	private boolean isTypeNameUnique() {
		EditItemTypesPageDAO dao = new EditItemTypesPageImpl();
		
		int forTypeId = getSelectedTypeId();
		String proposedName = typeName.getText();
		
		return dao.isTypeNameUnique(forTypeId, proposedName);
	}

	private void refreshTable() {
		typesTbl.getItems().clear();
		types.clear();
		loadItemTypes();
		populateTypesTbl();
	}

}
