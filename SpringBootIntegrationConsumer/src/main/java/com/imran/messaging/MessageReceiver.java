package com.imran.messaging;
 
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.support.converter.MessageConverter;
import org.springframework.stereotype.Service;

import com.imran.model.Product;
import com.imran.service.OrderService;
 
//@Component
@Service
public class MessageReceiver implements MessageListener{
 
    static final Logger LOG = LoggerFactory.getLogger(MessageReceiver.class);
     
    @Autowired
    MessageConverter messageConverter;
      
    @Autowired
    OrderService orderService;
 
     
    @Override
    public void onMessage(Message message)  {
        try {
            LOG.info("-----------------------------------------------------");
            String id=message.getJMSCorrelationID();
           
            Product product = (Product) messageConverter.fromMessage(message);
            LOG.info("Application : Product received by customer : {}",product); 
            LOG.info("CORELATION ID FOR RECEIVED PRODUCT BY CUSTOMER IS "+id);
           orderService.processOrder(product);
            LOG.info("-----------------------------------------------------");
        } catch (JMSException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
         
    }
}