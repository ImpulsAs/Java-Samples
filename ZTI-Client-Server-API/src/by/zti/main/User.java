package by.zti.main;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class User implements Runnable{
	private Socket connection;
	private IOListener listener;
	private ServerConnector connector;
	
	public User(Socket connection, ServerConnector connector){
		this.connection = connection;
		this.connector = connector;
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
		for(Object object: listener.getReadenObject()){
			connector.addObject(object);
		}
		listener.clearObjects();
	}
	
}
