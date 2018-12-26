package com.handy.aws.data_access;

import java.util.List;

import com.amazonaws.services.lambda.runtime.Context;
import com.handy.aws.domain.Product;

public interface InventoryDA {

    public Product getProductById(Integer productId, Context context);
    
    public List<Product> getAllProducts(Context context);
    
    public boolean insertProducts(List<Product> productsToInsert, Context context);
    
    public boolean updateProducts(List<Product> productsToUpdate, Context context);

}
