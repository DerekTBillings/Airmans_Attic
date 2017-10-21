package com.billings.utils;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;

/**
 * This class is used to find and return
 * Scene Objects. Use this file to track
 * which files return which scenes.
 * @author Derek
 *
 */
public class FXMLFactory {
	
	private static final String fxmlPackagePath = "/com/billings/fxml/";
	
	public static String getFirstScene() {
		return getWelcomePage();
	}
	
	public static String getWelcomePage() {
		return fxmlPackagePath+"WelcomePage.fxml";
	}
	
	public static String getSignInPage() {
		return fxmlPackagePath+"SignInPage.fxml";
	}
	
	public static String getCustomerInfoPage() {
		return fxmlPackagePath+"CustomerInfoPage.fxml";
	}
	
	public static String getCustomerCheckOutPage() {
		return fxmlPackagePath+"CustomerCheckOut.fxml";
	}
	
	public static String getRafflePage() {
		return fxmlPackagePath+"RafflePage.fxml";
	}
	
	public static String getAdminPage() {
		return fxmlPackagePath+"AdminPage.fxml";
	}
	
	public static String getRaffleAdminPage() {
		return fxmlPackagePath+"RaffleAdminPage.fxml";
	}

	public static String getEditAtticInfoPage() {
		return fxmlPackagePath+"EditAtticInfoPage.fxml";
	}
	
	public static String getEditAtticEventsPage() {
		return fxmlPackagePath+"EditAtticEventsPage.fxml";
	}
	
	public static String getGenerateReportsPage() {
		return fxmlPackagePath+"GenerateReportsPage.fxml";
	}
	
	public static String getSelectCustomerPage() {
		return fxmlPackagePath+"SelectCustomerPage.fxml";
	}
	
	public static String getNotificationPage() {
		return fxmlPackagePath+"NotificationPage.fxml";
	}
	
	public static String getCheckoutItemInfoPage() {
		return fxmlPackagePath+"CheckoutItemInfoPage.fxml";
	}
	
	public static String getNewRaffleItemPage() {
		return fxmlPackagePath+"NewRaffleItemPage.fxml";
	}
	
	public static String getPasswordInterfacePage() {
		return fxmlPackagePath+"PasswordInterface.fxml";
	}
	
	public static String getEditItemPage() {
		return fxmlPackagePath+"EditItemPage.fxml";
	}

	public static String getEditItemTypesPage() {
		return fxmlPackagePath+"EditItemTypesPage.fxml";
	}
	
	public static String getAdminManagementPage() {
		return fxmlPackagePath+"AdminManagement.fxml";
	}

	public static String getReportResultsPage() {
		return fxmlPackagePath+"ReportResults.fxml";
	}
}
