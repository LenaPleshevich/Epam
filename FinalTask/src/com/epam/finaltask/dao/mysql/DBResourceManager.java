package com.epam.finaltask.dao.mysql;

import java.util.ResourceBundle;

/**
 * 	This class is used to read the parameters of the database from the properties-file
 */
public class DBResourceManager {
	private static final DBResourceManager instance = new DBResourceManager();
	
	private static final ResourceBundle bundle = ResourceBundle.getBundle(DBParameter.DB);
	
	public static DBResourceManager getInstance(){
		return instance;
	}
	public String getValue(String key){
		return bundle.getString(key);
	}
}
