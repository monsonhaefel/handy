package com.handy.aws.functions;
//import com.handy.aws.data_access.InventoryDA;
//import com.handy.aws.data_access.InventoryDAFactory;


import java.util.ArrayList;
import java.util.List;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

import com.handy.aws.domain.Product;
import com.handy.aws.data_access.*;


public class InventoryUpdateFunction_M9_L2 implements RequestHandler<RequestProductsUpdate, ResponseProductsUpdate> {
    @Override
    public ResponseProductsUpdate handleRequest(RequestProductsUpdate request, Context context){
        
    	
        String daTypeString = System.getenv("InventoryDAType");
        InventoryDA da = InventoryDAFactory.create(daTypeString);
        ResponseProductsUpdate response = new ResponseProductsUpdate();
        
    	List<Product> productToUpdate = request.getBody();
    	
    	Boolean success = da.updateProducts(productToUpdate, context);
    	response.setBody(success);
        return response;

        
    }
}
