package com.imran.service.test;

import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.when;

import javax.jms.Message;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jms.support.converter.MessageConverter;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.client.RestTemplate;

import com.imran.messaging.MessageReceiver;
import com.imran.model.InventoryResponse;
import static org.hamcrest.CoreMatchers.equalTo;


@RunWith(MockitoJUnitRunner.class)
public class MessageReceiverTest {
	
	private MockMvc mockMvc;
	
	@InjectMocks
	MessageReceiver messageReceiver;
	
	@Mock
	 MessageConverter messageConverter;
	
	@Mock
	RestTemplate restTemplate;
	
	@Mock
	ResponseEntity<InventoryResponse> responseEntity;
	
	
	
	
	
	@Mock
	Message message;
	
	@Before
    public void setup() { 

        
        MockitoAnnotations.initMocks(this);
      //  mockMvc = MockMvcBuilders.standaloneSetup().build();
        mockMvc = MockMvcBuilders.standaloneSetup(messageReceiver).build();
        
         
    }
	
	
	@Test
	public void onMessageTest() throws Exception{
	
 InventoryResponse inventoryResponse=new InventoryResponse();
		 
		 inventoryResponse.setComment("test comments");
		 
		 inventoryResponse.setProductId("test id");
		 
		 inventoryResponse.setReturnCode(1);
		when(messageConverter.fromMessage(message)).thenReturn(inventoryResponse);
		
		// ResponseEntity<InventoryResponse> entity=new ResponseEntity<InventoryResponse>(inventoryResponse, HttpStatus.OK);
		 
		 when(restTemplate.postForEntity("http://localhost:8080/receive/receive", inventoryResponse,InventoryResponse.class)).thenReturn(responseEntity);
		
		
		 ResponseEntity<InventoryResponse> responseEntity = new ResponseEntity<>(inventoryResponse,
                 null,
                 HttpStatus.OK);

		 
		 assertThat(responseEntity.getBody().getComment(),equalTo("test comments"));
		 
		 assertThat(responseEntity.getBody().getProductId(),equalTo("test id"));
		 
		 assertThat(responseEntity.getBody().getReturnCode(),equalTo(1));
		 
		 
		 
	}
	
	
	

}
