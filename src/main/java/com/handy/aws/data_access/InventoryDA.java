package com.handy.aws.data_access;

import com.amazonaws.services.lambda.runtime.Context;
import com.handy.aws.domain.Product;

public interface InventoryDA {

    public Product getProductById(Integer productId, Context context);

}
