package com.billings.jdbc.dao;

import java.util.List;

import com.billings.jdbc.dto.Person;

public interface CustomerInfoPageSetupDAO {

	public List<String> getRankList();
	
	public List<String> getRequiredFields();
	
	public Person getPersonInfoById(int personId);	
}
