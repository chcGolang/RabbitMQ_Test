package com.chc.rabbitmq_java_api.confirm;

import java.io.IOException;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.ConfirmListener;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import constant.RabbitMQConstant;

public class Producer {

	
	public static void main(String[] args) throws Exception {
		
		
		//1 创建ConnectionFactory
		ConnectionFactory connectionFactory = new ConnectionFactory();
		connectionFactory.setHost(RabbitMQConstant.RABBIT_HOST);
		connectionFactory.setPort(RabbitMQConstant.RABBIT_PORT);
		//connectionFactory.setVirtualHost("/");
		connectionFactory.setUsername(RabbitMQConstant.RABBIT_USETNAME);
		connectionFactory.setPassword(RabbitMQConstant.RABBIT_PASSWORD);
		
		//2 获取C	onnection
		Connection connection = connectionFactory.newConnection();
		
		//3 通过Connection创建一个新的Channel
		Channel channel = connection.createChannel();
		
		
		//4 指定我们的消息投递模式: 消息的确认模式 
		channel.confirmSelect();
		
		String exchangeName = "test_confirm_exchange";
		String routingKey = "confirm.save";
		
		//5 发送一条消息
		String msg = "Hello RabbitMQ Send confirm message!";
		channel.basicPublish(exchangeName, routingKey, null, msg.getBytes());
		
		//6 添加一个确认监听(只能说明投递成功,不能说明消费者接收成功)
		channel.addConfirmListener(new ConfirmListener() {
			// 失败的
			@Override
			public void handleNack(long deliveryTag, boolean multiple) throws IOException {
				System.out.println("-------no ack!-----------");
			}

			// 成功时
			@Override
			public void handleAck(long deliveryTag, boolean multiple) throws IOException {
				System.out.println("-------ack!-----------");
			}
		});
		
		
		
		
		
	}
}
