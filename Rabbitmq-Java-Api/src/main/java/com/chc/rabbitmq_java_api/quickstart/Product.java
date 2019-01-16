package com.chc.rabbitmq_java_api.quickstart;


import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import constant.RabbitMQConstant;

/**
 * 生产者
 * @author chc
 * @create 2019-01-16 13:18
 **/
public class Product {

    public static void main(String[] args) throws Exception {
        // 创建connectionFactory,并配置
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost(RabbitMQConstant.RABBIT_HOST);
        connectionFactory.setPort(RabbitMQConstant.RABBIT_PORT);
        //connectionFactory.setVirtualHost("/");
        connectionFactory.setUsername(RabbitMQConstant.RABBIT_USETNAME);
        connectionFactory.setPassword(RabbitMQConstant.RABBIT_PASSWORD);

        // 连接工厂创建连接
        Connection connection = connectionFactory.newConnection();

        // 创建channel
        Channel channel = connection.createChannel();

        // 发送消息
        String body = "Hello RabbitMQ.";
        channel.basicPublish("","test1",null,body.getBytes());

        // 关闭连接
        channel.close();
        connection.close();

    }
}
