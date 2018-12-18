package com.handy.aws.functions;
import com.handy.aws.data_access.InventoryDA;
import com.handy.aws.data_access.InventoryDAFactory;
import com.handy.aws.data_access.InventoryDAType;
import com.handy.aws.data_access.Inventory_MySQL;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.handy.aws.domain.Product;

public class InventoryFunction_M8_L1 implements RequestHandler<RequestClass, ResponseProduct> {
    @Override
    public ResponseProduct handleRequest(RequestClass request, Context context){
        
        
        String id = (String)request.queryStringParameters.getOrDefault("id", "-1");
        
        Integer productId = Integer.parseInt(id);
        
        InventoryDA da = InventoryDAFactory.create(InventoryDAType.MySQL);
        
        Product product = da.getProductById(productId, context);
        
        ResponseProduct response = new ResponseProduct();
        
        if(product != null){
            response.setBody(product);
        }
        return response;
    }
}