package com.imran.messaging;
 
import java.util.HashMap;
import java.util.Map;

import javax.jms.DeliveryMode;
import javax.jms.JMSException;
import javax.jms.Message;
//import javax.jms.Message;
import javax.jms.ObjectMessage;
import javax.jms.Session;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.messaging.MessageHeaders;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.imran.model.InventoryResponse;
 
//@Component
@Service
public class MessageSender {
 
    @Autowired
    JmsTemplate jmsTemplate;
    static final Logger LOG = LoggerFactory.getLogger(MessageReceiver.class);
 
    public void sendMessage(final InventoryResponse inventoryResponse) {
 
        jmsTemplate.send(new MessageCreator(){
                @Override
                public Message createMessage(Session session) throws JMSException{
                    ObjectMessage objectMessage = session.createObjectMessage(inventoryResponse);
                    
                   
                   //adding header in the payload.
                    objectMessage.setJMSCorrelationID("Consumer_id_senderm");
                    
                    
                    
                    LOG.info("-----------------------------------------------------");
                    LOG.info("Consumer sent");
                    
                    LOG.info("-----------------------------------------------------");
                    
                    return objectMessage;
                }
            });
        

    	
        
    }
 
}