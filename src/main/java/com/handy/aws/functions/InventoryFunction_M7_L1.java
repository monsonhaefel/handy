package com.handy.aws.functions;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Properties;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.handy.aws.domain.Product;
import com.handy.aws.functions.RequestClass;
import com.handy.aws.functions.ResponseProduct;

public class InventoryFunction_M7_L1 implements RequestHandler<RequestClass, ResponseProduct>{  
    
     private static final String RDS_INSTANCE_HOSTNAME = "handydev.cabbkm3wjh6o.us-east-1.rds.amazonaws.com";
    private static final int RDS_INSTANCE_PORT = 3306;
    private static final String REGION_NAME = "us-east-1";
    private static final String DB_USER = "masteruser";
    private static final String DB_PASS = "12345678";
    private static final String DB_NAME = "handydev";
    private static final String JDBC_URL = "jdbc:mysql://" + RDS_INSTANCE_HOSTNAME + ":" + RDS_INSTANCE_PORT + "/HandyDev";
    @Override
    public ResponseProduct handleRequest(RequestClass request, Context context){
        
        
        String ids = (String)request.queryStringParameters.getOrDefault("id", "-1");
        
        Integer idi = Integer.parseInt(ids);
        
        context.getLogger().log("******* idi = " + idi + "**********");
        
        Product product = null;
        
        try {
            
            Connection con = DriverManager.getConnection(JDBC_URL, getDBProperties());
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM HandyDev.inventory WHERE product_id = " + idi);
            while (rs.next()) {
                  String toolType = rs.getString("tool_type");
                  String brand = rs.getString("brand");
                  String name = rs.getString("name");
                  String countStr = rs.getString("count");
                  int count = Integer.parseInt(countStr);
                  Product prod = new Product(idi, toolType, brand, name, count);
                  context.getLogger().log("Product Found: " + prod.toString());
                  product = prod;
            }
            
        }catch( Exception e) {
            context.getLogger().log("JDBC exception: " + e.toString());
        }
        context.getLogger().log("finished try/catch");
        
        ResponseProduct response = new ResponseProduct();
        
        if(product != null){
            response.setBody(product);
        }
        return response;
        
        
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
