package com.hiekn.demo.test.frame.mq;

import java.io.IOException;

import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.ConfirmListener;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.MessageProperties;

public class RabbitMqPublish {
	
	private final static String QUEUE_NAME = "queue_hello";
	private final static String EXCHANGE_NAME = "exchange_hello";
	private final static String ROUTINGKEY = "rount_hello";
	
	public static void main(String[] args) throws Exception {
		
		Connection connection = RabbitMqFactory.getConnection("test", "test123", "192.168.1.152", "/", 5672);
		
		Channel channel =  connection.createChannel();
		
		channel.exchangeDeclare(EXCHANGE_NAME, BuiltinExchangeType.DIRECT,true);
		
		channel.queueDeclare(QUEUE_NAME, true, false, false, null);
		
		channel.queueBind(QUEUE_NAME, EXCHANGE_NAME, ROUTINGKEY);  
		
		channel.confirmSelect();

		channel.addConfirmListener(new ConfirmListener() {  

			@Override  
			public void handleNack(long deliveryTag, boolean multiple) throws IOException {  
				System.out.println("nack: deliveryTag = "+deliveryTag+" multiple: "+multiple);  
			}  

			@Override  
			public void handleAck(long deliveryTag, boolean multiple) throws IOException {  
				System.out.println("ack: deliveryTag = "+deliveryTag+" multiple: "+multiple);  
			}  
			
		}); 
		
		String message = "Hello World!";
		
		channel.basicPublish(EXCHANGE_NAME, ROUTINGKEY, MessageProperties.PERSISTENT_TEXT_PLAIN, message.getBytes());
		
		System.out.println("Publish " + message);
		
	}
	
}
