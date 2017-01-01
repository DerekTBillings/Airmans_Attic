package com.billings.resources;

import java.time.LocalDateTime;


public class CommonResources {
	
	public static Class MAIN_CLASS;
	
	private static boolean isSignedIn = false;
	private static LocalDateTime dateSignedIn = null;
	
	
	public static boolean isSystemSignedIn() {
		if (isSignInFromToday()) {
			return isSignedIn;
		
		} else {
			isSignedIn = false;
			dateSignedIn = null;
			return false;
		}
		
	}
	
	
	private static boolean isSignInFromToday() {
		LocalDateTime today = LocalDateTime.now();
		
		if (dateSignedIn != null
				&& dateSignedIn.getDayOfMonth() == today.getDayOfMonth()
				&& dateSignedIn.getYear() == today.getYear()) {
			return true;
		}
		
		return false;
	}
	
	
	public static void annotateSignedIntoSystem() {
		isSignedIn = true;
		dateSignedIn = LocalDateTime.now();
	}
	
	
}
