package com.handy.aws.data_access;


import com.handy.aws.domain.Product;
import com.amazonaws.services.lambda.runtime.Context;

public class Inventory_TestData {
    
    Product [] products;

    public Product getProductById(Integer productId, Context context) {
        
        Integer index = productId - 100;
        
        if(index < 0 && index > 2) { return null;}
        
        
        
        if(products == null) {
            System.out.println("********** creating products *******");
            products = new Product[3];
            products[0] = new Product(100, "Hammer", "Stanley", "5oz Magnetic Tack Hammer",    20);
            products[1] = new Product(101, "Hammer", "Wilton Bash", "24oz Ball Peen",          27);
            products[2] = new Product(102, "Hammer", "DeWalt", "15oz MIG Weld",                17);
        }
        
        System.out.println("****** about to return product id = " + productId);
        return products[index];
    }
}