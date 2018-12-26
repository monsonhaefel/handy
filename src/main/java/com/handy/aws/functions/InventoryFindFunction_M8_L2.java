package com.handy.aws.functions;
import com.handy.aws.data_access.InventoryDA;
import com.handy.aws.data_access.InventoryDAFactory;
import com.handy.aws.data_access.InventoryDAType;
import com.handy.aws.data_access.Inventory_MySQL;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.handy.aws.domain.Product;


public class InventoryFindFunction_M8_L2 implements RequestHandler<RequestClass, ResponseProduct> {
    @Override
    public ResponseProduct handleRequest(RequestClass request, Context context){
        
    	String id = (String)request.queryStringParameters.getOrDefault("id", "-1");
        Integer productId = Integer.parseInt(id);
        
        String daTypeString = System.getenv("InventoryDAType");
        InventoryDA da = InventoryDAFactory.create(daTypeString);
        
        Product product = da.getProductById(productId, context);
        
        ResponseProduct response = new ResponseProduct();
        
        if(product != null){
            response.setBody(product);
        }
        return response;
    }
}