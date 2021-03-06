package com.billings.controllers;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.function.Predicate;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import javafx.collections.ObservableList;
import javafx.event.EventTarget;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TableView.TableViewSelectionModel;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;

import com.billings.jdbc.dao.CustomerCheckOutPageDAO;
import com.billings.jdbc.dao.CustomerCheckOutPageImpl;
import com.billings.jdbc.dao.EditAtticInfoPageDAO;
import com.billings.jdbc.dao.EditAtticInfoPagePersonImpl;
import com.billings.jdbc.dto.CheckoutHistoryItem;
import com.billings.jdbc.dto.CheckoutItem;
import com.billings.main.WindowController;
import com.billings.resources.CheckoutItemInfoPageResources;
import com.billings.resources.CustomerCheckOutPageResources;
import com.billings.utils.Common;
import com.billings.utils.FXMLFactory;

public class CustomerCheckOutController implements Initializable {

	@FXML
	Label checkOutLbl;
	@FXML
	Label customerNameLbl;
	@FXML
	Label filterLbl;
	
	@FXML
	TextField filterTxt;
	
	@FXML
	TableView inventoryTbl;
	@FXML
	TableView customerHistoryTbl;
	
	@FXML
	Button checkoutBtn;
	@FXML
	Button cancelBtn;
	
	@FXML
	Tab checkoutTab;
	@FXML
	Tab historyTab;
	
	@FXML
	TableColumn checkoutItem;
	@FXML
	TableColumn checkoutItemType;
	@FXML
	TableColumn checkoutQuantity;
	@FXML
	TableColumn historyQuantity;
	@FXML
	TableColumn historyItem;
	@FXML
	TableColumn historyDate;
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		setupNodesWithTextValues();
		setupColumnLinks();
		storeCheckoutListInResources();
		resetCheckoutList();
		populateHistoryList();
		setupCheckoutListEvents();
		setupFilterEvents();
		setupCheckoutBtn();
		setupCancelBtn();
	}

	private void setupNodesWithTextValues() {
		checkOutLbl.setText(CustomerCheckOutPageResources.CHECK_OUT_LBL 
				+ CustomerCheckOutPageResources.getCustomerName());
		filterLbl.setText(CustomerCheckOutPageResources.FILTER_LBL);
		checkoutBtn.setText(CustomerCheckOutPageResources.CHECKOUT_BTN);
		cancelBtn.setText(CustomerCheckOutPageResources.CANCEL_BTN);
		checkoutTab.setText(CustomerCheckOutPageResources.CHECKOUT_TAB);
		historyTab.setText(CustomerCheckOutPageResources.HISTORY_TAB);
		checkoutItem.setText(CustomerCheckOutPageResources.CHECKOUT_ITEM);
		checkoutItemType.setText(CustomerCheckOutPageResources.CHECKOUT_ITEM_TYPE);
		checkoutQuantity.setText(CustomerCheckOutPageResources.CHECKOUT_QUANTITY);
		historyQuantity.setText(CustomerCheckOutPageResources.HISTORY_QUANTITY);
		historyItem.setText(CustomerCheckOutPageResources.HISTORY_ITEM);
		historyDate.setText(CustomerCheckOutPageResources.HISTORY_DATE);
	}
	
	private void setupColumnLinks() {
		setupCheckoutColumnMappingLink(checkoutItem, "itemName");
		setupCheckoutColumnMappingLink(checkoutItemType, "itemType");
		setupCheckoutColumnMappingLink(checkoutQuantity, "quantity");

		setupCheckoutColumnMappingLink(historyItem, "itemName");
		setupCheckoutColumnMappingLink(historyQuantity, "quantity");
		setupCheckoutColumnMappingLink(historyDate, "date");
	}
	
	private void resetCheckoutList() {
		List<CheckoutItem> checkoutItemList = CustomerCheckOutPageResources.getCheckoutItemList();
		
		for (CheckoutItem item : checkoutItemList) {
			item.setQuantity(0);
		}
		
		CustomerCheckOutPageResources.reloadCheckoutListContainer();
	}
	
	private void setupCheckoutColumnMappingLink(TableColumn column, String attribute) {
		column.setCellValueFactory(
			new PropertyValueFactory<CheckoutItem, String>(attribute)
		);
		
	}
	
	private void populateHistoryList() {
		List<CheckoutHistoryItem> historyItemList = CustomerCheckOutPageResources.getCustomerHistoryItems();
		
		ObservableList<CheckoutHistoryItem> tableList = customerHistoryTbl.getItems();
		
		tableList.addAll(historyItemList);
	}
	
	private void storeCheckoutListInResources() {
		CustomerCheckOutPageResources.setCheckoutItemListContainer(
				inventoryTbl.getItems());
	}
	
	private void setupHistoryColumnMappingLink(TableColumn column, String attribute) {
		column.setCellValueFactory(
			new PropertyValueFactory<CheckoutHistoryItem, String>(attribute)
		);
	}
	
	private void setupCheckoutListEvents() {
		
		inventoryTbl.setOnMouseClicked(e -> {
			EventTarget target = e.getTarget();
			
			if (Common.isTargetAColumn(target)) {
				TableViewSelectionModel<CheckoutItem> selectedItem = inventoryTbl.getSelectionModel();
				CheckoutItem selectedRow = selectedItem.getSelectedItem();
				
				CheckoutItemInfoPageResources.setCheckoutItem(selectedRow);
				openCheckoutItemInfoPage();
			}
		});
	}
	
	private void openCheckoutItemInfoPage() {
		WindowController.createPopupWindow(
				FXMLFactory.getCheckoutItemInfoPage());
	}
	
	private void setupFilterEvents() {
		filterTxt.setOnKeyReleased(e -> {
			String filterText = filterTxt.getText();
			filterText = filterText.toLowerCase();
			
			doFilter(filterText);
		});
	}
	
	private void doFilter(String filterText) {
		List<CheckoutItem> itemsList = getItemListCopy();
		
		Predicate<CheckoutItem> filterPredicate = getFilterPredicate(filterText);

		itemsList = itemsList.stream().filter(filterPredicate).collect(Collectors.toList());

		CustomerCheckOutPageResources.loadCheckoutListContainer(itemsList);
	}
	
	private List<CheckoutItem> getItemListCopy() {
		List<CheckoutItem> possibleItems = CustomerCheckOutPageResources.getCheckoutItemList();
		
		List<CheckoutItem> possibleItemsCopy = new ArrayList<CheckoutItem>();
		possibleItemsCopy.addAll(possibleItems);
		
		return possibleItemsCopy;
	}
	
	private Predicate<CheckoutItem> getFilterPredicate(String filterText) {
		
		Predicate<CheckoutItem> filterPredicate = item -> {	
			String name = item.getItemName();
			String type = item.getItemType();
			
			name = name.toLowerCase();
			type = type.toLowerCase();
			
			boolean allowed = (name.contains(filterText) ||
					type.contains(filterText));
			
			return allowed;
			
		};
		
		return filterPredicate;
	}

	private void setupCheckoutBtn() {
		checkoutBtn.setOnAction(e -> {
			List<CheckoutItem> checkoutItems = CustomerCheckOutPageResources.getCheckoutItemList();
			int personId = CustomerCheckOutPageResources.getCustomerId();
			
			CustomerCheckOutPageDAO dao = new CustomerCheckOutPageImpl();
			dao.checkout(checkoutItems, personId);
			
			closePage();
		});
	}
	
	private void closePage() {
		WindowController.closeNodeContainingWindow(cancelBtn);
	}

	private void setupCancelBtn() {
		cancelBtn.setOnAction(e -> {
			closePage();
		});
	}

}
