package com.billings.test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javafx.application.Application;
import javafx.stage.Stage;

import com.billings.jdbc.dto.FoundCustomer;
import com.billings.resources.CommonResources;
import com.billings.resources.CustomerInfoPageResources;
import com.billings.resources.SelectCustomerPageResources;
import com.billings.utils.FXMLFactory;
import com.billings.utils.TestUtils;

public class SelectCustomerPageTest  extends Application{

	public static void main(String[] args) {
		launch(args); 
	}

	@Override
	public void start(Stage window) throws Exception {
		CommonResources.MAIN_CLASS = getClass();
		
		setupTestData();
		
		CustomerInfoPageResources.setupWithEditSponsorInfo();
		
		TestUtils.displayFXMLPaneInStage(window, 
				FXMLFactory.getSelectCustomerPage());
	}
	
	private void setupTestData() {
		List<FoundCustomer> customerList = new ArrayList<FoundCustomer>();
		
		customerList.addAll(Arrays.asList(
			new FoundCustomer(1, "Derek", "", "5303868074"),
			new FoundCustomer(2, "Lindsey", "Derek", "5303868075")
		));
		
		SelectCustomerPageResources.setupWithFoundCustomers(customerList);
	}

}
