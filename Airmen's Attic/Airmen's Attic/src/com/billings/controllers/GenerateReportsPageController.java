package com.billings.controllers;

import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import com.billings.jdbc.dao.GenerateReportsDAO;
import com.billings.jdbc.dao.GenerateReportsImpl;
import com.billings.jdbc.dto.CheckoutItem;
import com.billings.jdbc.dto.Report;
import com.billings.main.WindowController;
import com.billings.resources.GenerateReportsPageResources;
import com.billings.resources.ReportResultsResources;
import com.billings.utils.Common;
import com.billings.utils.FXMLFactory;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class GenerateReportsPageController implements Initializable {

	@FXML
	Label pageHeaderLbl;

	@FXML
	TableView<Report> reportTable;

	@FXML
	TableColumn reportName;

	@FXML
	Button generateBtn;
	@FXML
	Button closeBtn;
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		setupNodesWithTextValues();
		mapColumns();
		addItemsToTable();
		setupGenerageBtn();
		setupCloseBtn();
	}

	private void setupNodesWithTextValues() {
		pageHeaderLbl.setText(GenerateReportsPageResources.PAGE_HEADER_LBL);
		reportName.setText(GenerateReportsPageResources.REPORT_NAME);
		generateBtn.setText(GenerateReportsPageResources.GENERATE_BTN);
		closeBtn.setText(GenerateReportsPageResources.CLOSE_BTN);
	}

	private void mapColumns() {
		reportName.setCellValueFactory(getPropertyValue("name"));
	}

	private PropertyValueFactory getPropertyValue(String attribute) {
		return new PropertyValueFactory<Report, String>(attribute);
	}

	private void addItemsToTable() {
		reportTable.getItems().addAll(getReports());
	}
	
	private List<Report> getReports() {
		GenerateReportsDAO dao = new GenerateReportsImpl();
		
		return dao.getReports();
	}

	private void setupGenerageBtn() {
		generateBtn.setOnAction(e -> {
			Report selected = getSelectedItem();
			
			if (selected != null) {
				String query = selected.getQuery();
				
				storeReport(generateReport(query));
				displayReport();
			}
		});
	}

	private List<Map<String, Object>> generateReport(String query) {
		GenerateReportsDAO dao = new GenerateReportsImpl();
		
		return dao.generateReport(query);
	}

	private void storeReport(List<Map<String, Object>> reportResults) {
		ReportResultsResources.setReport(reportResults);		
	}

	private Report getSelectedItem() {
		return (Report)reportTable.getSelectionModel().getSelectedItem();
	}

	private void displayReport() {
		WindowController.createPopupWindow(
				FXMLFactory.getReportResultsPage());
	}

	private void setupCloseBtn() {
		closeBtn.setOnAction(e -> {
			WindowController.closeNodeContainingWindow(closeBtn);
		});
	}
	
}
