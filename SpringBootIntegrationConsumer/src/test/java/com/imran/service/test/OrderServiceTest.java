package com.imran.service.test;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.verify;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.imran.messaging.MessageSender;
import com.imran.model.InventoryResponse;
import com.imran.model.Product;
import com.imran.service.OrderServiceImpl;

@RunWith(MockitoJUnitRunner.class)
public class OrderServiceTest {
	
	@InjectMocks
	OrderServiceImpl orderService;
	
	@Mock
	MessageSender messageSender;
	
	private MockMvc mockMvc;
	
	
	@Before
    public void setup() { 

        
        MockitoAnnotations.initMocks(this);
      //  mockMvc = MockMvcBuilders.standaloneSetup().build();
        mockMvc = MockMvcBuilders.standaloneSetup(orderService).build();
        
        
    }
	
	@Test
	public void processOrderTest()
	{
		
Product product=new Product();
		
		product.setName("testname");
		
		product.setProductId("testproductid");
		
		product.setQuantity(1);
		orderService.processOrder(product);
		
		/*InventoryResponse inventoryResponse=new InventoryResponse();
		
		inventoryResponse.setComment("test comment");
		
		inventoryResponse.setProductId("test id");
		
		inventoryResponse.setReturnCode(2);
		*/
		
		verify(messageSender).sendMessage(any(InventoryResponse.class));
		
	}

}
