package com.billings.test;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import com.billings.controllers.SignInPageController;
import com.billings.main.WindowController;
import com.billings.resources.CommonResources;
import com.billings.resources.SignInPageResources;
import com.billings.utils.FXMLFactory;
import com.billings.utils.TestUtils;

public class SignInTest extends Application {

	public static void main(String[] args) {
		launch(args);

	}

	@Override
	public void start(Stage window) throws Exception {
		CommonResources.MAIN_CLASS = getClass();
		
		SignInPageResources.setupWithCustomerSignIn();
		
		TestUtils.displayFXMLPaneInStage(window,
				FXMLFactory.getSignInPage());
		
	}

}
