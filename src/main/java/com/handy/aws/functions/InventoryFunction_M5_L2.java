package com.handy.aws.functions;


import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

import com.handy.aws.domain.Product;
import com.handy.aws.data_access.Inventory_TestData;

import com.handy.aws.functions.RequestClass;
import com.handy.aws.functions.ResponseClass;

public class InventoryFunction_M5_L2 extends Inventory_TestData implements RequestHandler<RequestClass, ResponseClass>{  
    
    
    @Override
    public ResponseClass handleRequest(RequestClass request, Context context){
        
        
        String ids = (String)request.queryStringParameters.getOrDefault("id", "-1");
        
        Integer idi = Integer.parseInt(ids);
        
        Product product = getProductById(idi, context);
        
        if(product != null){
            
            ResponseClass response = new ResponseClass();
            response.setBody("Product Selected is: " + product.toString());
            return response;
            
        }else {
        
            ResponseClass response = new ResponseClass();
            response.setBody("Error. Id submitted was : " + idi);
            return response;
        }
        
    }

}
