package com.chc.rabbitmq_java_api.affair;

import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.QueueingConsumer;
import constant.RabbitMQConstant;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 消费端
 * @author chc
 * @create 2019-01-26 14:37
 **/
public class Recv {

    // 队列名称
    private final static String QUEUE_NAME = "affair_queue";
    private final static String EXCHANGE_NAME="affair_exchange";
    private final static String ROUTING_KEY="affair_key";


    public static void main(String[] argv) throws Exception{
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

        channel.exchangeDeclare(EXCHANGE_NAME, BuiltinExchangeType.DIRECT.getType(), true,
                false, null);

        //4 声明（创建）一个队列
        channel.queueDeclare(QUEUE_NAME, true, false, false, null);
        //5 创建消费者
        QueueingConsumer queueingConsumer = new QueueingConsumer(channel);
        channel.queueBind(QUEUE_NAME,EXCHANGE_NAME,ROUTING_KEY);

        //6 设置Channel
        channel.basicConsume(QUEUE_NAME, true, queueingConsumer);
        try {

            while(true){
                QueueingConsumer.Delivery delivery = queueingConsumer
                        .nextDelivery();
                String message = (new String(delivery.getBody()));
                System.out.println("message:"+message);
            }

        } catch (Exception exception) {
            exception.printStackTrace();

        }

    }
}