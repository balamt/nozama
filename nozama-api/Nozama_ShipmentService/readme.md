# Nozama - Shipment Service - Spring boot + Batch (Nozama_DeliveryScheduler)
--------------------------------------

* Track Shipment
	* Use Batch Scheduler to fake the transit status.
	* Which should update the status code in order.
* Cancel Shipment
	* If the Order is cancelled, Cancel the Shipment (Cancel cannot be done when the status are in SHIP_DELIVERED)
* Return Package
	* When user is not available or Order is cancelled, return the package (Should be mocked/faked)
* Deliver Package
	* Randomly Change the Status of Package to Delivered based on the transmit info and delivery date. Use Batch to Update the transmit location status.

Shipment Service uses the Port **8083**

It uses the Nozama_EurekaServer as the Service Discovery application.

If Kafka is Implemented try with RabbitMQ
----------------------------------------------------
## Installing RabbitMQ in Unix or Windows

* [MQ Install in Windows](https://www.rabbitmq.com/install-windows.html)
* [MQ Install in windows manually](https://www.rabbitmq.com/install-windows-manual.html)
* [MQ Install in Linux](https://www.rabbitmq.com/install-debian.html)

 
* RabbitMQ Sample : 
    * [Spring AMQP Tutorial](https://www.rabbitmq.com/tutorials/tutorial-one-spring-amqp.html) 
    * [Messaging using RabbitMQ](https://spring.io/guides/gs/messaging-rabbitmq/) 

-----------------------------------------

## Microservice Reference from DZone
   * [Microservice communication Service to Service](https://dzone.com/articles/microservices-communication-service-to-service)
   * [Feign Client](https://dzone.com/articles/microservices-communication-feign-as-rest-client)
   * [Kafka Docker](https://hub.docker.com/r/bitnami/kafka)
   * [Kafka from Docker](https://towardsdatascience.com/how-to-install-apache-kafka-using-docker-the-easy-way-4ceb00817d8b)

 
