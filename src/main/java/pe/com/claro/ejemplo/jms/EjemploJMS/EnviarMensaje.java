package pe.com.claro.ejemplo.jms.EjemploJMS;

import java.util.Hashtable;

import javax.jms.ObjectMessage;
import javax.jms.Queue;
import javax.jms.QueueConnection;
import javax.jms.QueueConnectionFactory;
import javax.jms.QueueReceiver;
import javax.jms.QueueSender;
import javax.jms.QueueSession;
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
		
	private String JNDI_QUEUE_CF = "";
	private String JNDI_QUEUE= "";
	private static final String CONTEXTO = "weblogic.jndi.WLInitialContextFactory";
	private String URL = "";
	private String USUARIO = "";
	private String PASSWORD = "";
	
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
			
			this.objectMessage = this.queueSession.createObjectMessage();
			this.queueSender.send(this.objectMessage);
			System.out.println("- MENSAJE ENVIADO ...!!!");
			
		}catch(Exception e){
			
		}
		
	}
	
	public void limpiarConexiones(){
//		try {
//			if (this.queueSender != null) {
//				this.queueSender.close();
//			}
//			if (this.objQueueReciver != null) {
//				this.objQueueReciver.close();
//			}
//			if (this.objQueueSesion != null) {
//				this.objQueueSesion.close();
//			}
//			if (this.objQueueConexion != null) {
//				this.objQueueConexion.close();
//			}
//
//			this.objMensaje = null;
//			this.objQueueSender = null;
//			this.objQueueReciver = null;
//			this.objQueue = null;
//			this.objQueueSesion = null;
//			this.objQueueConexion = null;
//			this.objQueueFactory = null;
//			this.objContexto = null;
//
//			System.out.println("- LIMPIANDO CONFIGURACION ...!!!");
//		} catch (JMSException e) {
//			System.out.println("Error, JMSException");
//			e.printStackTrace();
//		} catch (Exception e) {
//			System.out.println("Error, Exception");
//			e.printStackTrace();
//		}
	}
	

}
