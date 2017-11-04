package com.hiekn.demo.test.mq;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

public class RabbitMqFactory {
	
	public static Connection getConnection(String username,String password,String host,String virtualHost,int port) throws IOException, TimeoutException{
		ConnectionFactory factory = new ConnectionFactory();
		factory.setUsername(username);
		factory.setPassword(password);
		factory.setHost(host);
		factory.setVirtualHost(virtualHost);
		factory.setPort(port);
		return factory.newConnection();
	}
	
}
