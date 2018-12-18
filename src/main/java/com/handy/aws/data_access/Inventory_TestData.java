package com.handy.aws.data_access;


import com.handy.aws.domain.Product;

import java.util.ArrayList;
import java.util.List;

import com.amazonaws.services.lambda.runtime.Context;

public class Inventory_TestData implements InventoryDA {
    
    List<Product> products;
    
    public List<Product> getAllProducts(Context context) {
    	this.setUpData();
    	return products;
    }

    public Product getProductById(Integer productId, Context context) {

        Integer index = productId - 100;
        
        if(index < 0 && index > 2) { return null;}
        
        this.setUpData();
        
        System.out.println("****** about to return product id = " + productId);
        return products.get(index);
    }
    
    public void setUpData() {
        if(products == null) {
            System.out.println("********** creating products *******");
            products = new ArrayList<Product>();
            products.add(new Product(100, "Hammer", "Stanley", "(test data) 5oz Magnetic Tack Hammer",    20));
            products.add(new Product(101, "Hammer", "Wilton Bash", "(test data) 24oz Ball Peen",          27));
            products.add(new Product(102, "Hammer", "DeWalt", "(test data) 15oz MIG Weld",                17));
        }
    }
}