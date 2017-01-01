package com.billings.controllers;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;

import com.billings.events.implementation.CheckoutItemInfoPageNewItem;
import com.billings.events.implementation.CheckoutItemInfoPageSetItem;
import com.billings.events.interfaces.CheckoutItemInfoPageEvents;
import com.billings.jdbc.dto.CheckoutItem;
import com.billings.main.WindowController;
import com.billings.resources.CheckoutItemInfoPageResources;
import com.billings.resources.CustomerCheckOutPageResources;
import com.billings.utils.Logger;
import com.billings.utils.Messages;

public class CheckoutItemInfoPageController implements Initializable {

	@FXML
	Label headerLbl;
	@FXML
	Label nameLbl;
	@FXML
	Label typeLbl;
	@FXML
	Label quantityLbl;
	@FXML
	Label newTypeLbl;

	@FXML
	TextField quantity;
	@FXML
	TextField name;
	@FXML
	TextField newType;

	@FXML
	ChoiceBox typeList;

	@FXML
	Button setItemBtn;
	@FXML
	Button cancelBtn;
	
	private CheckoutItem item;
	
	private boolean isNewItem;
	
	private CheckoutItemInfoPageEvents pageEvents;
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		setupNodesWithTextValues();
		setupTypeList();
		setupSetItemBtn();
		setupCancelBtn();
		
		setupPageForCheckoutItem();
		
		setupItemQuantity();
	}

	private void setupNodesWithTextValues() {
		headerLbl.setText(CheckoutItemInfoPageResources.HEADER_LBL);
		nameLbl.setText(CheckoutItemInfoPageResources.NAME_LBL);
		typeLbl.setText(CheckoutItemInfoPageResources.TYPE_LBL);
		quantityLbl.setText(CheckoutItemInfoPageResources.QUANTITY_LBL);
		newTypeLbl.setText(CheckoutItemInfoPageResources.NEW_TYPE_LBL);
		setItemBtn.setText(CheckoutItemInfoPageResources.SET_ITEM_BTN);
		cancelBtn.setText(CheckoutItemInfoPageResources.CANCEL_BTN);
	}
	
	private void setupTypeList() {
		List<String> itemTypeList = CheckoutItemInfoPageResources.getTypeList();
		
		ObservableList<String> typeListChildren = typeList.getItems();
		
		typeListChildren.addAll(itemTypeList);
	}
	
	private void setupItemQuantity() {
		Integer amount = item.getQuantity();
		
		if (amount == null) {
			amount = 0;
		}
		
		quantity.setText(Integer.toString(amount));
	}
	
	private void setupPageForCheckoutItem() {
		item = CheckoutItemInfoPageResources.getCheckoutItem();
		
		//This section exists only for testing
		if (item == null) {
			item = new CheckoutItem();
		}
		//end
		
		if (item.getItemId() != 0) {
			isNewItem = false;
			pageEvents = new CheckoutItemInfoPageSetItem();
			
			hideNewTypeSection();
			disableFields();
			displayItemInfo();
			
		} else {
			isNewItem = true;
			pageEvents = new CheckoutItemInfoPageNewItem();
		}
	}
	
	private void hideNewTypeSection() {
		Pane parent = (Pane)newTypeLbl.getParent();
		
		ObservableList children = parent.getChildren();
		
		children.remove(newTypeLbl);
		children.remove(newType);
	}
	
	private void disableFields() {
		name.setDisable(true);
		typeList.setDisable(true);
	}
	
	private void setupSetItemBtn() {
		setItemBtn.setOnAction(e -> {
			CheckoutItem item = buildCheckoutItem();
			
			if (pageEvents.setItemBtnEvent(item)) {
				closeWindow();
				reloadCheckoutItemList();
			}
		});
	}
	
	private void reloadCheckoutItemList() {
		CustomerCheckOutPageResources.reloadCheckoutListContainer();
	}
	
	private CheckoutItem buildCheckoutItem() {
		String quantityInput = quantity.getText();
		int count = 0;
		
		try {
			count = Integer.parseInt(quantityInput);
			item.setQuantity(count);
		} catch(Exception e) {
			Logger.notifyUser(Messages.quantityError);
		}
		
		if (isNewItem) {
			String itemName = name.getText();
			String itemType = newType.getText();
			
			if (itemType.isEmpty()) {
				itemType = (String)typeList.getValue();
			}
			
			item.setItemName(itemName);
			item.setItemType(itemType);
		}
		
		return item;
	}
	
	private void setupCancelBtn() {
		cancelBtn.setOnAction(e -> {
			closeWindow();
		});
	}
	
	private void displayItemInfo() {
		name.setText(item.getItemName());
		typeList.setValue(item.getItemType());
	}
	
	private void closeWindow() {
		WindowController.closeNodeContainingWindow(cancelBtn);
	}
	
}
