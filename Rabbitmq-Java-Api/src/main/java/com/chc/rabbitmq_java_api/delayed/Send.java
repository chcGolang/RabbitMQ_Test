package com.chc.rabbitmq_java_api.delayed;

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
    private final static String EXCHANGE_NAME="delay_exchange";
    private final static String ROUTING_KEY="delay_key";

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

        Map<String, Object> headers = new HashMap<>();
        Date now = new Date();

        String readyToPushContent = "消息发送时间:"+sf.format(now);

        // 设置延迟时长
        headers.put("x-delay", 10000);

        AMQP.BasicProperties.Builder props = new AMQP.BasicProperties.Builder()
                .headers(headers);
        channel.basicPublish(EXCHANGE_NAME, ROUTING_KEY, props.build(),
                readyToPushContent.getBytes());

        // 关闭频道和连接
        channel.close();
        connection.close();
    }
}

