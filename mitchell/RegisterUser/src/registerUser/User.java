package registerUser;

import java.io.Serializable;

public class User implements Serializable {
	private String id, pass, name;
	
	protected User() {
		
	}
	protected User(String id, String pass, String name) {
		setID(id);
		setPass(pass);
		setName(name);
	}

	public void setID(String id) {
		this.id = id;
	}
	public void setPass(String pass) {
		this.pass = pass;
	}
	public void setName(String name) {
		this.name = name;
	}

	public String getID() {
		return this.id;
	}
	public String getPass() {
		return this.pass;
	}
	public String getName() {
		return this.name;
	}
}
