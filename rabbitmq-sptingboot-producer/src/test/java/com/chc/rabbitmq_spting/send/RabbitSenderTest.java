package com.chc.rabbitmq_spting.send;

import com.chc.rabbitmq_spting.RabbitmqSptingApplicationTests;
import model.Order;
import org.junit.Test;
import org.springframework.amqp.core.MessageBuilder;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;

@Component
public class RabbitSenderTest extends RabbitmqSptingApplicationTests {

    @Autowired
    RabbitSender rabbitSender;

    private static SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
    @Test
    public void send()throws Exception {
        Map<String, Object> properties = new HashMap<>();
        properties.put("number", "12345");
        properties.put("send_time", simpleDateFormat.format(new Date()));
        rabbitSender.send("Hello RabbitMQ For Spring Boot!", properties);

    }

    @Test
    public void sendOrder()throws Exception {
        Order order = new Order("22","33name");
        rabbitSender.sendOrder(order);
    }
}