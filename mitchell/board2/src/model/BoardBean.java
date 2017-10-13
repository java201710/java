package model;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class BoardBean {
	private int id;
	private String name;
	private String email;
	private String comment;
	private String dateTime;

	public BoardBean() {
	}

	public BoardBean(int id, String name, String email, String comment) {
		setId(id);

		if (name.equals("")) {
			setName("ゲスト");
		} else {
			setName(name);
		}

		if (email.equals("")) {
			setEmail("なし");
		} else {
			setEmail(email);
		}
		setComment(comment);

		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		setDateTime(df.format(new Date(System.currentTimeMillis())));

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
