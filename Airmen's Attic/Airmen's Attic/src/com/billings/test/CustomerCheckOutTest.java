package com.billings.test;

import com.billings.resources.CommonResources;
import com.billings.utils.FXMLFactory;
import com.billings.utils.TestUtils;

import javafx.application.Application;
import javafx.stage.Stage;

public class CustomerCheckOutTest extends Application {

	public static void main(String[] args) {
		launch(args);

	}

	@Override
	public void start(Stage window) throws Exception {
		CommonResources.MAIN_CLASS = getClass();
		
		TestUtils.displayFXMLPaneInStage(window, 
				FXMLFactory.getCustomerCheckOutPage());
	}

}
