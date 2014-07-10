package com.rabbitmq.sample;

import java.io.IOException;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

/**
 * Represents a connection with a queue
 * @author syntx
 *
 */
public abstract class EndPoint{
	
    protected Channel channel;
    protected Connection connection;
    protected String endPointName;
	
    public EndPoint(String endpointName) throws IOException{
         this.endPointName = endpointName;
		
         //Create a connection factory
         ConnectionFactory factory = new ConnectionFactory();
	    
         //other host property may be used
         /*factory.setUsername(userName);
         factory.setPassword(password);
         factory.setVirtualHost(virtualHost);
         factory.setHost(hostName);
         factory.setPort(portNumber);
         
         alt we can use
         factory.setUri("amqp://userName:password@hostName:portNumber/virtualHost");
         
         */
         
         //hostname of your rabbitmq server
         factory.setHost("localhost");
		
         //getting a connection
         connection = factory.newConnection();
	    
         //creating a channel
         channel = connection.createChannel();
	    
         //declaring a queue for this channel. If queue does not exist,
         //it will be created on the server.
         channel.queueDeclare(endpointName, false, false, false, null);
    }
	
	
    /**
     * Close channel and connection. Not necessary as it happens implicitly any way. 
     * @throws IOException
     */
     public void close() throws IOException{
         this.channel.close();
         this.connection.close();
     }
}