package com.billings.utils;

import java.io.IOException;

import com.billings.resources.CommonResources;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class TestUtils {
	
	public static void displayFXMLPaneInStage(Stage stage, String FXMLPath) {
		Pane loadedPane = handleLoadingFXML(FXMLPath);
		
		Scene sceneWithPane = loadSceneWithPane(loadedPane);
		
		displayStageWithScene(stage, sceneWithPane);
	}

	private static Pane handleLoadingFXML(String FXMLPath) {
		Pane loadedPane = null;
		try {
			loadedPane = loadFXML(FXMLPath);
		} catch(IOException io) {
			io.printStackTrace();
		}
		return loadedPane;
	}

	private static Pane loadFXML(String FXMLPath) throws IOException {
		Pane loadedPane = FXMLLoader.load(
				CommonResources.MAIN_CLASS.getResource(FXMLPath));
		return loadedPane;
		
	}
	
	private static Scene loadSceneWithPane(Pane loadedPane) {
		return new Scene(loadedPane);
	}

	private static void displayStageWithScene(Stage stage, Scene sceneWithPane) {
		stage.setScene(sceneWithPane);
		
		stage.show();
	}
	
}
