# RabbitMQ 使用案例

# 一 项目结构说明
* `common`:公用模块
* `Rabbitmq-Java-Api`: java使用rabbitmq案例

## Rabbitmq-Java-Api
* `com.chc.rabbitmq_java_api.exchange.direct`: exchange的direct
* `com.chc.rabbitmq_java_api.exchange.fanout` : exchange的fanou
* `com.chc.rabbitmq_java_api.exchange.topic` : exchange的topic
* `com.chc.rabbitmq_java_api.message`: massage的其他属性的使用和消息过期
* `com.chc.rabbitmq_java_api.confirm`: confirm的消息回送机制
* `com.chc.rabbitmq_java_api.returnlistener`: return消息回送机制
* `com.chc.rabbitmq_java_api.consumer`: 自定义消费者
* `com.chc.rabbitmq_java_api.limit`: 消费端限流
* `com.chc.rabbitmq_java_api.ack`: 消费端nack,ack和重回队列
* `com.chc.rabbitmq_java_api.dlx`: 死信队列

## rabbitmq-springboot-consumer
* springboot与rabbitmq整合的接收端

## rabbitmq-sptingboot-producer
* springboot与rabbitmq整合的发送端

## Rabbitmq-springcloudstream-consumer
* spring cloud stream 的接收端

## Rabbitmq-springcloudstream-producer
* spring cloud stream 的发送端
