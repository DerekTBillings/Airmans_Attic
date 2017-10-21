package com.billings.controllers;

import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import com.billings.jdbc.dto.CheckoutItem;
import com.billings.main.WindowController;
import com.billings.resources.CustomerCheckOutPageResources;
import com.billings.resources.ReportResultsResources;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.MapValueFactory;

public class ReportResultsController implements Initializable {
	
	@FXML
	Label reportHeader;
	
	@FXML
	TableView resultsContainer;
	
	@FXML
	Button closeBtn;
	
	@FXML
	TextField filter;
	
	private List<Map<String, Object>> report;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		getReport();
		setupCloseBtn();
		
		if (!report.isEmpty())
			displayReport();
	}

	private void getReport() {
		report = ReportResultsResources.getReport();
	}
	
	private void setupCloseBtn() {
		closeBtn.setOnAction(e -> {
			WindowController.closeNodeContainingWindow(closeBtn);
		});
	}

	private void displayReport() {
		establishColumns();
		populateTable(report);
		setupFilter();
	}

	private void establishColumns() {
		ObservableList<TableColumn> columns = resultsContainer.getColumns();
		Set<String> keys = getKeys();
		
		for (String key : keys) {
			TableColumn<Map, String> column = new TableColumn<Map, String>(key);
			column.setCellValueFactory(new MapValueFactory<String>(key));
			
			column.setText(key);
            	
			columns.add(column);
		}
	}
	
	private Set<String> getKeys() {
		return report.get(0).keySet();
	}

	private void populateTable(List data) {
		ObservableList items = resultsContainer.getItems();
		
		items.clear();
		items.addAll(data);
	}

	private void setupFilter() {
		filter.setOnKeyReleased(e -> {
			String filterText = filter.getText();
			filterText = filterText.toLowerCase();
			
			doFilter(filterText);
		});
	}
	
	private void doFilter(String filterText) {
		List<Map<String, Object>> filteredRows = new ArrayList<Map<String, Object>>();
		
		for (Map<String, Object> row : report) {
			Set<String> keys = row.keySet();
			
			for (String key : keys) {
				if (getCellValue(row, key).contains(filterText)) {
					filteredRows.add(row);
				}
			}
		}
		
		populateTable(filteredRows);
	}

	private String getCellValue(Map<String, Object> row, String key) {
		Object value = row.get(key);
		
		if (value == null)
			return "";
		
		return value.toString().toLowerCase();
	}

}
