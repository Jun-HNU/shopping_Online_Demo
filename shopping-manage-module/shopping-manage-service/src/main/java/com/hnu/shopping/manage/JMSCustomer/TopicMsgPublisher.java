package com.hnu.shopping.manage.JMSCustomer;

import javax.jms.*;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Hashtable;

public class TopicMsgPublisher {
 
	// Defines the JNDI context factory.
	public final static String JNDI_FACTORY = "weblogic.jndi.WLInitialContextFactory";
 
	// Defines the JNDI provider url.
	public final static String PROVIDER_URL = "t3://localhost:7001/";
 
	// Defines the JMS connection factory for the topic.
	public final static String CONNECTION_FACTORY_JNDI_NAME = "myJMSConnectionFactoryJNDIName";
 
	// Defines the topic, use the topic JNDI name 
	public final static String TOPIC_JNDI_NAME = "myJMSTopicJNDIName"; 
 
	private TopicConnectionFactory tconFactory;
	private TopicConnection topicConnection;
	private TopicSession topicSession;
	private TopicPublisher topicPublisher;
	private Topic topic;
	private TextMessage textMessage;
	private StreamMessage streamMessage;
	private BytesMessage bytesMessage;
	private MapMessage mapMessage;
	private ObjectMessage objectMessage;
	
	/**
	 * get the context object.
	 * 
	 * @return context object
	 * @throws NamingException if operation cannot be performed
	 */
	private static InitialContext getInitialContext() throws NamingException {
		Hashtable table = new Hashtable();
		table.put(Context.INITIAL_CONTEXT_FACTORY, JNDI_FACTORY);
		table.put(Context.PROVIDER_URL, PROVIDER_URL);
		InitialContext context = new InitialContext(table);
		return context;
	}
 
	/**
	 * Creates all the necessary objects for sending messages to a JMS topic.
	 * 
	 * @param ctx JNDI initial context
	 * @param topicName name of topic
	 * @exception NamingException if operation cannot be performed
	 * @exception JMSException if JMS fails to initialize due to internal error
	 */
	public void init(Context ctx, String topicName) throws NamingException, JMSException {
		tconFactory = (TopicConnectionFactory) ctx.lookup(CONNECTION_FACTORY_JNDI_NAME);
		topicConnection = tconFactory.createTopicConnection();
		topicSession = topicConnection.createTopicSession(false, Session.AUTO_ACKNOWLEDGE);
		topic = (Topic) ctx.lookup(topicName);
		topicPublisher  = topicSession.createPublisher(topic);
 
		textMessage = topicSession.createTextMessage();
		streamMessage = topicSession.createStreamMessage();
		bytesMessage = topicSession.createBytesMessage();
		mapMessage = topicSession.createMapMessage();
		objectMessage = topicSession.createObjectMessage();
 
		topicConnection.start();
	}
 
	/**
	 * Sends a message to a JMS topic.
	 * 
	 * @param message message to be sent
	 * @exception JMSException if JMS fails to send message due to internal error
	 */
	public void send(String message) throws JMSException {
		// type1: set TextMessage
		textMessage.setText(message);
		
		// type2: set StreamMessage
		streamMessage.writeString(message);
		streamMessage.writeInt(20);
 
		// type3: set BytesMessage
		byte[] block = message.getBytes();
		bytesMessage.writeBytes(block);
 
		// type4: set MapMessage
		mapMessage.setString("name", message);
 
		// type5: set ObjectMessage
		/*User user = new User();
		user.setName(message);
		user.setAge(30);*/
		objectMessage.setObject(" i am here");
		
		topicPublisher.publish(objectMessage);
	}
 
	/**
	 * read the msg from the console, then send it.
	 * 
	 * @param msgPublisher
	 * @throws IOException if IO fails to send message due to internal error
	 * @throws JMSException if JMS fails to send message due to internal error
	 */
	private static void readAndSend(TopicMsgPublisher msgPublisher) throws IOException, JMSException {
		BufferedReader msgStream = new BufferedReader(new InputStreamReader(System.in));
		System.out.println("Enter message(input quit to quit):");  
		String line = null;
		boolean quit = false; 
		do {
			line = msgStream.readLine();
			if (line != null && line.trim().length() != 0) { 
				msgPublisher.send(line);
				System.out.println("JMS Message Sent: " + line + "\n");
				quit = line.equalsIgnoreCase("quit");
			}
		} while (!quit);
 
	}
	
	/**
	 * release resources.
	 * 
	 * @exception JMSException if JMS fails to close objects due to internal error
	 */
	public void close() throws JMSException {
		topicPublisher.close();
		topicSession.close();
		topicConnection.close();
	}
	
	/**
	 * test client.
	 * 
	 * @param args
	 * @throws Exception 
	 */
	public static void main(String[] args) throws Exception {
		InitialContext ctx = getInitialContext(); 
		TopicMsgPublisher publisher = new TopicMsgPublisher();  
		publisher.init(ctx, TOPIC_JNDI_NAME); 
		readAndSend(publisher);
		publisher.close();
	}
}