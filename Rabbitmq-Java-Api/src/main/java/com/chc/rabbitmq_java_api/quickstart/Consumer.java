package com.chc.rabbitmq_java_api.quickstart;


import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.QueueingConsumer;
import constant.RabbitMQConstant;

/**
 * 消费者
 * @author chc
 * @create 2019-01-16 13:11
 **/
public class Consumer {

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

        //4 声明（创建）一个队列
        String queueName = "test1";
        channel.queueDeclare(queueName, true, false, false, null);

        //5 创建消费者
        QueueingConsumer queueingConsumer = new QueueingConsumer(channel);

        //6 设置Channel
        channel.basicConsume(queueName, true, queueingConsumer);

        while(true){
            //7 获取消息
            QueueingConsumer.Delivery delivery = queueingConsumer.nextDelivery();
            String msg = new String(delivery.getBody());
            System.out.println("消费者: " + msg);
        }

    }
}
