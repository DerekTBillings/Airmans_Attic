package com.billings.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * This class is used to control
 * all of the database connections.
 * Use this class to open a connection 
 * and to close open connections, to 
 * include Statements and resultSets.
 * @author Derek
 *
 */
public class ConnManager {
	
	private static Connection connection;
	private static Statement statement;
	private static ResultSet resultSet;
	
	private static final String  DATABASE_USERNAME = "AAUser";
	private static final String DATABASE_PASSWORD = "QueryAADB54321";
	private static final String DATABASE_URL = "jdbc:mysql://localhost:3306/";
	private static final String DATABASE_SCHEMA_NAME = "airmen's attic";
	
	
	public static Connection getNewConnection() {
		if (isConnectionNull()) {
			createNewConnection();
		}
		return connection;
	}
	
	private static boolean isConnectionNull() {
		boolean openNewConnection = false;
		
		try {
			if (connection == null
					|| connection.isClosed()) {
				openNewConnection = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return openNewConnection;
	}
	
	private static void createNewConnection() {
		try {
			classForName();
			establishDriverManagerConnection();
		} catch (Exception e) {
			Logger.log("Unable to establish a connection");
			e.printStackTrace();
		}
	}
	
	private static void classForName() throws Exception {
		Class.forName("com.mysql.jdbc.Driver");
	}
	
	private static void establishDriverManagerConnection() throws Exception {
		connection = DriverManager.getConnection(  
				DATABASE_URL+DATABASE_SCHEMA_NAME,
				DATABASE_USERNAME,
				DATABASE_PASSWORD);  
	}
	
	public static void closeDatabaseConnectionAndTools(ResultSet rs) {
		resultSet = rs;
		closeDatabaseConnectionAndTools();
	}
	
	public static void closeDatabaseConnectionAndTools() {
		try {
			closeConnection();
			closeStatement();
			closeResultSet();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private static void closeConnection() throws Exception {
		if (connection != null) 
			connection.close();
	}
	
	private static void closeStatement() throws Exception {
		if (statement != null)
			statement.close();
	}
	
	private static void closeResultSet() throws Exception {
		if (resultSet != null) {
			resultSet.close();
		}
	}
	
	public static PreparedStatement getPreparedStatement(String query) {
		if (isConnectionNull()) {
			createNewConnection();
		}
		createPreparedStatement(query);
		return (PreparedStatement)statement;
	}
	
	private static void createPreparedStatement(String query) {
		try {
			prepareStatement(query);
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	private static void prepareStatement(String query) throws Exception {
		statement = connection.prepareStatement(query);
	}
	
}
