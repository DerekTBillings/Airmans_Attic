package com.billings.jdbc.sql;

public class EditAtticInfoPageSQL {
	
	private static String getNote = "select note "+
			"from notes "+
			"where note_type = '%s'";
	
	public static String getAtticInfo = String.format(getNote, "Attic Info");
	
	public static String getAtticMessage = String.format(getNote, "Attic Message");
	
	private static String updateNote = "update notes "+
			"set note = ? "+
			"where note_type = '%s'";
	
	public static String updateAtticInfo = String.format(updateNote, "Attic Info");
	
	public static String updateAtticMessage = String.format(updateNote, "Attic Message");
	
	public static String getPersonNote = "select n.note_id, note, count(*) "+
			"from notes n "+
			"where person_id = ?";
	
	public static String addPersonNote = "insert into notes "+
			"values(null, ?, 'Person', ?)";

	public static String updatePersonNote = "update notes "+
			"set note = ? "+
			"where note_id = ?";
	
}
