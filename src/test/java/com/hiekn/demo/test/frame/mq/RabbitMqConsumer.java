package com.hiekn.demo.test.frame.mq;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.Envelope;

public class RabbitMqConsumer {

	private final static String QUEUE_NAME = "queue_hello";
	
	public static void main(String[] args) throws IOException, TimeoutException {

		Connection connection = RabbitMqFactory.getConnection("test", "test123", "192.168.1.122", "/", 5672);

		Channel channel =  connection.createChannel();

		channel.basicConsume(QUEUE_NAME, false, new DefaultConsumer(channel) {
			@Override
			public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body)
					throws IOException {
				String message = new String(body, "UTF-8");
				System.out.println("Received " + message);
				channel.basicAck(envelope.getDeliveryTag(), false);
			}
		});
		
	}

}
