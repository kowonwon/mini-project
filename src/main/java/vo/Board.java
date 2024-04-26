package vo;

import java.sql.Timestamp;

public class Board {
	private int no;
	private String title;
	private String writer;
	private String bookTitle;
	private String author;
	private Timestamp regDate;
	private String content;
	private String pass;
	private String img1;
	private String file1;
	
	public Board() {}
	public Board(int no, String title, String writer, String bookTitle, String author, Timestamp regDate, String content, String pass, 
			String img1, String file1) {
		super();
		this.no = no;
		this.title = title;
		this.writer = writer;
		this.bookTitle = bookTitle;
		this.author = author;
		this.regDate = regDate;
		this.content = content;
		this.pass = pass;
		this.img1 = img1;
		this.file1 = file1;
	}

	public int getNo() {
		return no;
	}

	public void setNo(int no) {
		this.no = no;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getWriter() {
		return writer;
	}

	public void setWriter(String writer) {
		this.writer = writer;
	}

	public String getBookTitle() {
		return bookTitle;
	}

	public void setBookTitle(String bookTitle) {
		this.bookTitle = bookTitle;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public Timestamp getRegDate() {
		return regDate;
	}

	public void setRegDate(Timestamp regDate) {
		this.regDate = regDate;
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

	public String getImg1() {
		return img1;
	}

	public void setImg1(String img1) {
		this.img1 = img1;
	}

	public String getFile1() {
		return file1;
	}

	public void setFile1(String file1) {
		this.file1 = file1;
	}
}