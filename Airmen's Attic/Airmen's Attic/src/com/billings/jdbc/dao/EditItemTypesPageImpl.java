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
		
		ResultSet rs = SQLStatementUtils.executeQueryAndReturnResultSet(query);
		
		List<ItemType> types = tryGetItemTypes(rs);
		
		return types;
	}

	private List<ItemType> tryGetItemTypes(ResultSet rs) {
		try {
			return getItemTypes(rs);
		} catch(Exception e) {
			Logger.log(e.getMessage());
		}
		
		return new ArrayList<ItemType>();
	}

	private List<ItemType> getItemTypes(ResultSet rs) throws Exception {
		List<ItemType> types = new ArrayList<ItemType>();
		
		while(rs.next()) {
			ItemType type = new ItemType();
			
			type.setId(rs.getInt("type_id"));
			type.setName(rs.getString("type_name"));
			
			types.add(type);
		}
		
		return types;
	}

	@Override
	public boolean isTypeUsed(int typeId) {
		String query = EditItemTypesPageSQL.isTypeUsed;
		ResultSet rs = SQLStatementUtils.executeQueryAndReturnResultSet(query, typeId);
		
		boolean isUsed = getSengleCellBooleanValue(rs);
		
		SQLStatementUtils.closeConnectionsWithResultSet();
		
		return isUsed;
	}

	private boolean getSengleCellBooleanValue(ResultSet rs) {
		try {
			if (rs.next())
				return rs.getBoolean(1);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return false;
	}

	@Override
	public void deleteItemType(int typeId) {
		String query = EditItemTypesPageSQL.deleteType;
		SQLStatementUtils.executeQueryWithoutResultSet(query, typeId);
	}

	@Override
	public void updateItemType(ItemType type) {
		String query = EditItemTypesPageSQL.updateType;
		SQLStatementUtils.executeQueryWithoutResultSet(
				query, type.getName(), type.getId());
	}

	@Override
	public boolean isTypeNameUnique(int forTypeId, String text) {
		String query = EditItemTypesPageSQL.isTypeNameUnique;
		ResultSet rs = SQLStatementUtils.executeQueryAndReturnResultSet(query, text, forTypeId);
		
		boolean isUnique = getSengleCellBooleanValue(rs);
		
		SQLStatementUtils.closeConnectionsWithResultSet();
		
		return isUnique;
	}

}
