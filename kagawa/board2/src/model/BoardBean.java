package model;

import java.io.Serializable;

public class BoardBean implements Serializable {
	private int id;
	private String name;
	private String email;
	private String comment;
	private String dateTime;

	public BoardBean() {
	}

	public BoardBean(int id, String name, String email, String comment, String dateTime) {
		this.id = id;
		this.name = name;
		this.email = email;
		this.comment = comment;
		this.dateTime = dateTime;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public String getDateTime() {
		return dateTime;
	}

	public void setDateTime(String dateTime) {
		this.dateTime = dateTime;
	}

}