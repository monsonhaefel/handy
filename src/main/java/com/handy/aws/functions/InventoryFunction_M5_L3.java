package com.handy.aws.functions;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

import com.handy.aws.domain.Product;
import com.handy.aws.data_access.Inventory_TestData;
import com.handy.aws.functions.RequestClass;
import com.handy.aws.functions.ResponseHTML;

public class InventoryFunction_M5_L3 extends Inventory_TestData implements RequestHandler<RequestClass, ResponseHTML>{  
    
   
    @Override
    public ResponseHTML handleRequest(RequestClass request, Context context){
        
        
        String ids = (String)request.queryStringParameters.getOrDefault("id", "-1");
        
        Integer idi = Integer.parseInt(ids);
        
        Product product = getProductById(idi, context);
        
        
        if(product != null){
            
            
            String htmlText = 
            		"<!DOCTYPE html>"
            		+ "<html>"
            			+ "<body>"
            				+ "<h1> The Product Id is "+ product.getProduct_id() +"</1>"
            			+ "</body>"
        			+ "</html>";
            return new ResponseHTML(htmlText);
            
        }else {
        
            ResponseHTML response = new ResponseHTML();
            response.statusCode = "400";
            return response;
        }
    }
}