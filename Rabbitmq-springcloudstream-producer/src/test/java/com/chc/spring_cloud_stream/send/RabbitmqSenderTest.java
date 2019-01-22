package com.chc.spring_cloud_stream.send;

import com.chc.spring_cloud_stream.SpringCloudStreamApplicationTests;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;

@Component
public class RabbitmqSenderTest extends SpringCloudStreamApplicationTests {

    private static SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");


    @Autowired
    RabbitmqSender rabbitmqSender;

    @Test
    public void sendMessage() {
        for(int i = 0; i < 1; i ++){
            try {
                Map<String, Object> properties = new HashMap<String, Object>();
                properties.put("SERIAL_NUMBER", "12345");
                properties.put("BANK_NUMBER", "abc");
                properties.put("PLAT_SEND_TIME", simpleDateFormat.format(new Date()));
                rabbitmqSender.sendMessage("Hello, I am amqp sender num :" + i, properties);

            } catch (Exception e) {
                System.out.println("--------error-------");
                e.printStackTrace();
            }
        }
        //TimeUnit.SECONDS.sleep(Integer.MAX_VALUE);
    }
}