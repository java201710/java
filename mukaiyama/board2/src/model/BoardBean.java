package model;

import java.io.Serializable;

public class BoardBean implements Serializable {
	private int id; //投稿№
	private String name; //名前
	private String email; //E-Mail
	private String comment; //コメント
	private String dateTime; //投稿日時

	public BoardBean() {
	}

	public BoardBean(int id, String name, String email, String comment, String dateTime) {
		this.setId(id);
		this.setName(name);
		this.setEmail(email);
		this.setComment(comment);
		this.setDateTime(dateTime);
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