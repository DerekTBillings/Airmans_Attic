package com.billings.test;

import com.billings.resources.CommonResources;
import com.billings.resources.SignInPageResources;
import com.billings.utils.FXMLFactory;
import com.billings.utils.TestUtils;

import javafx.application.Application;
import javafx.stage.Stage;

public class RafflePageThroughSignInTest extends Application {

	public static void main(String[] args) {
		launch(args);
	}
	
	@Override
	public void start(Stage window) throws Exception {
		CommonResources.MAIN_CLASS = getClass();
		
		SignInPageResources.setupWithRaffleSignIn();
		
		TestUtils.displayFXMLPaneInStage(window, 
				FXMLFactory.getSignInPage()
		);
	}

}
