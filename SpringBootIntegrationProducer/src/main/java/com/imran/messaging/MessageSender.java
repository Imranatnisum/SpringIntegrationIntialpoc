package com.imran.messaging;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.ObjectMessage;
import javax.jms.Session;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Service;

import com.imran.model.Product;

//@Component
@Service
public class MessageSender {

	@Autowired
	JmsTemplate jmsTemplate;
	static final Logger LOG = LoggerFactory.getLogger(MessageReceiver.class);

	public void sendMessage(final Product product) {

		jmsTemplate.send(new MessageCreator(){
				@Override
				public Message createMessage(Session session) throws JMSException{
					ObjectMessage objectMessage = session.createObjectMessage(product);
					
					objectMessage.setJMSCorrelationID("Producer_id_sendermethod");
					
					
					 LOG.info("-----------------------------------------------------");
	                    LOG.info("Producer sent");
	                    
	                    
	                    
	                    LOG.info("-----------------------------------------------------");
					return objectMessage;
				}
			});
		
		
		try {
            //Thread.sleep(600000);
			
			Thread.sleep(60);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
	}

}
