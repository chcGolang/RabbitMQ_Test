package com.chc.rabbitmq_spting.send;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessagePostProcessor;

/**
 * @author chc
 * @create 2019-01-17 17:57
 **/
public class MyMessageProcessor implements MessagePostProcessor {

    public static MessagePostProcessor Build(Message message){
        MyMessageProcessor processor = new MyMessageProcessor();
        processor.postProcessMessage(message);
        return processor;
    }

    @Override
    public Message postProcessMessage(Message message) {
        return message;
    }
}
