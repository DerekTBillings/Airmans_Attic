package com.billings.jdbc.dao;

import java.util.HashMap;
import java.util.Map;

import com.billings.jdbc.sql.EditAtticInfoPageSQL;
import com.billings.utils.SQLStatementUtils;

public class EditAtticInfoPagePersonImpl implements EditAtticInfoPageDAO {

	private int personId;
	private Integer noteId;

	public EditAtticInfoPagePersonImpl(int personId) {
		this.personId = personId;
	}

	@Override
	public String getInfo() {
		Map<String, Object> note = getPersonNote();
		
		noteId = (Integer) note.get("note_id");

		return nullCheck((String)note.get("note"));
	}

	@SuppressWarnings("unchecked")
	private Map<String, Object> getPersonNote() {
		String query = EditAtticInfoPageSQL.getPersonNote;
		
		return (Map<String, Object>) SQLStatementUtils.executeQueryForSingleRow(
				query, HashMap.class, personId);
	}

	private String nullCheck(String str) {
		if (str == null)
			return "";
		
		return str;
	}

	@Override
	public void updateInfo(String update) {
		if (noteId == null)
			doNoteInsert(update);
		else
			doNoteUpdate(update);
	}

	private void doNoteInsert(String update) {
		SQLStatementUtils.executeInsert(EditAtticInfoPageSQL.addPersonNote, update, personId);
	}

	private void doNoteUpdate(String update) {
		SQLStatementUtils.executeInsert(EditAtticInfoPageSQL.updatePersonNote, update, noteId);
	}

}
