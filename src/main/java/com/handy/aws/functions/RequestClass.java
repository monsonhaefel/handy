package  com.handy.aws.functions;
import java.util.List;
import java.util.Map;

import com.handy.aws.domain.Product;
public class RequestClass {
    
    Map<String, String> queryStringParameters;

    public Map<String, String> getQueryStringParameters() {
        return queryStringParameters;
    }
    public void setQueryStringParameters(Map<String, String> queryStringsMap) {
        queryStringParameters = queryStringsMap;
    }

}