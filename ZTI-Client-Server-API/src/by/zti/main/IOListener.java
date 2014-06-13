package by.zti.main;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import javax.swing.JOptionPane;

public class IOListener implements Runnable {
	private ObjectInputStream input;
	private ObjectOutputStream output;
	private boolean isListeining;
	private ArrayList<Object> readenObject;
	
	public IOListener(ObjectOutputStream output, ObjectInputStream input){
		this.input = input;
		this.output = output;
		readenObject = new ArrayList<Object>();
		new Thread(this).start();
	}
	
	public void sendObject(Object object){
		try {
			output.flush();
			output.writeObject(object);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void run() {
		isListeining = true;
		while(isListeining){
			try {
				readenObject.add(input.readObject());
			} catch (ClassNotFoundException e) {
				//JOptionPane.showMessageDialog(null, e.getMessage());
			} catch (IOException e) {
				//JOptionPane.showMessageDialog(null, e.getMessage());
			}
		}
		close();
	}

	public void close() {
		isListeining=false;
		input=null;
		output=null;
		readenObject=null;
	}

	public boolean isListeining() {
		return isListeining;
	}

	public void setListeining(boolean isListeining) {
		this.isListeining = isListeining;
	}

	public ArrayList<Object> getReadenObject() {
		return readenObject;
	}

	public void clearObjects() {
		readenObject.clear();
	}

}
