package com.imran.service.test;

import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;

import javax.jms.Message;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.jms.support.converter.MessageConverter;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.imran.messaging.MessageReceiver;
import com.imran.model.Product;
import com.imran.service.OrderService;
import static org.mockito.Matchers.any;



@RunWith(MockitoJUnitRunner.class)
public class MessageReceiverTest {
	
	private MockMvc mockMvc;
	
	@InjectMocks
	MessageReceiver messageReceiver;
	
	@Mock
	 MessageConverter messageConverter;
	
	@Mock
	 OrderService orderService;
	
	@Mock
	Message message;
	
	@Before
    public void setup() { 

        
        MockitoAnnotations.initMocks(this);
      //  mockMvc = MockMvcBuilders.standaloneSetup().build();
        mockMvc = MockMvcBuilders.standaloneSetup(messageReceiver).build();
        
         
    }
	
	
	@Test
	public void onMessageTest() throws Exception
	{
		
		Product product=new Product();
		
		product.setName("testname");
		
		product.setProductId("testproductid");
		
		product.setQuantity(1);
		
		messageReceiver.onMessage(message);
		
		when(messageConverter.fromMessage(message)).thenReturn(product);
		
		verify(orderService).processOrder(any(Product.class));
	}
	
	
	

}
