package test.google.gosn;

import java.io.Serializable;
import java.util.Date;

public class Users implements Serializable {
	private static final long serialVersionUID = 3258802394052735249L;
	
	private int id;
	private String username;
	private String password;
	private Date register;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Date getRegister() {
		return register;
	}
	public void setRegister(Date register) {
		this.register = register;
	}
	
	
}
