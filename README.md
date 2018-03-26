# SpringIntegrationIntialpoc
Uses active mq where three applications communicate with each other by using queues
SpringIntegration-poc

Uses active mq where three applications communicate with each other by using queues

To test intended flow of application

Typical retail producer consumer application

    Run the "SpringIntegrationProducer" application where the application produces one message and puts in queue called "order queqe". Now producer application is waiting for response from "SpringIntegrationConsumer" application on "Order response queue"

    Run "RestService" application , which must be utilised by consumer application.

3)Run the "SpringIntegrationConsumer" application which takes a message from order queue and acknowledges the producer application by sending response message on "order response queue". Aslo consumer application invokes a rest service from "RestService" application.
