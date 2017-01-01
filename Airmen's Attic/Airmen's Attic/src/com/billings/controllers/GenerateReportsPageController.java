package com.billings.controllers;

import java.net.URL;
import java.util.ResourceBundle;

import com.billings.resources.GenerateReportsPageResources;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class GenerateReportsPageController implements Initializable {

	@FXML
	Label pageHeaderLbl;

	@FXML
	TableView reportTable;

	@FXML
	TableColumn reportName;
	@FXML
	TableColumn reportDescription;

	@FXML
	Button generateBtn;
	@FXML
	Button closeBtn;
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		setupNodesWithTextValues();		
	}

	private void setupNodesWithTextValues() {
		pageHeaderLbl.setText(GenerateReportsPageResources.PAGE_HEADER_LBL);
		reportName.setText(GenerateReportsPageResources.REPORT_NAME);
		reportDescription.setText(GenerateReportsPageResources.REPORT_DESCRIPTION);
		generateBtn.setText(GenerateReportsPageResources.GENERATE_BTN);
		closeBtn.setText(GenerateReportsPageResources.CLOSE_BTN);
	}
	
}
