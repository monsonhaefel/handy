package com.handy.aws.functions;


import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

import com.handy.aws.domain.Product;
import com.handy.aws.data_access.Inventory_MySQL;

import com.handy.aws.functions.RequestClass;
import com.handy.aws.functions.ResponseProduct;

public class InventoryFindFunction_M7_L4 extends Inventory_MySQL implements RequestHandler<RequestClass, ResponseProduct>{  
        
    @Override
    public ResponseProduct handleRequest(RequestClass request, Context context){
        
        
        String ids = (String)request.queryStringParameters.getOrDefault("id", "-1");
        
        Integer idi = Integer.parseInt(ids);
        
        Product product = getProductById(idi, context);
        
        ResponseProduct response = new ResponseProduct();
        
        if(product != null){

            response.setBody(product);
        }

        return response;
    }  
}
