package com.chc.rabbitmq_java_api.message;

import java.util.HashMap;
import java.util.Map;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import constant.RabbitMQConstant;

public class Procuder {

	
	public static void main(String[] args) throws Exception {
		//1 创建一个ConnectionFactory, 并进行配置
		ConnectionFactory connectionFactory = new ConnectionFactory();
		connectionFactory.setHost(RabbitMQConstant.RABBIT_HOST);
		connectionFactory.setPort(RabbitMQConstant.RABBIT_PORT);
		//connectionFactory.setVirtualHost("/");
		connectionFactory.setUsername(RabbitMQConstant.RABBIT_USETNAME);
		connectionFactory.setPassword(RabbitMQConstant.RABBIT_PASSWORD);
		
		//2 通过连接工厂创建连接
		Connection connection = connectionFactory.newConnection();
		
		//3 通过connection创建一个Channel
		Channel channel = connection.createChannel();
		
		Map<String, Object> headers = new HashMap<>();
		headers.put("my1", "111");
		headers.put("my2", "222");
		
		
		AMQP.BasicProperties properties = new AMQP.BasicProperties.Builder()
				// 1:为不持久化,3:持久化
				.deliveryMode(2)
				// 编码
				.contentEncoding("UTF-8")
				// 超时时间
				.expiration("10000")
				// 自定义属性
				.headers(headers)
				.build();
		
		//4 通过Channel发送数据
		for(int i=0; i < 5; i++){
			String msg = "Hello RabbitMQ!";
			//1 exchange   2 routingKey
			channel.basicPublish("", "test1", properties, msg.getBytes());
		}

		//5 记得要关闭相关的连接
		channel.close();
		connection.close();
	}
}
