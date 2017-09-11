package com.billings.main;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import com.billings.resources.CommonResources;
import com.billings.utils.FXMLFactory;
import com.billings.utils.Logger;

/**
 * This class is used to start the program and
 * house a reference to the Stage that is used
 * to display each window.
 * 
 * Use this class to change the scene viewed
 * by the user.
 * @author Derek
 *
 */
public class WindowController extends Application{
	
	private static Scene scene;
	private static Stage window;
	private static Class windowControllerClass;
	
	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage stage) throws Exception {		
		windowControllerClass = getClass();
		CommonResources.MAIN_CLASS = windowControllerClass;
		
		window = stage;
		
		initializeProgramWithFirstScene();
		displaySceneInWindow();
	}
	
	private static void initializeProgramWithFirstScene() {
		changeScene(FXMLFactory.getFirstScene());
	}
	
	public static void changeScene(String fxmlPath) {
		Pane paneForFXML = handleAndLoadFXML(fxmlPath);
		instantiateSceneWithPane(paneForFXML);
		displaySceneInWindow();
	}
	
	public static void createPopupWindow(String fxmlPath) {
		Pane paneForFXML = handleAndLoadFXML(fxmlPath);
		createPopupStageUsingPane(paneForFXML);
	}
	
	private static Pane handleAndLoadFXML(String fxmlPath) {
		Pane loadedFXMLPane = null;
		try {
			loadedFXMLPane = loadFXML(fxmlPath);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return loadedFXMLPane;
	}
	
	private static Pane loadFXML(String fxmlPath) throws IOException {
		Logger.log("Redirected to: "+fxmlPath);
		
		Pane loadedFXMLPane = FXMLLoader.load(
				CommonResources.MAIN_CLASS.getResource(fxmlPath));
		return loadedFXMLPane;
	}
	
	private static void createPopupStageUsingPane(Pane loadedPane) {
		Scene popupScene = new Scene(loadedPane);

		Stage popupWindow = new Stage();
		popupWindow.setScene(popupScene);
		
		popupWindow.initModality(Modality.APPLICATION_MODAL);
		
		popupWindow.show();
	}
	
	public static void closeNodeContainingWindow(Node node) {
		Stage stage = (Stage)node.getScene().getWindow();
		stage.close();
	}
	
	private static void instantiateSceneWithPane(Pane loadedPane) {
		scene = new Scene(loadedPane);
	}
	
	private static void displaySceneInWindow() {
		window.setScene(scene);
		window.show();
	}

}
