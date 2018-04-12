package br.com.caelum.jms;

import java.util.Scanner;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageListener;
import javax.jms.Session;
import javax.naming.InitialContext;
import javax.naming.NamingException;

public class TesteConsumidor {

	@SuppressWarnings("resource")
	public static void main(String[] args) throws NamingException, JMSException {
		
        InitialContext context = new InitialContext(); 

        //imports do package javax.jms
        ConnectionFactory factory = (ConnectionFactory) context.lookup("ConnectionFactory");
        Connection connection = factory.createConnection();
        connection.start();

        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        Destination fila = (Destination) context.lookup("financeiro");
        MessageConsumer consumer = session.createConsumer(fila);

        //Message message = consumer.receive();
        //System.out.println("Recebendo msg: "+ message);
        
        consumer.setMessageListener(new MessageListener(){

            @Override
            public void onMessage(Message message){
                System.out.println(message);
            }
        });

        session.close();
        
        
        new Scanner(System.in).nextLine(); //parar o programa para testar a conexao

        connection.close();
        context.close();



	}

}
