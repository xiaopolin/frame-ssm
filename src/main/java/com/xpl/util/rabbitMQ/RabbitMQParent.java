package com.xpl.util.rabbitMQ;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

public class RabbitMQParent {

    private static Properties properties = null;

    protected boolean durable = false;
    public boolean isDurable() {
        return durable;
    }
    public void setDurable(boolean durable) {
        this.durable = durable;
    }

    static {
        Logger.getLogger("rabbitmq.clients.rabbitmq").setLevel(Level.WARNING);
        Logger.getLogger(RabbitMQParent.class.getName()).setLevel(Level.WARNING);

        properties = new Properties();

        try {
            properties.load(Thread.currentThread().getContextClassLoader().getResourceAsStream("properties/rabbitmq.properties"));
        } catch (IOException e) {
            System.out.println("获取文件异常");
        }
    }

    protected Channel channel;
    protected Connection connection;

    public RabbitMQParent() {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost(properties.getProperty("rabbitmq.host"));
        factory.setPort(AMQP.PROTOCOL.PORT);
        factory.setUsername(properties.getProperty("rabbitmq.userName"));
        factory.setPassword(properties.getProperty("rabbitmq.passWord"));

        try {
            connection = factory.newConnection();
            setChannel(connection.createChannel());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Channel getChannel() {
        return channel;
    }

    public void setChannel(Channel channel) {
        this.channel = channel;
    }

    public void closeResource() {
        try {
            this.channel.close();
            this.connection.close();
        } catch (Exception e) {
            // TODO: handle exception
            System.out.println("rabbitMq释放资源失败：" + e);
        }
    }

}
