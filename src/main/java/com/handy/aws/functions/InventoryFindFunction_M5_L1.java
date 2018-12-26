package com.handy.aws.functions;

import com.handy.aws.data_access.Inventory_TestData;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.handy.aws.domain.Product;

public class InventoryFindFunction_M5_L1 extends Inventory_TestData implements RequestHandler<Object, String> {
    @Override
    public String handleRequest(Object input, Context context) {
    	Product product = getProductById(101,context);
        return product.toString();
    }
    
}
