package by.zti.main;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.swing.JOptionPane;

public class Connector {
	private String host_ip;
	private int port;
	private Socket connection;
	private ObjectOutputStream output;
	private ObjectInputStream input;
	private boolean isConnected;
	private IOListener listener;
	
	public Connector(String host_ip, int port){
		this.host_ip = host_ip;
		this.port = port;
	}
	
	public void close(){
		try {
			input.close();
			output.close();
			setConnected(false);
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
	}
	
	public Object getObject(){
		return listener.getReadenObject();
	}
	
	public void connect(String host_ip, int port){
		try {
			connection = new Socket(InetAddress.getByName(host_ip), port);
			output = new ObjectOutputStream(connection.getOutputStream());
			input = new ObjectInputStream(connection.getInputStream());
			setConnected(true);
			listener = new IOListener(output, input);
		} catch (UnknownHostException e) {
			handleConnectionErrors();
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
	}
	
	public void connect(){
		try {
			connection = new Socket(InetAddress.getByName(getHost_ip()), getPort());
			output = new ObjectOutputStream(connection.getOutputStream());
			input = new ObjectInputStream(connection.getInputStream());
		} catch (UnknownHostException e) {
			handleConnectionErrors();
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
	}
	
	private void handleConnectionErrors(){
		int new_host_port = 0;
		String new_host_ip = JOptionPane.showInputDialog(null, "Can't connect the server (Error #11). Pleas, type new server IP adress or type 'CANCELL' to exit");
		if (new_host_ip.contains("CANCELL")){
			System.exit(11);
		}else{
			try{
				 new_host_port = Integer.parseInt(JOptionPane.showInputDialog(null, "Please, enter server port here"));
			}catch (Exception e2){};
			connect(new_host_ip, new_host_port);
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

	public IOListener getListener() {
		return listener;
	}

	public void setListener(IOListener listener) {
		this.listener = listener;
	}
	
	
}
