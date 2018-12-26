package com.handy.aws.functions;
import com.handy.aws.data_access.InventoryDA;
import com.handy.aws.data_access.InventoryDAFactory;


import java.util.ArrayList;
import java.util.List;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.handy.aws.domain.Product;


public class Old_Junk_InventoryFunction_M9_L1 implements RequestHandler<RequestClass, ResponseHTML> {
    @Override
    public ResponseHTML handleRequest(RequestClass request, Context context){
        
        String daTypeString = System.getenv("InventoryDAType");
        InventoryDA da = InventoryDAFactory.create(daTypeString);
        
    	String id = (String)request.queryStringParameters.getOrDefault("id", "-1");
    	
    	List<Product> products = null;
    	
    	if(id.equalsIgnoreCase("All")) {
    		products = da.getAllProducts(context);
    	}else {
    		Integer productId = Integer.parseInt(id);
    		Product product = da.getProductById(productId, context);
    		products = new ArrayList<Product>();
    		products.add(product);
    	}
    	
    	ResponseHTML response = new ResponseHTML();
    	if(products != null) {
    		String htmlText = generateHtml(products);
            response.setBody(htmlText);
    	}

        return response;
    }
    public String generateHtml(List<Product> products) {
    	return startHtml()+htmlTable(products)+endHtml();
    }
    
    public String startHtml() {
    	String htmlText = 
        		"<!DOCTYPE html>"
        		+ "<html>"
        			+ "<body>";
    	return htmlText;
    }
    public String endHtml() {
    	String htmlText = 
        		"</body>"
    			+ "</html>";
    	return htmlText;
    }
    public String htmlColumn(int value) {
    	return htmlColumn(Integer.toString(value));
    }
    public String htmlColumn(String value) {
    	return "<td>"+value+"<td>";
    }
    public String htmlTable(List<Product> products) {
 	
    	String htmlText = "<table><tr>"
    						+ htmlColumn("Product ID")
    						+ htmlColumn("Tool Type")
    						+ htmlColumn("Brand")
    						+ htmlColumn("Name")
    						+ htmlColumn("Count")
    						+ "</tr>";
    	
    	
    	for(Product prod : products) {
    		htmlText += "<tr>"  
    				 	+ htmlColumn(prod.getProduct_id())
						+ htmlColumn(prod.getToolType())
						+ htmlColumn(prod.getBrand())
						+ htmlColumn(prod.getName())
						+ htmlColumn(prod.getCount())
	                  + "</tr>";
    		
    	}
    	return htmlText;
    							
    }
}
