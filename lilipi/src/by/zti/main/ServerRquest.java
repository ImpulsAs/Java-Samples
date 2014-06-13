package by.zti.main;

import java.io.Serializable;

public class ServerRquest implements Serializable{
	
	private String request_text;
	private Object object;
	
	public ServerRquest(String text, Object object){
		request_text = text;
		this.setObject(object);
	}

	public String getRequest_text() {
		return request_text;
	}

	public void setRequest_text(String request_text) {
		this.request_text = request_text;
	}

	public Object getObject() {
		return object;
	}

	public void setObject(Object object) {
		this.object = object;
	}

}
