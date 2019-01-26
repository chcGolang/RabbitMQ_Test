package com.chc.rabbitmq_java_api.delayed;

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
    private final static String QUEUE_NAME = "delay_queue";
    private final static String EXCHANGE_NAME="delay_exchange";
    private final static String ROUTING_KEY="delay_key";


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

        // 声明x-delayed-type类型的exchange
        Map<String, Object> args = new HashMap<>();
        args.put("x-delayed-type", "direct");
        // x-delayed-message 使用延迟队列
        channel.exchangeDeclare(EXCHANGE_NAME, "x-delayed-message", true,
                false, args);

        //4 声明（创建）一个队列
        channel.queueDeclare(QUEUE_NAME, true, false, false, null);
        //5 创建消费者
        QueueingConsumer queueingConsumer = new QueueingConsumer(channel);
        channel.queueBind(QUEUE_NAME,EXCHANGE_NAME,ROUTING_KEY);

        //6 设置Channel
        channel.basicConsume(QUEUE_NAME, true, queueingConsumer);
        SimpleDateFormat sf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
        try {
            System.out.println("****************WAIT***************");
            while(true){
                QueueingConsumer.Delivery delivery = queueingConsumer
                        .nextDelivery(); //

                String message = (new String(delivery.getBody()));
                System.out.println("message:"+message);
                System.out.println("now:\t"+sf.format(new Date()));
            }

        } catch (Exception exception) {
            exception.printStackTrace();

        }

    }
}