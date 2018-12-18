package com.handy.aws.data_access;


public class InventoryDAFactory {

    public static InventoryDA create(String typeDAString) {
    	
       if(typeDAString.equalsIgnoreCase(InventoryDAType.MySQL.getTextValue())) {
    		return InventoryDAFactory.create(InventoryDAType.MySQL);
    	}else {
    		return InventoryDAFactory.create(InventoryDAType.Test_Data);
    	}
    }
	public static InventoryDA create(InventoryDAType typeDA) {
	
		switch(typeDA) {
		case MySQL:
			return new Inventory_MySQL();
		default:// Test_Data
			return new Inventory_TestData();
		}
	}

}
