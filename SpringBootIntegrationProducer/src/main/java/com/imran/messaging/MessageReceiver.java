package com.imran.messaging;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.jms.support.converter.MessageConverter;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.imran.model.InventoryResponse;

//@Component
@Service
public class MessageReceiver implements MessageListener{

	static final Logger LOG = LoggerFactory.getLogger(MessageReceiver.class);
	
	@Autowired
	MessageConverter messageConverter;
	
	
	@Autowired
	RestTemplate restTemplate;
	
	
	@Override
	public void onMessage(Message message) { 
		try {
			LOG.info("+++++++++++++++++++++++++++++++++++++++++++++++++++++");
			String id=message.getJMSCorrelationID();
			
			
			InventoryResponse response = (InventoryResponse) messageConverter.fromMessage(message);
			LOG.info("Application : order response received : {}",response);
			
			LOG.info("CORELATION ID OF RESPONSE MESSAGE IS "+id);
			
			LOG.info("+++++++++++++++++++++++++++++++++++++++++++++++++++++");
			
			 try {
		            Thread.sleep(10000);
		        } catch (InterruptedException e) {
		            e.printStackTrace();
		        }
			 
			 ResponseEntity<InventoryResponse> entity=restTemplate.postForEntity("http://localhost:8080/receive/receive", response,InventoryResponse.class);
			 
			 InventoryResponse inventory=entity.getBody();
			 
			 LOG.info("+++++++++++++++++++++++++++++++++++++++++++++++++++++");
			 LOG.info("REST SERVICE SUCCESSFULLY Intiated  "+inventory.getComment());
			 
			LOG.info("+++++++++++++++++++++++++++++++++++++++++++++++++++++");
		} catch (JMSException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
