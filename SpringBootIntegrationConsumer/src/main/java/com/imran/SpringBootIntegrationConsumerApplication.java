package com.imran;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;

@SpringBootApplication
public class SpringBootIntegrationConsumerApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootIntegrationConsumerApplication.class, args);
		AbstractApplicationContext  context = new AnnotationConfigApplicationContext(SpringBootIntegrationConsumerApplication.class);

		try {
            Thread.sleep(600000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
 
        ((AbstractApplicationContext) context).close();
	}
}
