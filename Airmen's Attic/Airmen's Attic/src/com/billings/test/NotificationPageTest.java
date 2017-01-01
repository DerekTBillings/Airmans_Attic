package com.billings.test;

import javafx.application.Application;
import javafx.stage.Stage;

import com.billings.resources.CommonResources;
import com.billings.resources.NotificationPageResources;
import com.billings.utils.FXMLFactory;
import com.billings.utils.TestUtils;

public class NotificationPageTest extends Application {

	public static void main(String[] args) {
		launch(args);

	}

	@Override
	public void start(Stage window) throws Exception {
		CommonResources.MAIN_CLASS = getClass();
		
		NotificationPageResources.setupPageWithMessage("This is a test");
		
		TestUtils.displayFXMLPaneInStage(window,
				FXMLFactory.getNotificationPage());
	}

}
