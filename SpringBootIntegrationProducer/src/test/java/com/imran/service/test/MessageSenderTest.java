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
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.imran.messaging.MessageSender;
import com.imran.model.Product;

@RunWith(MockitoJUnitRunner.class)
public class MessageSenderTest {

	
	@InjectMocks
	MessageSender messageSender;
	
	
	@Mock
    JmsTemplate jmsTemplate;
	
	@Mock
	Product product;
	
	private MockMvc mockMvc;
	
	@Before
    public void setup() { 

        
        MockitoAnnotations.initMocks(this);
      //  mockMvc = MockMvcBuilders.standaloneSetup().build();
        mockMvc = MockMvcBuilders.standaloneSetup(messageSender).build();
        
        
    }
	
	
	@Test
	public void sentMessageTest()
	{
		messageSender.sendMessage(product);
		
		verify(jmsTemplate).send(any(MessageCreator.class));
		
		
		
	}
}
