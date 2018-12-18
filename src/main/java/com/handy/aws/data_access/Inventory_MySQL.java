package com.handy.aws.data_access;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Map;
import java.util.Properties;

import com.handy.aws.domain.Product;
import com.amazonaws.services.lambda.runtime.Context;

public class Inventory_MySQL implements InventoryDA {
    
    private static final String RDS_INSTANCE_HOSTNAME = "handydev.cabbkm3wjh6o.us-east-1.rds.amazonaws.com";
    private static final int RDS_INSTANCE_PORT = 3306;
    private static final String REGION_NAME = "us-east-1";
    private static final String DB_USER = "masteruser";
    private static final String DB_PASS = "12345678";
    private static final String DB_NAME = "handydev";

    
    public Product getProductById(Integer productId, Context context) {
    	
    	Map<String, String> env = System.getenv();
    	String endpoint = env.getOrDefault("DatabaseEndpoint", RDS_INSTANCE_HOSTNAME);
    	
        String JDBC_URL = "jdbc:mysql://" + endpoint + ":" + RDS_INSTANCE_PORT + "/HandyDev";
 
        try {
            
            Connection con = DriverManager.getConnection(JDBC_URL, getDBProperties());
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM HandyDev.inventory WHERE product_id = " + productId);
            while (rs.next()) {
                  String toolType = rs.getString("tool_type");
                  String brand = rs.getString("brand");
                  String name = rs.getString("name");
                  String countStr = rs.getString("count");
                  int count = Integer.parseInt(countStr);
                  Product product = new Product(productId, toolType, brand, name, count);
                  context.getLogger().log("Product Found: " + product.toString());
                  return product;
            }
            
        }catch( Exception e) {
            context.getLogger().log("JDBC exception: " + e.toString());
            return null;
        }
        context.getLogger().log("return  null");
        return null;
    }
    
    public Properties getDBProperties() {
        Properties props = new Properties();
        props.put("user",DB_USER);
        props.put("password", DB_PASS);
        props.put("db_name", DB_NAME);
        props.put("name", DB_NAME);
        props.put("region_name", REGION_NAME);
        return props;
    }

}