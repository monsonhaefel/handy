package com.handy.aws.data_access;

public enum InventoryDAType {
	
    Test_Data("Test_Data"), 
    MySQL("MySQL");
	
	private String textValue;
	
	InventoryDAType(String txtVal) {
		textValue = txtVal;
	}
	
	public String getTextValue() {
		return textValue;
	}
} 