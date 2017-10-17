package model;


public class BoardBean {

	//変数の宣言と初期値(Set variables)。
	private int id;
	private String name, email, comment, dateTime;

	//コンストラクタ(Constructors)
	public BoardBean() {
		this(-1, "", "", "", "");
	}

	public BoardBean(int id, String name, String email, String comment, String dateTime) {
		setId(id);
		setName(name);
		setEmail(email);
		setComment(comment);
	}

	// 「getter」と「setter」のメソッド(Getter and setter methods)。
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
