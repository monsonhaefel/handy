package com.handy.aws.functions;

import com.handy.aws.domain.Product;

public class ResponseProduct {
    Product body;

	public Product getBody() {
		return body;
	}

	public void setBody(Product body) {
		this.body = body;
	}
}