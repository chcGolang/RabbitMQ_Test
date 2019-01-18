package com.chc.rabbitmq.springboot_consumer.receiver;

import com.rabbitmq.client.Channel;
import constant.queue_info.OrderQueueInfo;
import model.Order;
import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

/**
 * @author chc
 * @create 2019-01-18 16:23
 **/
@Component
public class RabbitReceiver {

    @RabbitListener(bindings = @QueueBinding(
            exchange = @Exchange(value = "exchange-1",durable = "true"
                    ,type = ExchangeTypes.TOPIC,ignoreDeclarationExceptions = "true"),
            value = @Queue(value = "queue-1",durable = "true"),
            key = "springboot.*"
    ))
    @RabbitHandler
    public void process(@Payload String body, Message message, Channel channel)throws Exception{
        System.out.println("RabbitReceiver.process.message.getBody():"+new String(message.getBody()));
        System.out.println("RabbitReceiver.process.String body:"+body);
        Long deliveryTag = message.getMessageProperties().getDeliveryTag();
        //手工ACK
        channel.basicAck(deliveryTag,false);
    }

    @RabbitListener(bindings = @QueueBinding(
            exchange = @Exchange(value = OrderQueueInfo.EXCHANGE_VALUE,durable = OrderQueueInfo.EXCHANGE_DURABLE
                    ,type = OrderQueueInfo.EXCHANGE_TYPE,ignoreDeclarationExceptions = OrderQueueInfo.EXCHANGE_IGNOREDECLARATIONEXCEPTIONS),
            value = @Queue(value = OrderQueueInfo.QUEUE_VALUE,durable = OrderQueueInfo.QUEUE_DURABLE),
            key = OrderQueueInfo.KEY
    ))
    @RabbitHandler
    public void processOrder(@Payload Order order, Message message, Channel channel)throws Exception{
        System.out.println("RabbitReceiver.processOrder:"+order.getName());
        Long deliveryTag = message.getMessageProperties().getDeliveryTag();
        //手工ACK
        channel.basicAck(deliveryTag,false);
    }
}
