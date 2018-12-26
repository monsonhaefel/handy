package com.handy.aws.data_access;


import com.handy.aws.domain.Product;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

import com.amazonaws.services.lambda.runtime.Context;

public class Inventory_TestData implements InventoryDA {
    
    static List<Product> products;   
    
    public List<Product> getAllProducts(Context context) {
    	setUpData();
    	return products;
    }

    public Product getProductById(Integer productId, Context context) {

        Integer index = productId - 100;
        
        if(index < 0 && index > 2) { return null;}
        
        setUpData();
        
        return products.get(index);
    }
    
    public boolean insertProducts(List<Product> productsToInsert, Context context) {
    	setUpData();
    	return products.addAll(productsToInsert);
    }
    
    public boolean updateProducts(List<Product> productsToUpdate, Context context) {
    	setUpData();

    	synchronized (products){
    		
    		System.out.println("^^^^^^^^^ Entering updateProducts() ^^^^^^^^^");
    		
    		ListIterator<Product> updatesIterator = productsToUpdate.listIterator();
    		List<Product> tempProducts = new ArrayList<Product>(products);
    		ListIterator<Product> tempIterator = tempProducts.listIterator();

    		int updateCount = 0;
    		while (updatesIterator.hasNext()) {	
    			Product productToUpdate = updatesIterator.next();
    			int updateId = productToUpdate.getProduct_id();
    			
    			System.out.println("^^^^ update productID="+updateId+"^^^^^^^^^");
    			
    			while (tempIterator.hasNext()) {
    				Product product = tempIterator.next(); 
    				if(product.getProduct_id() == updateId) {
    					tempIterator.set(product);
    					updateCount += 1;
    	    			System.out.println("^^^^^^ productID="+updateId+" updated ^^^^^^^^^");
    				}
    			}
    		}
    		if(productsToUpdate.size() == updateCount) {
    			products = tempProducts;
    			return true;
    		}else {
    			return false;
    		}
    	}// End synchronized
    	
    }
    
    public static void setUpData() {
        if(products == null) {
            System.out.println("********** creating products *******");
            products = new ArrayList<Product>();
            products.add(new Product(100, "Hammer", "Stanley", "(test data) 5oz Magnetic Tack Hammer",    20));
            products.add(new Product(101, "Hammer", "Wilton Bash", "(test data) 24oz Ball Peen",          27));
            products.add(new Product(102, "Hammer", "DeWalt", "(test data) 15oz MIG Weld",                17));
        }
    }
}