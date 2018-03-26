package com.imran;
 
import java.util.concurrent.atomic.AtomicInteger;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;

import com.imran.model.Product;
import com.imran.service.ProductService;
import com.imran.util.BasicUtil;
@SpringBootApplication 
public class SpringBootIntegrationProducerApplication {
 
    static final Logger LOG = LoggerFactory.getLogger(SpringBootIntegrationProducerApplication.class);
     
    private static AtomicInteger id = new AtomicInteger();
     
    public static void main(String[] args){
    	AbstractApplicationContext  context = new AnnotationConfigApplicationContext(
        		SpringBootIntegrationProducerApplication.class);
  
        ProductService productService = (ProductService) context.getBean("productServiceImpl");
         
         
        Product product = getProduct();
        LOG.info("Application : sending order {}", product);
        productService.sendProduct(product);
         
        try {
            Thread.sleep(600000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
 
        ((AbstractApplicationContext)context).close();
    }
     
     
    private static Product getProduct(){
        Product p = new Product();
        p.setName("Product "+id.incrementAndGet());
        p.setProductId(BasicUtil.getUniqueId());
        p.setQuantity(2);
        return p;
    }
}