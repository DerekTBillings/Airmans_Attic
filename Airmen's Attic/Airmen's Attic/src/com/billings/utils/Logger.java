package com.billings.utils;

import com.billings.main.WindowController;
import com.billings.resources.NotificationPageResources;

/**
 * This class is used to log error messages.
 * Use this class to house the different forms
 * of error displays.
 * 
 * @author Derek
 *
 */
public class Logger {

	public static void log(String message) {
		System.out.println(message);
	}
	
	public static void notifyUser(String message) {
		NotificationPageResources.setupPageWithMessage(message);
		
		WindowController.createPopupWindow(
				FXMLFactory.getNotificationPage());
	}
	
}
