package by.zti.main;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.JOptionPane;

public class ServerConnector implements Runnable{
	private int max_users;
	private int port;
	private ServerSocket server;
	private boolean isRunning;
	private HashMap<String, User> users;
	private ArrayList<Object> readenObjects;
	
	public ServerConnector(int port, int max_users){
		this.port = port;
		this.max_users = max_users;
		users = new HashMap<String, User>();
		readenObjects = new ArrayList<Object>();
	}
	
	public void sendObject(String key, Object object){
		users.get(key).sendObject(object);
	}
	
	public void close(){
		try {
			isRunning = false;
			users.clear();
			readenObjects.clear();
			server.close();
		} catch (IOException e) {
			//JOptionPane.showMessageDialog(null, e.getMessage());
		}
	}
	
	@Override
	public void run() {
		try {
			
			server = new ServerSocket(port, max_users);
			while(isRunning){
				
				Socket connection = server.accept();
				if(connection!=null){
					System.out.println("------ Connected from: "+connection.getInetAddress().getHostAddress()+" ------");
					users.put(connection.getInetAddress().getHostAddress(), new User(connection, this));
					connection = null;
				}
			}
		} catch (IOException e) {
			//JOptionPane.showMessageDialog(null, e.getMessage());
		}
	}
	
	public void start(int max_users, int port){
		isRunning = true;
		new Thread(this).start();
	}
	
	public ServerConnector getInstance(){
		return this;
	}
	
	public synchronized void addObjects(ArrayList<Object> readenObject){
		readenObjects.addAll(readenObject);
	}
	
	public int getPort() {
		return port;
	}
	public void setPort(int port) {
		this.port = port;
	}

	public int getMax_users() {
		return max_users;
	}

	public void setMax_users(int max_users) {
		this.max_users = max_users;
	}

	public ServerSocket getServer() {
		return server;
	}

	public void setServer(ServerSocket server) {
		this.server = server;
	}

	public boolean isRunning() {
		return isRunning;
	}

	public void setRunning(boolean isRunning) {
		this.isRunning = isRunning;
	}

	public ArrayList<Object> getReadenObjects() {
		return readenObjects;
	}

	public HashMap<String, User> getUsers() {
		return users;
	}

	public void setUsers(HashMap<String, User> users) {
		this.users = users;
	}

}
