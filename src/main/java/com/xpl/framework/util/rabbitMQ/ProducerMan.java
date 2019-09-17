package com.xpl.framework.util.rabbitMQ;

import com.alibaba.fastjson.JSON;

public class ProducerMan extends RabbitMQParent {

    public void sendMessageToQueue(String queueName, Object message) {
        try {
            /*
             * 声明（创建）队列
             * 参数1：队列名称
             * 参数2：为true时server重启后队列不会消失，若前端想连接则必须设置为true
             * 参数3：队列是否是独占的，如果为true只能被一个connection使用，其他连接建立时会抛出异常
             * 参数4：队列不再使用时是否自动删除（没有连接，并且没有未处理的消息)
             * 参数5：建立队列时的其他参数
             */
            getChannel().queueDeclare(queueName, durable, false, false, null);

            //发送数据至queue
            getChannel().basicPublish("", queueName, null, objectToString(message).getBytes());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void sendMessageToExchange(String exchangeName, String routing, Object message) {
        try {
            /*
            声明（创建）exchange交换机
            参数一：交换机名称
            参数二：交换机类型（topic, direct, fanout）
            参数三：交换机持久性，如果为true则服务器重启时不会丢失,默认false(若为true，属性中会加上D)
            参数四：交换机在不被使用时是否删除，默认false(若为true，属性中会加上AD)
            参数五：交换机的其他属性，默认null
         */
            getChannel().exchangeDeclare(exchangeName, "topic", true, true, null);

            //发送消息之交换机，并设置路由键
            getChannel().basicPublish(exchangeName, routing, false, false, null, objectToString(message).getBytes());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static Object lock = new Object();

    private static <T> String objectToString(T _T) {
        synchronized (lock) {
            return JSON.toJSONString(_T);
        }
    }

}
