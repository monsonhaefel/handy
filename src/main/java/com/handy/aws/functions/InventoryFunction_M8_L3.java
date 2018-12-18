package com.handy.aws.functions;
import com.handy.aws.data_access.InventoryDA;
import com.handy.aws.data_access.InventoryDAFactory;
import com.handy.aws.data_access.InventoryDAType;
import com.handy.aws.data_access.Inventory_MySQL;

import java.util.ArrayList;
import java.util.List;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.handy.aws.domain.Product;


public class InventoryFunction_M8_L3 implements RequestHandler<RequestClass, ResponseInventory> {
    @Override
    public ResponseInventory handleRequest(RequestClass request, Context context){
        
        String daTypeString = System.getenv("InventoryDAType");
        InventoryDA da = InventoryDAFactory.create(daTypeString);
        ResponseInventory response = new ResponseInventory();
        
    	String id = (String)request.queryStringParameters.getOrDefault("id", "-1");
    	
    	if(id.equalsIgnoreCase("All")) {
    		List<Product> products = da.getAllProducts(context);
    		if(products != null) {
    			response.setBody(products);
    		}
    	}else {
    		Integer productId = Integer.parseInt(id);
    		Product product = da.getProductById(productId, context);
    		List<Product> products = new ArrayList<Product>();
    		products.add(product);
            if(product != null){
                response.setBody(products);
            }
    	}

        return response;
    }
}
