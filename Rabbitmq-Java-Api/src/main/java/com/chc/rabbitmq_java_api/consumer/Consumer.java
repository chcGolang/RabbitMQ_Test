package com.chc.rabbitmq_java_api.consumer;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import constant.RabbitMQConstant;

public class Consumer {

	
	public static void main(String[] args) throws Exception {
		
		
		ConnectionFactory connectionFactory = new ConnectionFactory();
		connectionFactory.setHost(RabbitMQConstant.RABBIT_HOST);
		connectionFactory.setPort(RabbitMQConstant.RABBIT_PORT);
		//connectionFactory.setVirtualHost("/");
		connectionFactory.setUsername(RabbitMQConstant.RABBIT_USETNAME);
		connectionFactory.setPassword(RabbitMQConstant.RABBIT_PASSWORD);
		
		Connection connection = connectionFactory.newConnection();
		Channel channel = connection.createChannel();
		
		
		String exchangeName = "test_consumer_exchange";
		String routingKey = "consumer.#";
		String queueName = "test_consumer_queue";
		
		channel.exchangeDeclare(exchangeName, "topic", true, false, null);
		channel.queueDeclare(queueName, true, false, false, null);
		channel.queueBind(queueName, exchangeName, routingKey);
		
		channel.basicConsume(queueName, true, new MyConsumer(channel));
		
		
	}
}
