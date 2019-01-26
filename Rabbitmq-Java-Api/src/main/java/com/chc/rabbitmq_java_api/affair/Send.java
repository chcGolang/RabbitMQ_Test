package com.chc.rabbitmq_java_api.affair;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import constant.RabbitMQConstant;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 消息发送端
 * @author chc
 * @create 2019-01-26 14:31
 **/
public class Send {
    // 队列名称
    private final static String EXCHANGE_NAME="affair_exchange";
    private final static String ROUTING_KEY="affair_key";

    @SuppressWarnings("deprecation")
    public static void main(String[] argv) throws Exception {
        /**
         * 创建连接连接到MabbitMQ
         */
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost(RabbitMQConstant.RABBIT_HOST);
        factory.setPort(RabbitMQConstant.RABBIT_PORT);
        //factory.setVirtualHost("/");
        factory.setUsername(RabbitMQConstant.RABBIT_USETNAME);
        factory.setPassword(RabbitMQConstant.RABBIT_PASSWORD);
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();

        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");

        String msg = "Hello  Simple QUEUE !";
        try {
            channel.txSelect(); // 开启事务
            channel.basicPublish(EXCHANGE_NAME, ROUTING_KEY, null, msg.getBytes());
            int result = 1 / 0;
            channel.txCommit(); // 提交事务
        } catch (Exception e) {
            channel.txRollback(); // 回滚
            System.out.println("----msg rollabck ");
        } finally {
            channel.close();
            connection.close();
        }

    }
}

