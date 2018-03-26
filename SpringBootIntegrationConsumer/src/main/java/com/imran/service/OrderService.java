package com.imran.service;
 
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.imran.model.Product;
 
@Service
@Qualifier("orderServiceImpl")
public interface OrderService {
 
    public void processOrder(Product product);
}
