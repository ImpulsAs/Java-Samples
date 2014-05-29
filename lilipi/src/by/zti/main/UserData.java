package by.zti.main;

import java.io.Serializable;

public class UserData implements Serializable{
	private String nick, pass, url;

	public UserData(String nick, String pass, String url) {
		this.nick = nick;
		this.pass = pass;
		this.url = url;
	}

	public String getNick() {
		return nick;
	}

	public void setNick(String nick) {
		this.nick = nick;
	}

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

}
