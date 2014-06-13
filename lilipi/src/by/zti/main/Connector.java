package by.zti.main;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

public class Connector implements Runnable{
	private String host_ip;
	private int port;
	private Socket connection;
	private ObjectOutputStream output;
	private ObjectInputStream input;
	private boolean isConnected;
	private Object read_object;
	
	public Connector(String host_ip, int port){
		this.host_ip = host_ip;
		this.port = port;
		new Thread(this).start();
	}
	
	@Override
	public void run() {
		try {
			connection = new Socket(InetAddress.getByName(host_ip), port);
			output = new ObjectOutputStream(connection.getOutputStream());
			input = new ObjectInputStream(connection.getInputStream());
			setConnected(true);
			while(isConnected()){
				setRead_object(input.readObject());
			}
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void sendObject(Object object){
		try {
			output.flush();
			output.writeObject(object);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public String getHost_ip() {
		return host_ip;
	}
	public void setHost_ip(String host_ip) {
		this.host_ip = host_ip;
	}
	public int getPort() {
		return port;
	}
	public void setPort(int port) {
		this.port = port;
	}
	public Socket getConnection() {
		return connection;
	}
	public void setConnection(Socket connection) {
		this.connection = connection;
	}
	public ObjectOutputStream getOutput() {
		return output;
	}
	public void setOutput(ObjectOutputStream output) {
		this.output = output;
	}
	public ObjectInputStream getInput() {
		return input;
	}
	public void setInput(ObjectInputStream input) {
		this.input = input;
	}
	public boolean isConnected() {
		return isConnected;
	}
	public void setConnected(boolean isConnected) {
		this.isConnected = isConnected;
	}

	public Object getRead_object() {
		return read_object;
	}

	public void setRead_object(Object read_object) {
		this.read_object = read_object;
	}
}
