package  com.handy.aws.functions;

import java.util.List;
import com.handy.aws.domain.Product;

public class RequestProductsUpdate {
    
    List<Product> body;
    
	public List<Product> getBody() {
		return body;
	}

	public void setBody(List<Product> body) {
		this.body = body;
	}
}
