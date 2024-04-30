package vo;

import java.sql.Timestamp;

public class Comment {
	private int cNo;
	private int no;
	private String writer;
	private String content;
	private String pass;
	private Timestamp regDate;
	
	public Comment() {}

	public Comment(int cNo, int no, String writer, String content, String pass, Timestamp regDate) {
		this.cNo = cNo;
		this.no = no;
		this.writer = writer;
		this.content = content;
		this.pass = pass;
		this.regDate = regDate;
	}

	public int getcNo() {
		return cNo;
	}

	public void setcNo(int cNo) {
		this.cNo = cNo;
	}

	public int getNo() {
		return no;
	}

	public void setNo(int no) {
		this.no = no;
	}

	public String getWriter() {
		return writer;
	}

	public void setWriter(String writer) {
		this.writer = writer;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

	public Timestamp getRegDate() {
		return regDate;
	}

	public void setRegDate(Timestamp regDate) {
		this.regDate = regDate;
	}
}