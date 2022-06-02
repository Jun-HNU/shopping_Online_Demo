package com.hnu.shopping.manage.JMSCustomer;

import javax.jms.*;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.util.Hashtable;


//https://blog.csdn.net/u014691866/article/details/78350439/
public class QueueMsgReceiver implements MessageListener {
	// Defines the JNDI context factory.
	public final static String JNDI_FACTORY = "weblogic.jndi.WLInitialContextFactory";
 
	// Defines the JNDI provider url.
	public final static String PROVIDER_URL = "t3://localhost:7001";
 
	// Defines the JMS connection factory for the queue.
	public final static String CONNECTION_FACTORY_JNDI_NAME = "myJMSConnectionFactoryJNDIName";
 
	// Defines the queue, use the queue JNDI name 
	public final static String QUEUE_JNDI_NAME = "myJMSQueueJNDIName";
 
	private QueueConnectionFactory qconFactory;
	private QueueConnection queueConnection;
	private QueueSession queueSession;
	private QueueReceiver queueReceiver;
	private Queue queue;
	private boolean quit = false;
	
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
	 * Creates all the necessary objects for receiving messages from a JMS queue.
	 * 
	 * @param ctx JNDI initial context
	 * @param queueName name of queue
	 * @exception NamingException if operation cannot be performed
	 * @exception JMSException if JMS fails to initialize due to internal error
	 */
	public void init(Context ctx, String queueName) throws NamingException, JMSException {
		qconFactory = (QueueConnectionFactory) ctx.lookup(CONNECTION_FACTORY_JNDI_NAME);
		queueConnection = qconFactory.createQueueConnection(); 
		queueSession = queueConnection.createQueueSession(false, Session.AUTO_ACKNOWLEDGE);
		queue = (Queue) ctx.lookup(queueName);
		queueReceiver = queueSession.createReceiver(queue); 
		queueReceiver.setMessageListener(this);
		
		// second thread: message reveive thread.
		queueConnection.start();  
	}
 
	/**
	 * implement from MessageListener.
	 * when a message arrived, it will be invoked.
	 * 
	 * @param message message
	 */
	public void onMessage(Message message) {
		try {
			String msgStr = "";  
			int age = 0; 
 
			if (message instanceof TextMessage) {
				msgStr = ((TextMessage) message).getText();
			} else if (message instanceof StreamMessage) {
				msgStr = ((StreamMessage) message).readString();
				age = ((StreamMessage) message).readInt();
			} else if (message instanceof BytesMessage) {
				byte[] block = new byte[1024];
				((BytesMessage) message).readBytes(block);
				msgStr = String.valueOf(block);
			} else if (message instanceof MapMessage) {
				msgStr = ((MapMessage) message).getString("name");
			} else if (message instanceof ObjectMessage) {
				/*User user = (User) ((ObjectMessage) message).getObject();
				msgStr = user.getName(); 
				age = user.getAge();*/
			}
 
			System.out.println("Message Received: " + msgStr + ", " + age);
 
			if (msgStr.equalsIgnoreCase("quit")) {
				synchronized (this) {
					quit = true;
					this.notifyAll(); // Notify main thread to quit
				}
			}
		} catch (JMSException e) {
			throw new RuntimeException("error happens", e);
		}
	}
 
	/**
	 * release resources.
	 * 
	 * @exception JMSException if JMS fails to close objects due to internal error
	 */
	public void close() throws JMSException {
		queueReceiver.close();
		queueSession.close();
		queueConnection.close();
	}
 
	/**
	 * test client.
	 * first thread(main thread)
	 * 
	 * @param args
	 * @throws Exception 
	 */
	public static void main(String[] args) throws Exception {
		InitialContext ctx = getInitialContext();
		QueueMsgReceiver receiver = new QueueMsgReceiver(); 
		receiver.init(ctx, QUEUE_JNDI_NAME);
 
		// Wait until a "quit" message has been received.
		synchronized (receiver) {
			while (!receiver.quit) {
				try {
					receiver.wait();
				} catch (InterruptedException e) { 
					throw new RuntimeException("error happens", e);
				}
			}
		}
		receiver.close();
	}
}
 