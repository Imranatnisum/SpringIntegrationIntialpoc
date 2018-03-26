package com.imran.service;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

import com.imran.model.Product;


@Service
@Qualifier("productServiceImpl")
public interface ProductService {

	public void sendProduct(Product product);
	
	
}
