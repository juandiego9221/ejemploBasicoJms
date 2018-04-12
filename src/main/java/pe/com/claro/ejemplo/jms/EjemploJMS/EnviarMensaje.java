package pe.com.claro.ejemplo.jms.EjemploJMS;

import java.util.Hashtable;

import javax.jms.JMSException;
import javax.jms.ObjectMessage;
import javax.jms.Queue;
import javax.jms.QueueConnection;
import javax.jms.QueueConnectionFactory;
import javax.jms.QueueReceiver;
import javax.jms.QueueSender;
import javax.jms.QueueSession;
import javax.jms.TextMessage;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.Context;

public class EnviarMensaje {
	
	private Context context = null;
	private QueueConnectionFactory queueConnectionFactory = null;	
	private QueueConnection queueConnection = null;
	private QueueSession queueSession = null;
	private Queue queue = null;
	private QueueSender queueSender = null;
	private QueueReceiver queueReceiver = null;
	private ObjectMessage objectMessage = null;
		
	private String JNDI_QUEUE_CF = "pe.com.claro.ejemplo.cf";
	private String JNDI_QUEUE= "pe.com.claro.ejemplo.queue";
	private static final String CONTEXTO = "weblogic.jndi.WLInitialContextFactory";
	private String URL = "t3://localhost:7001";
	private String USUARIO = "weblogic";
	private String PASSWORD = "weblogic123";
	
	public void enviarMensajePuntoAPunto(){
		
		System.out.println("Inicio del envio JMS");
		
		try{
			Hashtable<String, String> objProperties = new Hashtable<String, String>();
			objProperties.put(Context.INITIAL_CONTEXT_FACTORY, CONTEXTO);
			objProperties.put(Context.PROVIDER_URL, URL);
			objProperties.put(Context.SECURITY_PRINCIPAL, USUARIO);
			objProperties.put(Context.SECURITY_CREDENTIALS, PASSWORD);
			
			this.context = new InitialContext(objProperties);
			this.queueConnectionFactory = (QueueConnectionFactory) this.context.lookup(this.JNDI_QUEUE_CF);
			this.queueConnection = this.queueConnectionFactory.createQueueConnection();
			this.queueSession = this.queueConnection.createQueueSession(false, 0);
			this.queue  = (Queue) this.context.lookup(this.JNDI_QUEUE);
			this.queueSender = this.queueSession.createSender(queue);
			
			TextMessage textMessage = this.queueSession.createTextMessage();
			textMessage.setText("Hola Mundo");
//			this.objectMessage = this.queueSession.createObjectMessage();
			this.queueSender.send(textMessage);
			System.out.println("- MENSAJE ENVIADO ...!!!");
			
		}catch(Exception e){
			System.out.println("Excepcion : " + e);

		}
		
	}
	
	public void limpiarConexiones(){
		try {
			if (this.queueSender != null) {
				this.queueSender.close();
			}
			if (this.queueReceiver != null) {
				this.queueReceiver.close();
			}
			if (this.queueSession != null) {
				this.queueSession.close();
			}
			if (this.queueConnection != null) {
				this.queueConnection.close();
			}

			this.objectMessage = null;
			this.queueSender = null;
			this.queueReceiver = null;
			this.queue = null;
			this.queueSession = null;
			this.queueConnection = null;
			this.queueConnectionFactory = null;
			this.context = null;

			System.out.println("- LIMPIANDO CONFIGURACION ...!!!");
		} catch (JMSException e) {
			System.out.println("Error, JMSException");
			e.printStackTrace();
		} catch (Exception e) {
			System.out.println("Error, Exception");
			e.printStackTrace();
		}
	}
	

}
