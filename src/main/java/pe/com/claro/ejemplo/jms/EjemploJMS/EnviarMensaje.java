package pe.com.claro.ejemplo.jms.EjemploJMS;

import javax.jms.ObjectMessage;
import javax.jms.Queue;
import javax.jms.QueueConnection;
import javax.jms.QueueConnectionFactory;
import javax.jms.QueueReceiver;
import javax.jms.QueueSender;
import javax.jms.QueueSession;
import javax.naming.Context;
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
	
	private static String xx = "";
	private static String aaa="";
	
	public void enviarMensajePuntoAPunto(){
		
		System.out.println("Inicio del envio JMS");
		
		try{
			
			
		}catch(Exception e){
			
		}
		
	}
	

}
