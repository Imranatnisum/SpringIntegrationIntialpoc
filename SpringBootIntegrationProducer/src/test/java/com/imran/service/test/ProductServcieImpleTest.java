package com.imran.service.test;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.verify;

import javax.jms.Message;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.support.converter.MessageConverter;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.imran.messaging.MessageSender;
import com.imran.model.InventoryResponse;
import com.imran.model.Product;
//import com.imran.service.OrderService;
import com.imran.service.ProductServiceImpl;

@RunWith(MockitoJUnitRunner.class)
public class ProductServcieImpleTest {
	
	
private MockMvc mockMvc;
	
	@InjectMocks
	ProductServiceImpl productService;
	
	@Mock
	MessageSender messageSender;
	
	
	
	@Mock
	Message message;
	
	@Before
    public void setup() { 

        
        MockitoAnnotations.initMocks(this);
      //  mockMvc = MockMvcBuilders.standaloneSetup().build();
        mockMvc = MockMvcBuilders.standaloneSetup(productService).build();
        
         
    }
	
	
	@Test
	public void sendProductTest()
	{
		
Product product=new Product();
		
		product.setName("testname");
		
		product.setProductId("testproductid");
		
		product.setQuantity(1);
		productService.sendProduct(product);
		
		
		
		verify(messageSender).sendMessage(any(Product.class));
		
	}

}
