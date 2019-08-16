package com.xpl.util.RabbitMQ;

import com.rabbitmq.client.Consumer;
import com.rabbitmq.client.Envelope;

public class ConsumerMan extends RabbitMQParent {

    /**
     * 消费者监听队列并从队列获取数据
     *
     * @param queueName 队列名称
     */
    public void receiveMessageFromQueue(String queueName, Consumer consumer) {
        try {
            // 不会给该消费者传输多于i条消息，只有i条消息都确认后才会接收下i条消息
            getChannel().basicQos(1);

            /*
             * 声明（创建）队列 参数一：队列名称 参数二：消息是否持久化 参数三：断开后是否删除此队列 参数四：使用完之后是否删除此队列
             * 参数五：其他属性
             */
            getChannel().queueDeclare(queueName, false, false, false, null);

            /*
             * 监听队列，当b为true时，为自动提交（只要消息从队列中获取，无论消费者获取到消息后是否成功消息，都认为是消息已经成功消费），
             * 当b为false时，为手动提交（消费者从队列中获取消息后，服务器会将该消息标记为不可用状态，等待消费者的反馈，
             * 如果消费者一直没有反馈，那么该消息将一直处于不可用状态。
             * 如果选用自动确认,在消费者拿走消息执行过程中出现宕机时,消息可能就会丢失！！） 若使用手动提交模式（b == false）
             * 需使用channel.basicAck(envelope.getDeliveryTag(),false);进行消息确认
             */
            getChannel().basicConsume(queueName, false, consumer);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 从交换机中获取信息
     *
     * @param exchangeName 交换机名称
     * @param routing      路由键 *匹配一个随机单词 #匹配零至多个随机单词
     * @param queueName    队列名称
     */
    public void receiveMessageFromExchange(String exchangeName, String routing, String queueName, Consumer consumer) {
        try {
            getChannel().queueDeclare(queueName, false, false, false, null);

            // 将队列绑定至交换机
            getChannel().queueBind(queueName, exchangeName, routing);

            getChannel().basicQos(1);

            // 开启监听
            getChannel().basicConsume(queueName, false, consumer);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void confirmReceipt(Envelope envelope) {
        try {
            getChannel().basicAck(envelope.getDeliveryTag(), false);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String getMessage(byte message[]) {
        String result = "";
        try {
            result = new String(message, "UTF-8");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

}
