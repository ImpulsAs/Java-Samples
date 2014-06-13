package by.zti.main;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import javax.swing.JOptionPane;

public class User implements Runnable{
	private Socket connection;
	private IOListener listener;
	private ServerConnector connector;
	private String IP;
	
	public User(Socket connection, ServerConnector connector){
		this.connection = connection;
		this.connector = connector;
		this.IP = connection.getInetAddress().getHostAddress();
		initialise();
	}
	
	public void sendObject(Object object){
		listener.sendObject(object);
	}
	
	public void initialise(){
		try {
			this.listener = new IOListener(new ObjectOutputStream(connection.getOutputStream()), new ObjectInputStream(connection.getInputStream()));
			new Thread(this).start();
		} catch (IOException e) {};
	}

	@Override
	public void run() {
		while(connector.isRunning()){
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			if(!listener.getReadenObject().isEmpty()){
				connector.addObjects(listener.getReadenObject());
				listener.clearObjects();
				System.out.println(connector.getReadenObjects().size());
			}
		}
	}
	
}
