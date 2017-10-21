package com.billings.jdbc.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.billings.jdbc.dto.ItemType;
import com.billings.jdbc.sql.EditItemTypesPageSQL;
import com.billings.utils.Logger;
import com.billings.utils.SQLStatementUtils;

public class EditItemTypesPageImpl implements EditItemTypesPageDAO {

	@Override
	public List<ItemType> getItemTypes() {
		String query = EditItemTypesPageSQL.getItemTypes;
		
		return SQLStatementUtils.executeQuery(query, ItemType.class);
	}

	@Override
	public boolean isTypeUsed(int typeId) {
		String query = EditItemTypesPageSQL.isTypeUsed;
		
		return (Boolean)SQLStatementUtils.executeQueryForSingleCell(
				query, Boolean.class, typeId);
	}

	@Override
	public void deleteItemType(int typeId) {
		String query = EditItemTypesPageSQL.deleteType;
		
		SQLStatementUtils.executeDelete(query, typeId);
	}

	@Override
	public void updateItemType(ItemType type) {
		String query = EditItemTypesPageSQL.updateType;
		
		SQLStatementUtils.executeUpdate(
				query, type.getName(), type.getId());
	}

	@Override
	public boolean isTypeNameUnique(int forTypeId, String text) {
		String query = EditItemTypesPageSQL.isTypeNameUnique;
		
		return (Boolean)SQLStatementUtils.executeQueryForSingleCell(
				query, Boolean.class, text, forTypeId);
	}

}
